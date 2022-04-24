
#define CATCH_CONFIG_RUNNER
#include "catch.hpp"
#include <GLFW/glfw3.h>

// Define an error callback
static void error_callback(int error, const char* description)
{
    fputs(description, stderr);
    #ifdef _WIN32
        _fgetchar();
    #else
        getchar();
    #endif
}

/**
 * GLFW is initialized before tests since GLEW
 * is depending on it. GLEW has to be initialized
 * before using ShaderProgram class.
 */
int main( int argc, char* argv[] )
{

    // Set the error callback
    glfwSetErrorCallback(error_callback);

    //Initialize GLFW
    if (!glfwInit())
    {
        exit(EXIT_FAILURE);
    }

    //Set the GLFW window creation hints - these are optional
    #ifdef __APPLE__
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4); //Request a specific OpenGL version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1); //Request a specific OpenGL version
        glfwWindowHint(GLFW_SAMPLES, 4); //Request 4x antialiasing
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
    #elif __linux__
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); //Request a specific OpenGL version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2); //Request a specific OpenGL version
        glfwWindowHint(GLFW_SAMPLES, 4); //Request 4x antialiasing
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    #else
        // Define windows specific glfwWindowHint calls if needed
    #endif

    int result = Catch::Session().run( argc, argv );
    // global clean-up...
    glfwTerminate();

    return 0;
}