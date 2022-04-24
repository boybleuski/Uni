/**
 * @file texture_tests.cpp
 * Unit tests for Texture class.
 * @see Texture
 * @date 13.9.2018
 */

#include "../lib/texture.hpp"
#include "../lib/window.hpp"
#include "catch.hpp"

/**
 * Check that the constructor doesn't blow everything up
 */
TEST_CASE( "Texture constructor" ) {
    SECTION( "Should construct the object without exceptions" ) {

        CHECK_NOTHROW([&]() {
            Texture tex("fooBar", GL_REPEAT);
        }());
        
        CHECK_NOTHROW([&]() {
            Texture tex;
        }());
        
    }
}

/**
 * Check that loading an invalid object throw an error when receiving
 * invalid object.
 */
TEST_CASE( "Texture::load() " ) {

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

    Texture invalid("izbis", GL_REPEAT);
    Texture invalid2;
    Texture valid("tests/assets/kitten.png", GL_REPEAT);

    SECTION( "Should throw an exception when invalid file path" ) {

        CHECK_THROWS_AS(invalid.load(), std::invalid_argument);
        CHECK_THROWS_AS(invalid2.load(), std::invalid_argument);

    }

    SECTION( "Should not throw an exception when valid file path" ) {
        CHECK_NOTHROW(valid.load());
    }
}

/**
 * Check that the width is 0 when texture not loaded.
 */
TEST_CASE( "Texture::getWidth()" ) {
    Texture valid("tests/assets/kitten.png", GL_REPEAT);

    SECTION( "Should return 0 when load() not called" ) {
        CHECK( valid.getWidth() == 0 );
    }

    SECTION( "Should return actual width when load() has been called" ) {
        valid.load();
        CHECK( valid.getWidth() == 512 );
    }
}

/**
 * Check that the height is 0 when texture not loaded.
 */
TEST_CASE( "Texture::getHeight()" ) {
    Texture valid("tests/assets/kitten.png", GL_REPEAT);

    SECTION( "Should return 0 when load() not called" ) {
        CHECK( valid.getHeight() == 0 );
    }

    SECTION( "Should return actual width when load() has been called" ) {
        valid.load();
        CHECK( valid.getHeight() == 512 );
    }
}

/**
 * NOTE:
 * No unit tests for Texture::getData() since
 * it's returning a pointer to the data - quite tricky
 * to test it in any way. If you know how, be my guest.
 */
