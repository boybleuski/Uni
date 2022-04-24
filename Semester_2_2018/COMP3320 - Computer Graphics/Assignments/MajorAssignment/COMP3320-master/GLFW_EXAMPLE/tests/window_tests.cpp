/**
 * @file window_tests.cpp
 * Unit tests for Window class.
 * @see Window
 * @date 15.9.2018
 */

#include "../lib/window.hpp"
//#define CATCH_CONFIG_RUNNER // This tells Catch to provide a main() - only do this in one cpp file
#include "catch.hpp"

/**
 * Check that the constructor doesn't blow everything up
 */
TEST_CASE( "Window constructor" ) {
    SECTION( "Should construct the object without exceptions" ) {

        CHECK_NOTHROW([&]() {
            Window window;
            Window window2(1920, 1080);
            Window window3(1920, 1080, "Test window)");
        }());
        
    }
}

TEST_CASE ("Window::getWidth()" ) {
    SECTION( "Should return the correct width" ) {
        Window window;
        Window window2(2000, 1000);
        Window window3(1280, 720, "Test window");
        // Default width
        CHECK( window.getWidth() == 1920 );
        // Set widths
        CHECK( window2.getWidth() == 2000 );
        CHECK( window3.getWidth() == 1280 );
    }
}

TEST_CASE ("Window::getHeight()" ) {
    SECTION( "Should return the correct height" ) {
        Window window;
        Window window2(2000, 1000);
        Window window3(1280, 720, "Test window");
        // Default width
        CHECK( window.getHeight() == 1080 );
        // Set widths
        CHECK( window2.getHeight() == 1000 );
        CHECK( window3.getHeight() == 720 );
    }
}

TEST_CASE ("Window::getRatio()" ) {
    SECTION( "Should return the correct ratio" ) {
        Window window(2000, 1000);
        Window window2(1280, 720);

        CHECK( window.getRatio() == 2.0f );
        CHECK( floor(window2.getRatio() * 10) == 17.0f );
    }
}