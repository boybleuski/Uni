/**
 * @file scene_tests.cpp
 * Unit tests for Texture class.
 * @see Texture
 * @date 13.9.2018
 */

#include "../lib/scene.hpp"
//#define CATCH_CONFIG_RUNNER // This tells Catch to provide a main() - only do this in one cpp file
#include "catch.hpp"
#include <iostream>
/**
 * Check that the constructor doesn't blow everything up
 */
TEST_CASE( "Scene constructor" ) {
    SECTION( "Should construct the object without exceptions" ) {

        CHECK_NOTHROW([&]() {
            Scene scene;
        }());
        
    }
}

TEST_CASE( "Scene::addObject()" ) {

    DimensionalObject obj1("tests/assets/cube.obj");
    DimensionalObject obj2("tests/assets/cube.obj");
    Scene scene;

    SECTION( "Should add objects" ) {

        CHECK_NOTHROW([&]() {
            scene.addObject(obj1);
        }());

        
        CHECK_NOTHROW([&]() {
            scene.addObject(obj2);
        }());

        CHECK( scene.getObjects().size() == 2 );
    }

}

TEST_CASE( "Scene::getObjects()" ) {

    DimensionalObject obj1("tests/assets/cube.obj");
    DimensionalObject obj2("tests/assets/cube.obj");
    obj1.loadObject();
    obj2.loadObject();
    Scene scene;

    SECTION( "Should return pointers to the objects" ) {

        scene.addObject(obj1);
        scene.addObject(obj2);

        CHECK( scene.getObjects().size() == 2 );

        CHECK( scene.getObjects().at(0)->getVerticesCount() == obj1.getVerticesCount() );
        CHECK( scene.getObjects().at(1)->getVerticesCount() == obj2.getVerticesCount() );
    }

}

TEST_CASE( "Scene::getObject()" ) {

    DimensionalObject obj1("tests/assets/cube.obj");
    DimensionalObject obj2("tests/assets/cube.obj");
    obj1.loadObject();
    obj2.loadObject();
    Scene scene;

    SECTION( "Should return pointer to the object" ) {

        scene.addObject(obj1);
        scene.addObject(obj2);

        CHECK( scene.getObjects().size() == 2 );

        CHECK( scene.getObject(0)->getVerticesCount() == obj1.getVerticesCount() );
        CHECK( scene.getObject(1)->getVerticesCount() == obj2.getVerticesCount() );
    }

}

TEST_CASE( "Scene::getData()" ) {

    DimensionalObject obj1("tests/assets/cube.obj");
    DimensionalObject obj2("tests/assets/cube.obj");
    DimensionalObject obj3("tests/assets/cube.obj");
    obj1.loadObject();
    obj2.loadObject();
    obj3.loadObject();
    Scene scene;

    SECTION( "Should return the total amount of data" ) {

        scene.addObject(obj1);
        scene.addObject(obj2);
        scene.addTerrain(obj3);
        auto data1 = obj1.getData();
        auto data2 = obj2.getData();
        auto data3 = obj3.getData();
        data1.insert(data1.end(), data2.begin(), data2.end());
        data1.insert(data1.end(), data3.begin(), data3.end());

        CHECK( scene.getData().size() == data1.size() );
    }

}
