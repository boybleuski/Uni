/**
 * @file cubemap_texture.hpp
 * A file describing the API of CubemapTexture class.
 * @date 17.9.2018
 * @see CubemapTexture
 */

#ifndef CUBEMAP_TEXTURE_HPP
#define CUBEMAP_TEXTURE_HPP

#include "texture.hpp"
#include <vector>
#include <GL/glew.h>

class CubemapTexture : public Texture {

    const std::vector<std::string> faces;

    bool loaded;

    public:

        CubemapTexture(std::vector<std::string> facesIn): faces(facesIn), loaded(false) {}

        void load();

        GLuint get() const;

        /**
         * Bind the texture.
         */
        void bind() const;

};

#endif /* CUBEMAP_TEXTURE_HPP */