/**
 * @file cubemap_texture_tests.cpp
 * Unit tests for CubemapTexture class.
 * @see CubemapTexture
 * @date 17.9.2018
 */

#include "../lib/cubemap_texture.hpp"
#include "catch.hpp"

/**
 * Check that the constructor doesn't blow everything up
 */
TEST_CASE( "CubemapTexture constructor" ) {
    SECTION( "Should construct the object without exceptions" ) {

        CHECK_NOTHROW([&]() {
            CubemapTexture cubemap(std::vector<std::string>());
        }());
    }
}

/**
 * Check that loading an invalid object throw an error when receiving
 * invalid object.
 */
TEST_CASE( "CubemapTexture::load() " ) {
    std::vector<std::string> vec;
    for (int i = 0; i < 6; i++) vec.push_back("invalid");
    std::vector<std::string> vec2;
    for (int i = 0; i < 6; i++) vec2.push_back("tests/assets/kitten.png");
    std::vector<std::string> vec3;

    CubemapTexture invalid(vec3);
    CubemapTexture invalid2(vec);
    CubemapTexture valid(vec2);

    SECTION( "Should throw an exception when invalid file path" ) {

        //CHECK_THROWS_AS(invalid.load(), std::invalid_argument);
        CHECK_THROWS_AS(invalid2.load(), std::invalid_argument);

    }

    SECTION( "Should not throw an exception when valid file path" ) {
        CHECK_NOTHROW(valid.load());
    }
}
