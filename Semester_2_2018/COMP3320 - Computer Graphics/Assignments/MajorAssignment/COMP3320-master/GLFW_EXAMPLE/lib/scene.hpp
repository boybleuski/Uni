/**
 * @file scene.hpp
 * A file describing the Scene class API.
 * @data 13.09.2018
 * @see Scene
 */

#ifndef SCENE_HPP
#define SCENE_HPP

#include <vector>
#include <string>
#include "dimensional_object.hpp"
#include "shader_program.hpp"

class Scene {
    /**
     * Pointers to all objects in the scene are stored here.
     */
    std::vector<shared_dim_obj> objects;

    /**
     * Pointers to all terrains in the scene.
     */
    std::vector<shared_dim_obj> terrains;

    /**
     * Pointer to the object shader program.
     */
    ShaderProgram* objectShader;

    /**
     * Pointer to the terrain shader program.
     */
    ShaderProgram* terrainShader;

    /**
     * Flag for terrains
     */
    bool useTerrains;

    /**
     * Flag for objects
     */
    bool useObjects;

    public:

        /**
         * Default constructor for Scene.
         */
        Scene() {};

        /**
         * Add object shader.
         */
        void addObjectShader(ShaderProgram& program);

        /**
         * Add terrain shader.
         */
        void addTerrainShader(ShaderProgram& program);

        /**
         * Add a pointer of an object to the scene.
         * @param obj - The object to be added
         */
        void addObject(const DimensionalObject& obj);

        /**
         * Add a pointer of a terrain to the scene.
         * @param obj - The object to be added
         */
        void addTerrain(const DimensionalObject& terrain);

        /**
         * Get objects in the scene.
         * @return A vector containing all objects in the scene.
         */
        std::vector<shared_dim_obj> getObjects() const;

        /**
         * Get terrains in the scene.
         * @return A vector containing all terrains in the scene.
         */
        std::vector<shared_dim_obj> getTerrains() const;

         /**
         * Get the data of all terrains and objects in the scene in
         * a single vector and maintained order.
         * @return A vector containing all object data in the scene.
         */
        std::vector<GLfloat> getData() const;

        /**
         * Get the data of all objects in the scene in a single vector
         * in maintained order.
         * @return A vector containing all object data in the scene.
         */
        std::vector<GLfloat> getObjectData() const;

        /**
         * Get the data of all terrains in the scene in a single vector
         * in maintained order.
         * @return A vector containing all terrain data in the scene.
         */
        std::vector<GLfloat> getTerrainData() const;

        /**
         * Get an individual object at given index.
         * @param index - The index of object
         * @return The object.
         * @throws std::out_of_range - The index is not within bounds of the container.
         */
        shared_dim_obj getObject(const int index) const;

        /**
         * Get a individual terrain at given index.
         * @param index - The index of terrain
         * @return The terrain.
         * @throws std::out_of_range - The index is not within bounds of the container.
         */
        shared_dim_obj getTerrain(const int index) const;

        /**
         * Get a pointer to the object shader.
         * @return A shared pointer to the object shader.
         */
        ShaderProgram* getObjectShader() const;

        /**
         * Get a pointer to the terrain shader.
         * @return A shared pointer to the terrain shader.
         */
        ShaderProgram* getTerrainShader() const;

        /**
         * Render objects and terrains into the scene.
         * @param uniModelName - Name of the model uniform
         */
        void render(const std::string uniModelName) const;

        /**
         * Render objects and terrains into the scene.
         * @param uniModel - A refernce to the model uniform
         *
        void render(const GLint& uniModel) const;
        */
        /**
         * Render objects with given shader program.
         * @param program - A reference to the program
         * @param uniModelName - Name of the model uniform
         * @param offset - A reference to the offset
         */
        void renderObjects(const GLuint& program, const std::string uniModelName, long& offset) const;

        /**
         * Render objects with given shader program.
         * @param program - A reference to the shader program
         * @param uniModel - A refernce to the model uniform
         * @param offset - A reference to the offset
         */
        void renderObjects(const GLuint& program, const GLint& uniModel, long& offset) const;

        /**
         * Render objects with scene's own object shader.
         * @param uniModelName - Name of the model uniform
         * @param offset - A reference to the offset
         */
        void renderObjects(const std::string uniModelName, long& offset) const;

