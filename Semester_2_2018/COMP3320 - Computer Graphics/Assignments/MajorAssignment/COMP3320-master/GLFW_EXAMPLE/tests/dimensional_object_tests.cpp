/**
 * @file dimensional_object_tests.cpp
 * Unit tests for DimensionalObject class.
 * @see DimensionalObject
 * @date 11.9.2018
 */

#include "../lib/dimensional_object.hpp"
//#define CATCH_CONFIG_RUNNER // This tells Catch to provide a main() - only do this in one cpp file
#include "catch.hpp"
#include "../glm/glm.hpp"
#include "../glm/gtc/matrix_transform.hpp"


/**
 * Check that the constructor doesn't blow everything up
 */
TEST_CASE( "DimensionalObject constructor" ) {
    SECTION( "Should construct the object without exceptions" ) {
        Texture tex("fooBar", GL_REPEAT);
        CHECK_NOTHROW([&]() {
            DimensionalObject dimObj("fooBar");
            DimensionalObject dimObj2("fooBar", "izBiz");
            DimensionalObject dimObj3("foobar", tex);
        }());
    }
}

/**
 * Check that loading an invalid object throws an exception.
 */
TEST_CASE( "DimensionalObject::loadObject() " ) {
    DimensionalObject invalid("izbis");
    DimensionalObject valid("tests/assets/cube.obj");

    SECTION( "Should throw an exception when invalid file path" ) {
        
        CHECK_THROWS_AS(invalid.loadObject(), std::invalid_argument);
    }
    
    SECTION( "Should not throw an exception when valid file path" ) {
        CHECK_NOTHROW(valid.loadObject());
    }
    
}

/**
 * Check that loading an invalid texture throws an exception.
 */
TEST_CASE( "DimensionalObject::loadTexture() " ) {
    DimensionalObject invalid("izbis", "foobar");
    DimensionalObject valid("tests/assets/cube.obj", "tests/assets/kitten.png");

    SECTION( "Should throw an exception when invalid file path" ) {
        
        CHECK_THROWS_AS(invalid.loadTexture(), std::invalid_argument);
    }

    SECTION( "Should not throw an exception when valid file path" ) {
        CHECK_NOTHROW(valid.loadTexture());
    }
}

/**
 * Check that the vertices count is 0 when object not loaded.
 */
TEST_CASE( "DimensionalObject::getVerticesCount()" ) {
    DimensionalObject valid("tests/assets/cube.obj");

    SECTION( "Should return 0 when load() not called" ) {
        CHECK( valid.getVerticesCount() == 0 );
    }

    SECTION( "Should return x when load() has been called" ) {
        valid.loadObject();
        CHECK( valid.getVerticesCount() == 576 );
    }
}

/**
 * Check that the data matrix is empty when object not loaded.
 */
TEST_CASE( "DimensionalObject::getData()" ) {
    DimensionalObject valid("tests/assets/cube.obj");

    SECTION( "Should return empty vector when load() not called" ) {
        CHECK( valid.getData().size() == 0);
    }

    SECTION( "Should return data when load() has been called" ) {
        valid.loadObject();
        CHECK( valid.getData().size() == 6336 );
    }
}

/**
 * Check that loading textures and object will throw an exception
 * if one of them is wrong.
 */
TEST_CASE( "DimensionalObject::load()" ) {
    DimensionalObject invalid("tests/assets/cube.obj", "foobar");
    DimensionalObject invalid2("foobar", "test/assets/kitten.png");
    DimensionalObject valid("tests/assets/cube.obj", "tests/assets/kitten.png");
    DimensionalObject valid2("tests/assets/cube.obj");

    SECTION( "Should throw an exception when invalid file path" ) {
        
        CHECK_THROWS_AS(invalid.load(), std::invalid_argument);
        CHECK_THROWS_AS(invalid2.load(), std::invalid_argument);

    }

    SECTION( "Should not throw an exception when valid file path" ) {
        CHECK_NOTHROW(valid.load());
        CHECK_NOTHROW(valid2.load());
    }
}


 /**
  * Testing the DimensionalObject::clone() -function
  */
TEST_CASE( "DimensionalObject::clone()" ) {
  SECTION( "Should return a new object that is a clone of the caller" ) {
    DimensionalObject o1("tests/assets/cube.obj");
    o1.loadObject();
    std::unique_ptr<DimensionalObject> o2(o1.clone());

    CHECK( o1.getData().size() == o2->getData().size() );
    CHECK( o1.getVerticesCount() == o2->getVerticesCount() );

  }
}
  

TEST_CASE( "DimensionalObject::getModel() and DimensionalObject::setModel()" ) {

    SECTION( "Should return and set the model" ) {
        DimensionalObject obj("tests/assets/cube.obj");
        glm::mat4 model;

        CHECK( obj.getModel() == model );

        model = glm::translate(model, glm::vec3(1, 1, 1));
        obj.setModel(model);

        CHECK( obj.getModel() == model );
    }

}

TEST_CASE( "DimensionalObject::translate()" ) {

    SECTION( "Should translate the model into given direction" ) {
        DimensionalObject obj("tests/assets/cube.obj");
        glm::mat4 model;
        glm::vec3 translation(1, 3, 3);

        CHECK( obj.getModel() == model );

        model = glm::translate(model, translation);
        obj.translate(translation);

        CHECK( obj.getModel() == model );
    }
}

TEST_CASE( "DimensionalObject::rotate()" ) {

    SECTION( "Should rotate the model into given direction" ) {
        DimensionalObject obj("tests/assets/cube.obj");
        glm::mat4 model;
        glm::vec3 axis(1, 0, 0); // Rotate about X -axis
        float angle = 2.0f; // Rotate 2 radians

        CHECK( obj.getModel() == model );

        model = glm::rotate(model, angle, axis);
        obj.rotate(angle, axis);

        CHECK( obj.getModel() == model );
    }
}

