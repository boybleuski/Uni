/**
 * @file texture.cpp
 * A file containing the implementation of Texture class.
 * @date 13.09.2018
 * @see Texture
 */

#include "texture.hpp"
#ifdef _WIN32
    #include <SOIL.h>
#else
    #include <SOIL/SOIL.h>
#endif
#include <iostream>


Texture::Texture(std::string path, GLint param):
    filePath(path),
    width(0),
    height(0),
    param(param)
    {}

void Texture::load()
{
    glGenTextures(1, &id);
    glBindTexture(GL_TEXTURE_2D, id);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, param);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, param);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    unsigned char* data;
    if (!filePath.empty())
    {
        data = SOIL_load_image(filePath.c_str(), &width, &height, 0, SOIL_LOAD_RGB);
    }
    if (width <= 0 || height <= 0)
    {
        throw std::invalid_argument("Texture not found: " + (filePath.empty() ? "path not set" : filePath));
    }
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, data);
    //glGenerateMipmap(GL_TEXTURE_2D);
    SOIL_free_image_data(data);

    //glBindTexture(GL_TEXTURE_2D, 0);
}

void Texture::setFilePath(std::string path)
{
    filePath = path;
}

void Texture::setParam(GLint p)
{
    param = p;
}


int Texture::getHeight() const
{
    return height;
}

int Texture::getWidth() const
{
    return width;
}

GLuint Texture::get() const
{
    return id;
}

/**
 * Since the data in Texture class points to
 * image data, we can't just copy it - the destructor
 * of this would clear the data the pointer is
 * pointing at. Thus, load the image again to assign
 * new pointer to the clone.
 */
Texture* Texture::clone() const
{
    Texture* newTex = new Texture(filePath, param);
    if (width > 0 || height > 0)
    {
        newTex->load();
    }
    /*
    newTex->width = width;
    newTex->height = height;
    newTex->data = data;
    */
    return newTex;
}

void Texture::bind() const
{
    //glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, id);
}

/**
 * Copy assignment operator.
 */
Texture& Texture::operator=(const Texture& tex)
{
    if (this == &tex) return *this;

    filePath = tex.filePath;
    width = tex.width;
    height = tex.height;
    // If the source has loaded texture,
    // load it again into this
    if (tex.width > 0 || tex.height > 0)
    {
        load();
    }
    return *this;
}


Texture::~Texture() {}
