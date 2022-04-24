/**
 * @file texture.hpp
 * A file describing the Texture class.
 * @date 13.09.2018
 * @see Texture
 */

#ifndef TEXTURE_HPP
#define TEXTURE_HPP

#include <string>
#include <memory>
#include <GL/glew.h>

class Texture {

    /**
     * Width of the texture in pixels (default 0).
     */
    int width;

    /**
     * Height of the texture in pixels (default 0).
     */
    int height;

    protected:

        /**
         * A pointer to the image data.
         * TODO: Use smart pointers?
         *
        unsigned char* data;
        //std::shared_ptr<unsigned char> data;
        */

        /**
         * Path to the texture file.
         */
        std::string filePath;

        /**
         * Texture id.
         */
        GLuint id;

        /**
         * Texture param (i.e GL_REPEAT / GL_CLAMP_TO_EDGE).
         */
        GLint param;

    public:
        /**
         * Default constructor for Texture class.
         */
        Texture(): width(0), height(0), param(GL_REPEAT) {};

        /**
         * File path constructor for Texture class.
         * @param path - A path to the texture file.
         */
        Texture(std::string path, GLint param);

        /**
         * Set the file path for texture.
         * @param path - A path to the texture file.
         */
        void setFilePath(std::string path);

        void setParam(GLint param);

        /**
         * Load the texture.
         * @throws std::invalid_argument - The given texture file not found.
         */
        void load();

        /**
         * Get a pointer to the texture data.
         */
        unsigned char* getData() const;
        //std::shared_ptr<unsigned char> getData() const;

        /**
         * Get texture width.
         * @return The texture width in pixels.
         */
        int getWidth() const;

        /**
         * Get the texture height.
         * @return The texture height in pixels.
         */
        int getHeight() const;

        /**
         * Get the texture id.
         * @return The texture id.
         */
        GLuint get() const;

        /**
         * Clone the texture object.
         * @return A pointer to Texture which
         * is a copy of this.
         */
        Texture* clone() const;

        /**
         * Copy assignment operator for Texture.
         * @param tex - A reference to the source Texture
         * @return A reference to the caller.
         */
        Texture& operator=(const Texture& tex);

        /**
         * Bind the texture.
         */
        void bind() const;

        /**
         * Destructor for Textrure class.
         */
        virtual ~Texture();


};

// Type definition for shared texture pointer
typedef std::shared_ptr<Texture> shared_tex;

#endif /* TEXTURE_HPP */