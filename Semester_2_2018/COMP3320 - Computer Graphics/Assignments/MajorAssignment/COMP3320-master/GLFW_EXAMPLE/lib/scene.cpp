/**
 * @file scene.hpp
 * A file describing the implementation of Scene class.
 * @date 13.09.2018
 * @see Scene
 */

#include "scene.hpp"
#include <iostream>

void Scene::addObjectShader(ShaderProgram& shader)
{
    objectShader = &shader;
    useObjects = true;
}

void Scene::addTerrainShader(ShaderProgram& shader)
{
    terrainShader = &shader;
    useTerrains = true;
}

void Scene::addObject(const DimensionalObject& obj)
{
    objects.push_back(shared_dim_obj(obj.clone()));
}

void Scene::addTerrain(const DimensionalObject& terrain)
{
    terrains.push_back(shared_dim_obj(terrain.clone()));
}

ShaderProgram* Scene::getObjectShader() const
{
    return objectShader;
}

ShaderProgram* Scene::getTerrainShader() const
{
    return terrainShader;
}

std::vector<shared_dim_obj> Scene::getObjects() const
{
    return objects;
}

std::vector<shared_dim_obj> Scene::getTerrains() const
{
    return terrains;
}

std::vector<GLfloat> Scene::getData() const
{
    std::vector<GLfloat> data;
    if (terrains.size() > 0)
    {
        std::vector<GLfloat> terr = getTerrainData();
        data.insert(data.end(), terr.begin(), terr.end());
    }
    if (objects.size() > 0)
    {
        std::vector<GLfloat> objs = getObjectData();
        data.insert(data.end(), objs.begin(), objs.end());
    }
    return data;
}

std::vector<GLfloat> Scene::getObjectData() const
{

    std::vector<GLfloat> data;
    for (auto& obj : objects)
    {
        if (data.size() == 0) data = obj->getData();
        else 
        {
            auto tmp = obj->getData();
            data.insert(data.end(), tmp.begin(), tmp.end());
        }
    }
    return data;
}

std::vector<GLfloat> Scene::getTerrainData() const
{

    std::vector<GLfloat> data;
    for (auto& obj : terrains)
    {
        if (data.size() == 0) data = obj->getData();
        else 
        {
            auto tmp = obj->getData();
            data.insert(data.end(), tmp.begin(), tmp.end());
        }
    }
    return data;
}

shared_dim_obj Scene::getObject(const int i) const
{
    return objects.at(i);
}

shared_dim_obj Scene::getTerrain(const int i) const
{
    return terrains.at(i);
}

void Scene::renderObjects(const std::string uniModelName, long& offset) const
{
    GLint uniModel = glGetUniformLocation(objectShader->get(), uniModelName.c_str());
    renderObjects(uniModel, offset);
}

void Scene::renderObjects(const GLint& uniModel, long& offset) const
{
    objectShader->use();
    renderObjects(objectShader->get(), uniModel, offset);
}

void Scene::renderObjects(const GLuint& program, const std::string uniModelName, long& offset) const
{
    GLint uniModel = glGetUniformLocation(program, uniModelName.c_str());
    renderObjects(program, uniModel, offset);
}

void Scene::renderObjects(const GLuint& program, const GLint& uniModel, long& offset) const
{
    int i = 0;
    for (auto& obj : objects)
    {
        obj->render(uniModel, offset);
        i++;
    }
}

void Scene::renderTerrains(const std::string uniModelName, long& offset) const
{
    GLint uniModel = glGetUniformLocation(terrainShader->get(), uniModelName.c_str());
    renderTerrains(uniModel, offset);
}

void Scene::renderTerrains(const GLint& uniModel, long& offset) const
{
    terrainShader->use();
    renderTerrains(terrainShader->get(), uniModel, offset);
}

void Scene::renderTerrains(const GLuint& program, const std::string uniModelName, long& offset) const
{
    GLint uniModel = glGetUniformLocation(program, uniModelName.c_str());
    renderTerrains(program, uniModel, offset);
}

void Scene::renderTerrains(const GLuint& program, const GLint& uniModel, long& offset) const
{
    int i = 0;
    for (auto& obj : terrains)
    {   			
        obj->render(uniModel, offset);
        i++;
    }
}

void Scene::render(const std::string uniModelName) const
{
    long offset = 0;
    if (terrains.size() > 0)
    {
        if (!useTerrains) throw std::runtime_error("Scene contains terrain data but terrain shader has not been set up!");
        renderTerrains(uniModelName, offset);
    }
    if (objects.size() > 0)
    {
        if (!useObjects) throw std::runtime_error("Scene contains object data but object shader has not been set up!");
        renderObjects(uniModelName, offset);
    }
}


Scene::~Scene() {}
