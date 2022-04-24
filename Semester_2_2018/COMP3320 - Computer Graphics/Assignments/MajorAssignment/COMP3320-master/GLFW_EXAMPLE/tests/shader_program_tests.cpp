/**
 * @file shader_program_tests.cpp
 * Unit tests for ShaderProgram class.
 * @see ShaderProgram
 * @date 15.9.2018
 */

#include "../lib/shader_program.hpp"
#include "../lib/window.hpp"
//#define CATCH_CONFIG_RUNNER // This tells Catch to provide a main() - only do this in one cpp file
#include "catch.hpp"

/**
 * Check that the constructor doesn't blow everything up
 */
TEST_CASE( "ShaderProgram" ) {
    SECTION( "Should construct the object without exceptions" ) {

        // Context definition before initializing glew
        Window window;
        window.makeCurrentContext();

        glewExperimental = GL_TRUE;
        GLenum err = glewInit();

        //If GLEW hasn't initialized
        if (err != GLEW_OK)
        {
            fprintf(stderr, "Error: %s\n", glewGetErrorString(err));
            exit(EXIT_FAILURE);
        }

        CHECK_NOTHROW([&]() {
            ShaderProgram program("shaders/shader.vert", "shaders/shader.frag");

        }());
    }
}

TEST_CASE( "ShaderProgram::compileShaders()" ) {
    SECTION( "Should throw a runtime error when compiling invalid shaders" ) {
        // Context definition before program definition
        Window window;
        window.makeCurrentContext();
        // Invalid fragment shader
        ShaderProgram program("tests/assets/shader.vert", "tests/assets/invalid.frag");
        // Invalid vertex shader
        ShaderProgram program2("tests/assets/invalid.vert", "tests/assets/shader.frag");

        CHECK_THROWS_AS(program.compileShaders(), std::runtime_error);
        CHECK_THROWS_AS(program2.compileShaders(), std::runtime_error);
    }

    SECTION( "Should not throw a runtime error when compiling valid shaders" ) {
         // Context definition before program definition
        Window window;
        window.makeCurrentContext();

        ShaderProgram program("tests/assets/shader.vert", "tests/assets/shader.frag");

        CHECK_NOTHROW(program.compileShaders());
    }

}

/**
 * Test linking since it's deleting the shaders
 * right after linking.
 */
TEST_CASE( "ShaderProgram::link()" ) {
    SECTION( "Should not cause trouble" ) {
        // Context definition before program definition
        Window window;
        window.makeCurrentContext();
        // Invalid fragment shader
        ShaderProgram program("tests/assets/shader.vert", "tests/assets/shader.frag");

        program.compileShaders();
        program.attachShaders();

        CHECK_NOTHROW(program.link());
    }
}