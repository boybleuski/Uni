
#ifdef _WIN32
    #include <SOIL.h>
#else
    #include <SOIL/SOIL.h>
#endif

// APIs for fetching screen size
#ifdef __APPLE__
    #include <ApplicationServices/ApplicationServices.h>
#endif

#include <GL/glew.h>

#include <iostream> //cout

#include <fstream> //fstream
#include <vector>
#include <ctime>

//Include GLFW
#include <GLFW/glfw3.h>

//Include the standard C++ headers
#include <stdio.h>
#include <stdlib.h>

//Include matrix libraries
#include "glm/glm.hpp"
#include "glm/gtc/matrix_transform.hpp"
#include "glm/gtc/matrix_inverse.hpp"
#include "glm/gtc/type_ptr.hpp"

//#include <assimp/cimport.h>
#include <assimp/scene.h>
#include <assimp/postprocess.h>
#include <assimp/Importer.hpp>

#include "lib/scene.hpp"
#include "lib/dimensional_object.hpp"
#include "lib/texture.hpp"
#include "lib/window.hpp"
#include "lib/shader_program.hpp"
#include "lib/cubemap_texture.hpp"
#include "lib/camera.hpp"

// Cube for skybox (note that skybox shader requires only position)
GLfloat skyboxVertices[] = {
    // Positions (x, y z)
    -10.0f,  10.0f, -10.0f,
    -10.0f, -10.0f, -10.0f,
    10.0f, -10.0f, -10.0f,
    10.0f, -10.0f, -10.0f,
    10.0f,  10.0f, -10.0f,
    -10.0f,  10.0f, -10.0f,

    -10.0f, -10.0f,  10.0f,
    -10.0f, -10.0f, -10.0f,
    -10.0f,  10.0f, -10.0f,
    -10.0f,  10.0f, -10.0f,
    -10.0f,  10.0f,  10.0f,
    -10.0f, -10.0f,  10.0f,

    10.0f, -10.0f, -10.0f,
    10.0f, -10.0f,  10.0f,
    10.0f,  10.0f,  10.0f,
    10.0f,  10.0f,  10.0f,
    10.0f,  10.0f, -10.0f,
    10.0f, -10.0f, -10.0f,

    -10.0f, -10.0f,  10.0f,
    -10.0f,  10.0f,  10.0f,
    10.0f,  10.0f,  10.0f,
    10.0f,  10.0f,  10.0f,
    10.0f, -10.0f,  10.0f,
    -10.0f, -10.0f,  10.0f,

    -10.0f,  10.0f, -10.0f,
    10.0f,  10.0f, -10.0f,
    10.0f,  10.0f,  10.0f,
    10.0f,  10.0f,  10.0f,
    -10.0f,  10.0f,  10.0f,
    -10.0f,  10.0f, -10.0f,

    -10.0f, -10.0f, -10.0f,
    -10.0f, -10.0f,  10.0f,
    10.0f, -10.0f, -10.0f,
    10.0f, -10.0f, -10.0f,
    -10.0f, -10.0f,  10.0f,
    10.0f, -10.0f,  10.0f
};


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

//Define the key input callback
static void key_callback(GLFWwindow* window, int key, int scancode, int action, int mods)
{
    if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
        glfwSetWindowShouldClose(window, GL_TRUE);
}