        /**
         * Render objects with scene's own object shader.
         * @param program - A reference to the shader program
         * @param uniModel - A refernce to the model uniform
         */
        void renderObjects(const GLint& uniModel, long& offset) const;

        /**
         * Render terrains into given program.
         * @param program - The shader program
         * @param uniModelName - Name of the model uniform
         */
        void renderTerrains(const GLuint& program, const std::string uniModelName, long& offset) const;

        /**
         * Render terrains into given program.
         * @param program - A reference to the shader program
         * @param uniModel - A refernce to the model uniform
         */
        void renderTerrains(const GLuint& program, const GLint& uniModel, long& offset) const;

        /**
         * Render terrains into given program with scene's own terrain shader.
         * @param uniModelName - Name of the model uniform
         * @param offset - A reference to the offset
         */
        void renderTerrains(const std::string uniModelName, long& offset) const;

        /**
         * Render terrains into given program with scene's own terrain shader.
         * @param uniModel - A refernce to the model uniform
         * @param offset - A reference to the offset
         */
        void renderTerrains(const GLint& uniModel, long& offset) const;

        /**
         * Destructor for Scene.
         */
        virtual ~Scene();
};

/**
 * Example code for usage at this point:

    DimensionalObject cube("assets/obj/cube.obj", "assets/img/kitten.png");
    DimensionalObject cube2("assets/obj/cube.obj", "assets/img/puppy.png");
    glm::mat4 tmp;
    cube2.translate(glm::vec3(1, 0, 0));
    Scene scene;
    scene.addObject(cube);
    scene.addObject(cube2);

    try {
        for (auto& obj : scene.getObjects())
        {
            obj->load();
        }
    } catch (std::invalid_argument e) {
        std::cout << e.what() << std::endl;
        return 1;
    }
    // ....
    // Load shaders, enable attrib arrays etc...
    // ...

    // Load scene into buffer
    auto data = scene.getData();
    glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat) * data.size(), data.data(), GL_STATIC_DRAW);
    long frames = 0; // Count of frames
    do
    {

        double frame_time = (double) (clock()-start) / double(CLOCKS_PER_SEC);
        frames++;

        //Clear color buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

         // create and load view matrix
        glm::mat4 view = glm::lookAt(
                glm::vec3(2.2f, 2.2f, 2.2f), //Camera position
                glm::vec3(0.0f, 0.0f, 0.0f), //Camera view target
                glm::vec3(0.0f, 1.0f, 0.0f)  //Camera up vector which is usually z
                );

        GLint uniView = glGetUniformLocation(shaderProgram, "view");
        glUniformMatrix4fv(uniView, 1, GL_FALSE, glm::value_ptr(view));

        // create and load projection matrix
        glm::mat4 proj = glm::perspective(45.0f,            //VERTICAL FOV
                float(window_width) / float(window_height), //aspect ratio
                1.0f,                                       //near plane distance (min z)
                10.0f                                       //Far plane distance (max z)
                );
        GLint uniProj = glGetUniformLocation(shaderProgram, "proj");
        glUniformMatrix4fv(uniProj, 1, GL_FALSE, glm::value_ptr(proj));

        glm::vec4 light_position = view * glm::rotate(glm::mat4 (), 360 * float(frame_time) / period, glm::vec3(0.0f, 0.0f, 1.0f))* glm::vec4(1,0,1.3,1.0);
        GLint uniLightPos = glGetUniformLocation(shaderProgram, "light_position");
        glUniform4fv(uniLightPos, 1, glm::value_ptr(light_position));

        glm::vec4 light_colour(1,1,1,1);
        GLint uniLightCol = glGetUniformLocation(shaderProgram, "light_colour");
        glUniform4fv(uniLightCol, 1, glm::value_ptr(light_colour));
        // Count objects
        int i = 0;
        long offset = 0;
        for (auto& obj : scene.getObjects())
        {
            if (i > 0) // Rotate every object except the first one
            {
                obj->rotate(-360 * float(frame_time) / (10 * period * frames), glm::vec3(0.0f, 0.0f, 1.0f));
            }
            obj->render(shaderProgram, "model", offset);
            i++;
        }

        //Swap buffers
        glfwSwapBuffers(window);
        //Get and organize events, like keyboard and mouse input, window resizing, etc...
        glfwPollEvents();

    } //Check if the ESC key had been pressed or if the window had been closed
    while (!glfwWindowShouldClose(window));

 */

#endif /* SCENE_HPP */