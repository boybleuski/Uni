/**
 * @file cubemap_texture.cpp
 * A file describing the implementation of CubemapTexture class.
 * @date 17.9.2018
 * @see CubemapTexture
 */

#include "cubemap_texture.hpp"
#ifdef _WIN32
    #include <SOIL.h>
#else
    #include <SOIL/SOIL.h>
#endif

void CubemapTexture::load()
{
    if (loaded) return;
    if (faces.size() != 6) throw std::invalid_argument("Cubemap requires 6 faces but received " + std::to_string(faces.size()));

    int height = 0, width = 0;
    unsigned char* data;

    glGenTextures(1, &id);
    glBindTexture(GL_TEXTURE_CUBE_MAP, id);

    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_WRAP_R, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_CUBE_MAP, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    for (int i = 0; i < faces.size(); i++)
    {
        data = SOIL_load_image(faces[i].c_str(), &width, &height, 0, SOIL_LOAD_RGB);
        if (width <= 0 || height <= 0)
        {
            throw std::invalid_argument("Texture not found for cubemap: " + (faces[i].empty() ? "path not set" : faces[i]));
        }
        glTexImage2D(GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, data);
        SOIL_free_image_data(data);
    }

    //glBindTexture(GL_TEXTURE_CUBE_MAP, 0);
    loaded = true;
    
}


GLuint CubemapTexture::get() const
{
    return id;
}

void CubemapTexture::bind() const
{
    //glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_CUBE_MAP, id);
}