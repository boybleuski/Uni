/**
 * @file shader_program.hpp
 * @date 15.9.2018
 * A file describing the API of ShaderProgram class.
 * @see ShaderProgram
 */

#ifndef SHADER_PROGRAM_HPP
#define SHADER_PROGRAM_HPP

#include <string>
#include <GL/glew.h>

/**
 * Class for managing shader programs.
 * NOTE: glew has to be initialzied before using
 * ShaderProgram class - and context has to be set
 * up before initializing glew (create Window and set
 * it current context).
 */
class ShaderProgram {
    
    const std::string vertexShaderPath;
    const std::string fragmentShaderPath;
    GLuint vertexShaderId;
    GLuint fragmentShaderId;
    GLuint id;

    /**
     * Compile as single shader with given type.
     * @param path - Path to the shader file
     * @param shaderId - Reference to the shader id
     * @param type - Shader type (vertex or fragment)
     */
    void compileShader(const std::string path, GLuint& shaderId, GLenum type);

    /**
     * Get compile status of a shader.
     * @param shader Id of the shader
     * @return Boolean indicating successful compilation.
     */
    bool getCompileStatus(const GLuint shader) const;

    /**
     * Get the compile status of a shader.
     * @param shader Id of the shader
     * @return Log message
     */
    std::string readCompileLog(const GLuint shader) const;

    /**
     * Get program linking status.
     * @return Boolean indicating successful linking.
     */
    bool getLinkStatus() const;

    public:

        /**
         * Default constructor for ShaderProgram class.
         * @param vertexShader - A path to the vertex shader
         * @param fragmentShader - A path to the fragment shader
         */
        ShaderProgram(const std::string vertexShader, const std::string fragmentShader);

        /**
         * Compile shaders.
         * @throws std::runtime_error - Compiltaion failed
         */
        void compileShaders();
        
        /**
         * Attach shaders into the program.
         */
        void attachShaders();

        /**
         * Link the shader program.
         */
        void link();

        /**
         * Use the shader program.
         */
        void use();

        /**
         * Get the program id.
         * @return The program id.
         */
        GLuint get() const;

        /**
         * Get vertex shader id.
         * @return Id of the vertex shader.
         */
        GLuint getVertexShaderId() const;

        /**
         * Get fragment shader id.
         * @return Id of the fragment shader.
         */
        GLuint getFragmentShaderId() const;

        /**
         * Get uniform location from the program.
         * @return Location of the uniform in program.
         */
        GLint getUniformLocation(const std::string& uniform) const;

        /**
         * Destructor for ShaderProgram.
         */
        virtual ~ShaderProgram();

};

#endif /* SHADER_PROGRAM_HPP */