int main(void)
{
    glfwSetErrorCallback(error_callback);

    if (!glfwInit()) exit(EXIT_FAILURE);

    int width = 1920, height = 1080;

    //Set the GLFW window creation hints - these are optional
    #ifdef __APPLE__
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4); //Request a specific OpenGL version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1); //Request a specific OpenGL version
        glfwWindowHint(GLFW_SAMPLES, 4); //Request 4x antialiasing
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        height = CGDisplayPixelsHigh(CGMainDisplayID());
        width = CGDisplayPixelsWide(CGMainDisplayID());
    #elif __linux__
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); //Request a specific OpenGL version
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2); //Request a specific OpenGL version
        glfwWindowHint(GLFW_SAMPLES, 4); //Request 4x antialiasing
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    #else
        // Define windows specific glfwWindowHint calls if needed
    #endif

    Window window(width, height, "Test window");

    // If the window couldn't be created
    if (!window.get())
    {
        fprintf( stderr, "Failed to open GLFW window.\n" );
        glfwTerminate();
        exit(EXIT_FAILURE);
    }
    // "Create" context
    window.makeCurrentContext();

    glewExperimental = GL_TRUE;
    GLenum err = glewInit();

    // If GLEW hasn't initialized
    if (err != GLEW_OK)
    {
        fprintf(stderr, "Error: %s\n", glewGetErrorString(err));
        return -1;
    }

    printf("OpenGL version supported by this platform (%s): \n", glGetString(GL_VERSION));

    /*********************
     * CREATE SHADERS
     * - skyboxShader for rendering skybox
     * - terrainShader for rendering terrain
     * - objectShader for rendering objects
     *********************/

    ShaderProgram skyboxShader("shaders/cubemap.vert", "shaders/cubemap.frag");
    skyboxShader.compileShaders();
    skyboxShader.attachShaders();
    glBindFragDataLocation(skyboxShader.get(), 0, "outColor");
    skyboxShader.link();

    ShaderProgram terrainShader("shaders/terrain.vert", "shaders/shader.frag");
    terrainShader.compileShaders();
    terrainShader.attachShaders();
    glBindFragDataLocation(terrainShader.get(), 0, "outColor");
    terrainShader.link();

    ShaderProgram objectShader("shaders/shader.vert", "shaders/shader.frag");
    objectShader.compileShaders();
    objectShader.attachShaders();
    glBindFragDataLocation(objectShader.get(), 0, "outColor");
    objectShader.link();

    /*******************
     * CREATE MESH AND TEXTURES
     * - Create object references
     * - Create Scene and add mesh into it
     * - Load mesh
     *******************/

    Texture puppy("assets/img/puppy.png", GL_CLAMP_TO_EDGE);
    Texture grass("assets/img/grass.jpg", GL_REPEAT);

    DimensionalObject cube("assets/obj/cube.obj", puppy);
    DimensionalObject plane("assets/obj/plane.obj", grass);
    
    // Lower the plane
    plane.translate(glm::vec3(0, -1, 0));

    Scene scene;
    scene.addTerrain(plane);
    scene.addObject(cube);

    try {
        for (auto& obj : scene.getObjects())
        {
            obj->load();
        }
        for (auto& terr : scene.getTerrains())
        {
            terr->load();
        }
    } catch (std::invalid_argument e) {
        std::cout << e.what() << std::endl;
        return 1;
    }

    std::vector<std::string> faces;
    faces.push_back("assets/img/skybox/right.jpg");
	faces.push_back("assets/img/skybox/left.jpg");
	faces.push_back("assets/img/skybox/top.jpg");
	faces.push_back("assets/img/skybox/bottom.jpg");
	faces.push_back("assets/img/skybox/front.jpg");
	faces.push_back("assets/img/skybox/back.jpg");
    // Cubemap not included in the scene at the moment
    CubemapTexture cubemap(faces);
    cubemap.load();

    /*********************************
     * CREATE VERTEX ARRAY OBJECT AND VERTEX BUFFER OBJECT
     * - VAO is containing references / status data
     * - VBO is containing actual mesh data that we'll
     *   pass to the shaders
     * - NOTE: We could use single VAO-VBO only if all our 
     * shaders used same kind of buffer data
     *********************************/
    
    GLuint sVAO, sVBO; // For skybox
    glGenVertexArrays(1, &sVAO); // Reserve VAO address
    glGenBuffers(1, &sVBO); // Reserve VBO address
    glBindVertexArray(sVAO); // Set as current VAO
    glBindBuffer(GL_ARRAY_BUFFER, sVBO); // Link to the current VAO
    glBufferData(GL_ARRAY_BUFFER, sizeof(skyboxVertices), &skyboxVertices, GL_STATIC_DRAW);
    // Enable vertex attributes (link the shape of our data in VBO)
    // Right now we have only position attribute for our skybox cube
    GLint skyPos = glGetAttribLocation(skyboxShader.get(), "position");
    glVertexAttribPointer(skyPos, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(GLfloat), (GLvoid*) 0 );
    glEnableVertexAttribArray(skyPos);
   
    GLuint vao, vbo; // For objects and terrain
    glGenVertexArrays(1, &vao);
    glBindVertexArray(vao);
    glGenBuffers(1, &vbo);
    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    // NOTE: data contains first terrain(s), then objects from the scene
    auto data = scene.getData();
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat) * data.size(), data.data(), GL_STATIC_DRAW);
    // Enable vertex attributes for terrain- and object shader
    for (int i = 0; i < 2; i++)
    {
        GLuint id = i == 0 ? terrainShader.get() : objectShader.get();
        GLint posAttrib = glGetAttribLocation(id, "position");
        glVertexAttribPointer(
            posAttrib,                  // Buffer identifier
            3,                          // How many data points to read (3)
            GL_FLOAT,                   // Data type
            GL_FALSE,                   // Whether or not to clamp values between -1,1
            11 * sizeof(float),         // Stride (byte offset between consecutive values)
            0                           // Pointer to first attribute (see below for better examples)
        );
        glEnableVertexAttribArray(posAttrib);

        GLint normalAttrib = glGetAttribLocation(id, "normal");
        glVertexAttribPointer(
            normalAttrib,
            3,                          // 3 + 3
            GL_FLOAT,
            GL_FALSE,
            11 * sizeof(float),
            (void*)(3 * sizeof(float))  // Starting from 3rd index in every vertex
        );
        glEnableVertexAttribArray(normalAttrib);


        GLint colourAttrib = glGetAttribLocation(id, "colour");
        glVertexAttribPointer(
            colourAttrib,
            3,                          // 3 + 3 + 3
            GL_FLOAT,
            GL_TRUE,
            11 * sizeof(float),
            (void*)(6 * sizeof(float)) // Starting from 6th index in every vertex
        );
        glEnableVertexAttribArray(colourAttrib);

        GLint textureAttrib = glGetAttribLocation(id, "texcoord");
        glVertexAttribPointer(
            textureAttrib,
            2,                          // 3 + 3 + 3 + 2 = 11
            GL_FLOAT,
            GL_FALSE,
            11 * sizeof(float),
            (void*)(9 * sizeof(float)) // Starting from 9th index in every vertex
        );
        glEnableVertexAttribArray(textureAttrib);
    }

    // Pass shader references into the scene
    scene.addObjectShader(objectShader);
    scene.addTerrainShader(terrainShader);

    glClearColor(0.0f, 0.0f, 1.0f, 0.0f);
    glEnable(GL_DEPTH_TEST);
    glm::vec4 light_colour(1, 1, 1, 1);
    glm::vec4 light_position(1.0f, 1.0f, 1.0f, 1.0f);
    Camera* camera = window.getCamera();

    do
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Manage camera controls
        double xpos, ypos;
        glfwGetCursorPos(window.get(), &xpos, &ypos);
        glm::vec2 newMousePos = glm::vec2(xpos, ypos);
        camera->mouseUpdate(newMousePos);
        window.handleMovement();

        glm::mat4 view = camera->getWorldToViewMatrix();
        glm::mat4 proj = glm::perspective(
            45.0f,              // Vertical FOV
            window.getRatio(),  // Aspect ratio
            0.1f,               // Near plane distance (min Z)
            1000.0f             // Far plane distance (max Z)
        );
        // Bind object data (use terrain/object data buffer)
        glBindVertexArray(vao);
        // This is needed when rendering multiple objects
        // from same vertex buffer
        long objectDataOffset = 0;
        for (int i = 0; i < 2; i++)
        {   
            // On first loop, use terrain shader since we have
            // terrain data first in buffer
            ShaderProgram* shader = i == 0 ?
                scene.getTerrainShader() : scene.getObjectShader();

            shader->use();
            GLuint id = shader->get();
            // Get uniform locations for shader
            GLint uniView = glGetUniformLocation(id, "view");
            GLint uniProj = glGetUniformLocation(id, "proj");
            GLint uniLightPos = glGetUniformLocation(id, "light_position");
            GLint uniLightCol = glGetUniformLocation(id, "light_colour");
            GLint uniModel = glGetUniformLocation(id, "model");
            // Bind uniform values for shader
            glUniformMatrix4fv(uniView, 1, GL_FALSE, glm::value_ptr(view));
            glUniformMatrix4fv(uniProj, 1, GL_FALSE, glm::value_ptr(proj));
            glUniform4fv(uniLightPos, 1, glm::value_ptr(light_position));
            glUniform4fv(uniLightCol, 1, glm::value_ptr(light_colour));

            // Again, on first loop render terrain data
            i == 0 ? scene.renderTerrains(uniModel, objectDataOffset)
                : scene.renderObjects(uniModel, objectDataOffset);
        }

        // Prepare for rendering the skybox
        glDepthFunc(GL_LEQUAL);
        glDepthMask(GL_FALSE);
        // Switch to skybox shader
        skyboxShader.use();
        // Bind view and proj matrix uniform values into the shader
        GLint uniViewSkybox = glGetUniformLocation(skyboxShader.get(), "view");
        glUniformMatrix4fv(uniViewSkybox, 1, GL_FALSE, glm::value_ptr(view));
        glUniformMatrix4fv(glGetUniformLocation(skyboxShader.get(), "proj"), 1, GL_FALSE, glm::value_ptr(proj));
        // Switch to skybox vertex buffer
        glBindVertexArray(sVAO);
        // Bind skybox texture
        cubemap.bind();
        // Finally, draw the skybox
        glDrawArrays(GL_TRIANGLES, 0, 36);
        // Set these back to default
        glDepthMask(GL_TRUE);
        glDepthFunc(GL_LESS);

        glfwSwapBuffers(window.get());
        glfwPollEvents();
    }
    while (!glfwWindowShouldClose(window.get()));

    glfwTerminate();

    exit(EXIT_SUCCESS);
}