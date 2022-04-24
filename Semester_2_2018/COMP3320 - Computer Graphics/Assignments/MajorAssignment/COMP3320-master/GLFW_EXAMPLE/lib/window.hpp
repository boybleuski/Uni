/**
 * @file window.hpp
 * @date 14.9.2018
 * A file describing the API of Window class.
 * @see Window
 */
#ifndef WINDOW_HPP
#define WINDOW_HPP

#include <GLFW/glfw3.h>
#include <string>
#include "camera.hpp"
#include <iostream>
class Window {

    /**
     * Window height in pixels.
     */
    const int height;

    /**
     * Window width in pixels.
     */
    const int width;

    /**
     * A pointer to the underlying window object.
     */
    GLFWwindow* window;

    Camera camera;
    /**
     * key_callback function for opengl. Used to add WindowUserPointer so that Window can 
     * be accessed in the onKey
     */
    static void key_callback(GLFWwindow* window, int key, int scancode, int action, int mods) 
    {
        Window* win = (Window *)glfwGetWindowUserPointer(window);
        win->onKey(key, scancode, action, mods);
    }

    public:

        /**
         * Default for Window class (1920 / 1080).
         */
        Window();

        /**
         * Screen ratio constructor for Window class.
         */
        Window(const int width, const int height);

        /**
         * Screen ratio and name constructor for Window class.
         */
        Window(const int width, const int height, const std::string& name);

        /**
         * Get window height.
         * @return Height of the window as pixels.
         */
        int getHeight() const;

        /**
         * Get window width.
         * @return Width of the window as pixels.
         */
        int getWidth() const;

        /**
         * Get window screen ratio.
         * @return The window screen ratio.
         */
        float getRatio() const;

        /**
         * Get a pointer to the window element.
         * @return A pointer to the underlying window element.
         */
        GLFWwindow* get() const;

        /**
         * Get pointer to Camera object
         * @return pointer to Camera object
         */
        Camera* getCamera();
        /**
         * Set this window as current context in calling thread.
         */
        void makeCurrentContext() const;

        /**
         * key_callback function
         */
        void onKey(int key, int scancode, int actions, int mods);
        /**
         * Handle wasd keys camera movement
         */
        void handleMovement();

        /**
         * Destructor for Window class.
         */
        virtual ~Window();
};

#endif /* WINDOW_HPP */
