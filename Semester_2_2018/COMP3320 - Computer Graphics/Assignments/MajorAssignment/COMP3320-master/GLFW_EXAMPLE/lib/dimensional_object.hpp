/**
 * @file dimensinal_object.hpp
 * A file describing the DimensionalObject class.
 * @date 11.9.2018
 * @see DimensionalObject
 */
#ifndef DIMENSIONAL_OBJECT_HPP
#define DIMENSIONAL_OBJECT_HPP

#include <vector>
#include <string>
#include <memory>
#include <GL/glew.h>
#include "../glm/glm.hpp"
#include "texture.hpp"

/**
 * Class for storign 3D object references.
 * NOTE: The faces in objects should be triangulated
 */
class DimensionalObject {

    /**
     * This is where the object data is stored
     */
    std::vector<GLfloat> data;

    /**
     * Number of vertices in the object
     */
    int vertices;

    /**
     * Path to the .obj -file
     */
    std::string filePath;

    /**
     * Texture data
     */
    shared_tex tex;

    /**
     * Flag telling if texture is set.
     */
    bool textureSet;

    /**
     * The object model matrix.
     */
    glm::mat4 model;

    public:
        
        /**
         * Default constructor for DimensionalObject.
         * @param path - A path to the object file
         */
        DimensionalObject(const std::string path);

        /**
         * Texture contructor for DimensionalObject.
         * @param pathObj - A path to the object file
         * @param pathTex - A path to the texture file
         */
        DimensionalObject(const std::string pathObj, const std::string pathTex);

        /**
         * Texture contructor for DimensionalObject.
         * @param pathObj - A path to the object file
         * @param tex - A reference to the texture
         */
        DimensionalObject(const std::string pathObj, const Texture& tex);

        /**
         * Load the mesh with Assimp library and texture with
         * SOIL library into this DimensionalObject.
         * @throws std::invalid_argument - The file(s) was not found
         */
        void load();

        /**
         * Load the mesh into the object.
         * @throws std::invalid_argument - The .obj -file was not found
         */
        void loadObject();

        /**
         * Load the texture into the object.
         * @throws std::invalid_argument - The texture file was not found
         */
        void loadTexture();

        /**
         * Get the count of vertices.
         * @return The count of vertices
         */
        int getVerticesCount() const;

        /**
         * Get the object data.
         * @return The object data.
         */
        std::vector<GLfloat> getData() const;

        /**
         * Get a reference to the texture object.
         * @return A reference to the texture object.
         */
        Texture* getTexture() const;

        /**
         * Set the texture object.
         * @param texture - A reference to the texture object
         */
        void setTexture(const Texture& texture);

        /**
         * Set the texture by passing a file path.
         * @param path - The file path to the texture
         */
        void setTexture(const std::string path);

        /**
         * Get a reference to the model matrix.
         */
        glm::mat4 getModel() const;

        /**
         * Set the model matrix.
         * @param m - The new model matrix
         */
        void setModel(const glm::mat4 m);

        /**
         * Move the model with translate function.
         * @param movement - Vector describing the movement
         */
        void translate(const glm::vec3 movement);

        /**
         * Rotate the model with given parameters.
         * @param angle - The angle in radians
         * @param axis - The axis of rotation (x, y z)
         */
        void rotate(const float angle, const glm::vec3 axis);

        /**
         * Scale the model with given scale vector.
         * @param scale - The axis of rotation (x, y z)
         */
        void scale(const glm::vec3 scale);

        /**
         * Render the object into given program.
         * @param program - The shader program
         * @param uniModelName - Name of the model uniform
         * @param offset - A reference ot the vertice offset
         */
        void render(const GLuint& program, const std::string uniModelName, long& offset);

        /**
         * Render the object into given program.
         * @param uniModel - A refernce to the model uniform
         * @param offset - A reference ot the vertice offset
         */
        void render(const GLint& uniModel, long& offset);

        /**
         * Clone the dimensional object.
         * @return A pointer to the new object.
         */
        DimensionalObject* clone() const;
        
        /**
         * Destructor for the DimensionalObject class.
         */
        virtual ~DimensionalObject();
};

// Type definition for shared dimensional object pointer
typedef std::shared_ptr<DimensionalObject> shared_dim_obj;

#endif /* DIMENSIONAL_OBJECT_HPP */