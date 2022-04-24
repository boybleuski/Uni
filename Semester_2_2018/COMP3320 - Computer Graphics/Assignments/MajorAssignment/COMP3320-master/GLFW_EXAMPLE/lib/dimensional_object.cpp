/**
 * @file dimensional_object.cpp
 * A file containing the implementation of DimensionalObject class.
 * @date 11.9.2018
 * @see DimensionalObject
 */

#include "dimensional_object.hpp"
#include <assimp/scene.h>
#include <assimp/postprocess.h>
#include <assimp/Importer.hpp>
#include <iostream>
#include <memory>
#include "../glm/gtc/matrix_transform.hpp"
#include "../glm/gtc/type_ptr.hpp"

// Define the original loadMesh function
// TODO: Implement non-static version in DimensionalObject class?
static bool loadMesh(std::string file_name, std::vector<GLfloat>& data, int& number_of_elements);

// Constructor implementation
DimensionalObject::DimensionalObject(const std::string path):
    filePath(path),
    vertices(0),
    tex(new Texture()),
    textureSet(false)
    {}

DimensionalObject::DimensionalObject(const std::string pathObj, const std::string pathTex):
    filePath(pathObj),
    tex(new Texture(pathTex, GL_REPEAT)),
    textureSet(true),
    vertices(0)
    {}

DimensionalObject::DimensionalObject(const std::string pathObj, const Texture& tex):
    filePath(pathObj),
    tex(std::make_shared<Texture>(tex)),
    textureSet(true),
    vertices(0)
    {}

/**
 * Load the mesh from .obj file into the DimensionalObject instance
 * using Assimp library and texture with SOIL library.
 */
void DimensionalObject::load()
{
    loadObject();
    if (textureSet)
    {
        loadTexture();
    }
}

void DimensionalObject::loadObject()
{
    bool success = loadMesh(filePath, data, vertices);
    if (!success) throw std::invalid_argument("Object not found: " + filePath);
}

void DimensionalObject::loadTexture()
{
    if (tex->getWidth() == 0) tex->load();
}

/**
 * Get the count of vertices in this object.
 */
int DimensionalObject::getVerticesCount() const
{
    return vertices;
}

Texture* DimensionalObject::getTexture() const
{
    return tex.get();
}

/**
 * Get the actual mesh data of this object.
 */
std::vector<GLfloat> DimensionalObject::getData() const
{
    return data;
}

void DimensionalObject::setTexture(const Texture& texture)
{
    tex = shared_tex(texture.clone());
    textureSet = true;
}

void DimensionalObject::setTexture(const std::string path)
{
    tex = shared_tex(new Texture(path, GL_REPEAT));
    textureSet = true;
}

glm::mat4 DimensionalObject::getModel() const
{
    return model;
}

void DimensionalObject::setModel(const glm::mat4 m)
{
    model = m;
}

void DimensionalObject::translate(const glm::vec3 movement)
{
    model = glm::translate(model, movement);
}

void DimensionalObject::rotate(const float angle, const glm::vec3 axis)
{
    model = glm::rotate(model, angle, axis);
}

void DimensionalObject::scale(const glm::vec3 scale)
{
    model = glm::scale(model, scale);
}

DimensionalObject* DimensionalObject::clone() const
{
    DimensionalObject* obj = new DimensionalObject(filePath);
    obj->tex = shared_tex(tex->clone());
    obj->vertices = vertices;
    obj->data = data;
    obj->model = model;
    obj->textureSet = textureSet;
    return obj;
}

void DimensionalObject::render(const GLuint& program, const std::string uniModelName, long& offset)
{
    GLint uniModel = glGetUniformLocation(program, uniModelName.c_str());
    render(uniModel, offset);
}

void DimensionalObject::render(const GLint& uniModel, long& offset)
{
    tex->bind();
    glUniformMatrix4fv(uniModel, 1, GL_FALSE, glm::value_ptr(model));
    glDrawArrays(GL_TRIANGLES, offset, vertices);
    offset += vertices;
}

/**
 * Desctructor.
 */
DimensionalObject::~DimensionalObject() {}

/**
 * Load mesh from .obj file at given path to the given vector.
 * REFERENCE: This is straight copy-paste from the given solution 
 * on this course.
 * @param file_name - The file path to the .obj -file
 * @param data - A pointer to the vector storing the mesh data
 * @param number_of_elements - A pointer to the integer storing the amount
 * of vertices the object has
 * @return True if mesh loaded, false otherwise
 */
static bool loadMesh(std::string file_name, std::vector<GLfloat>& data, int& number_of_elements) {

    Assimp::Importer importer;
    importer.ReadFile(file_name, aiProcessPreset_TargetRealtime_MaxQuality);
    const aiScene* scene = importer.GetScene();
    number_of_elements = 0;

    if (scene) {

        if (scene->HasMeshes()) {
            for (unsigned int i = 0; i < scene->mNumMeshes; i++) {
                const struct aiMesh* mesh = scene->mMeshes[i];

                for (unsigned int t = 0; t < mesh->mNumFaces; ++t) {

                    const struct aiFace* face = &mesh->mFaces[t];
                    number_of_elements += face->mNumIndices;
                    if (face->mNumIndices != 3) {
                        std::cout << "WARNING " << __FILE__ << " : " << __LINE__ << " - faces are not triangulated" << std::endl;
                    }

                    for (unsigned int j = 0; j < face->mNumIndices; j++) {
                        int index = face->mIndices[j];

                        //Vertex positions
                        data.push_back(mesh->mVertices[index].x);
                        data.push_back(mesh->mVertices[index].y);
                        data.push_back(mesh->mVertices[index].z);

                        //Vertex normals
                        if (mesh->mNormals != NULL) {
                            //If we have normals, push them back next
                            data.push_back(mesh->mNormals[index].x);
                            data.push_back(mesh->mNormals[index].y);
                            data.push_back(mesh->mNormals[index].z);
                        }
                        else {
                            //If not, just set to zero, but warn the user as this will likely make the lighting null
                            data.push_back(0);
                            data.push_back(0);
                            data.push_back(0);
                            std::cout << "WARNING: No normals loaded for mesh " << file_name << std::endl;
                        }

                        //Vertex colours
                        if (mesh->mColors[0] != NULL) {
                            //If we have colours, append them
                            data.push_back(mesh->mColors[index]->r);
                            data.push_back(mesh->mColors[index]->g);
                            data.push_back(mesh->mColors[index]->b);
                        }
                        else {
                            //If no colours, push back white
                            data.push_back(1);
                            data.push_back(1);
                            data.push_back(1);
                        }

                        //Texture coords
                        if (mesh->mTextureCoords[0] != NULL) {
                            //Push back textures
                            data.push_back(mesh->mTextureCoords[0][index].x);
                            data.push_back(1 - mesh->mTextureCoords[0][index].y);
                        }
                        else {
                            std::cout << "WARNING: No texture coordinates loaded for mesh " << file_name << std::endl;
                            data.push_back(0);
                            data.push_back(0);
                        }
                    }

                }
            }
            return true;
        }
    }
    return false;
}