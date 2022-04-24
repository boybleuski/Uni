/**
 * @file shader_program.cpp
 * @date 15.9.2018
 * A file describing the implementation of ShaderProgram class.
 * @see ShaderProgram
 */

#include "shader_program.hpp"
#include <fstream>
#include <iostream>
#include <vector>

ShaderProgram::ShaderProgram(const std::string vertexShader, const std::string fragmentShader):
    vertexShaderPath(vertexShader),
    fragmentShaderPath(fragmentShader)
{
    id = glCreateProgram();
    if (id == 0) throw new std::runtime_error("An error occurred while creating OpenGL program");
}

void ShaderProgram::compileShader(const std::string path, GLuint& shaderId, GLenum type)
{
    std::ifstream in(path);
    std::string contents(
        (std::istreambuf_iterator<char>(in)),
        std::istreambuf_iterator<char>()
    );
    const char* source = contents.c_str(); // TODO: Should this pointer be freed?
    shaderId = glCreateShader(type);
    glShaderSource(shaderId, 1, &source, NULL);
    glCompileShader(shaderId);
}

bool ShaderProgram::getCompileStatus(const GLuint shader) const
{
    GLint status;
    glGetShaderiv(shader, GL_COMPILE_STATUS, &status);
    return (status == GL_TRUE) ? true : false;
}

bool ShaderProgram::getLinkStatus() const
{
    GLint status;
    glGetProgramiv(id, GL_LINK_STATUS, &status);
    return status == GL_TRUE ? true : false;
}

std::string ShaderProgram::readCompileLog(const GLuint shader) const
{
    int logLength = 512;
    // Store log data in a vector -> generate string easily
    std::vector<char> log(logLength);
    glGetShaderInfoLog(shader, logLength, NULL, log.data());
    return std::string(begin(log), end(log));
}

void ShaderProgram::compileShaders()
{
    compileShader(vertexShaderPath, vertexShaderId, GL_VERTEX_SHADER);
    if (!getCompileStatus(vertexShaderId))
    {
        std::string log = readCompileLog(vertexShaderId);
        throw std::runtime_error("Invalid vertex shader: " + log);
    }
    compileShader(fragmentShaderPath, fragmentShaderId, GL_FRAGMENT_SHADER);
    if (!getCompileStatus(fragmentShaderId))
    {
        std::string log = readCompileLog(fragmentShaderId);
        throw std::runtime_error("Invalid fragment shader: " + log);
    }
}

void ShaderProgram::attachShaders()
{   
    glAttachShader(id, vertexShaderId);
    glAttachShader(id, fragmentShaderId);
}

/**
 * Delete shaders as soon as linking is done.
 * Read more at https://stackoverflow.com/questions/9113154/proper-way-to-delete-glsl-shader
 */
void ShaderProgram::link()
{
    glLinkProgram(id);
    glDeleteShader(vertexShaderId);
    glDeleteShader(fragmentShaderId);
    if (!getLinkStatus())
    {
        throw std::runtime_error("Linking a shader program failed.");
    }
}

void ShaderProgram::use()
{
    glUseProgram(id);
}

GLuint ShaderProgram::get() const
{
    return id;
}

GLuint ShaderProgram::getVertexShaderId() const
{
    return vertexShaderId;
}

GLuint ShaderProgram::getFragmentShaderId() const
{
    return fragmentShaderId;
}

GLint ShaderProgram::getUniformLocation(const std::string& uniform) const
{
    return glGetUniformLocation(id, uniform.c_str());
}

ShaderProgram::~ShaderProgram()
{
    glDeleteProgram(id);
}