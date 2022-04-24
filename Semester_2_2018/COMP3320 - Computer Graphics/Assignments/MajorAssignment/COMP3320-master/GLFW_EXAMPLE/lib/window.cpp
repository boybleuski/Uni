/**
 * @file window.cpp
 * @date 14.9.2018
 * A file describing the implementation of Window class.
 * @see Window
 */

#include "window.hpp"
#include "camera.hpp"

Window::Window():
    height(1080),
    width(1920),
    window(glfwCreateWindow(1920, 1080, "Window", glfwGetPrimaryMonitor(), NULL)),
    camera(glm::vec3(2.2f, 2.2f, 2.2f))
{
    glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    glfwSetWindowUserPointer(window, this);
    glfwSetKeyCallback(window, key_callback);
}

Window::Window(const int width, const int height):
    width(width),
    height(height),
    window(glfwCreateWindow(width, height, "Window", glfwGetPrimaryMonitor(), NULL)),
    camera(glm::vec3(2.2f, 2.2f, 2.2f))
{
    glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    glfwSetWindowUserPointer(window, this);
    glfwSetKeyCallback(window, key_callback);
}

Window::Window(const int width, const int height, const std::string& name):
    width(width),
    height(height),
    window(glfwCreateWindow(width, height, name.c_str(), glfwGetPrimaryMonitor(), NULL)),
    camera(glm::vec3(2.2f, 2.2f, 2.2f))
{
    glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    glfwSetWindowUserPointer(window, this);
    glfwSetKeyCallback(window, key_callback);
}

int Window::getHeight() const
{
    return height;
}

int Window::getWidth() const
{
    return width;
}

GLFWwindow* Window::get() const
{
    return window;
}
Camera* Window::getCamera()
{
    return &camera;
}

float Window::getRatio() const
{
    return (float) width / (float) height;
}

void Window::makeCurrentContext() const
{
    glfwMakeContextCurrent(window);
}

void Window::onKey(int key, int scancode, int action, int mods) {

    if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
        glfwSetWindowShouldClose(window, GL_TRUE);
}
void Window::handleMovement() {
    int up = glfwGetKey(window, GLFW_KEY_UP);
    int down = glfwGetKey(window, GLFW_KEY_DOWN);
    int left = glfwGetKey(window, GLFW_KEY_LEFT);
    int right = glfwGetKey(window, GLFW_KEY_RIGHT);

    if (up == GLFW_PRESS)
        camera.moveForward();
    if (down == GLFW_PRESS)
        camera.moveBackward();
    if (right == GLFW_PRESS)
        camera.strafeRight();
    if (left == GLFW_PRESS)
        camera.strafeLeft();
}

Window::~Window()
{
    glfwDestroyWindow(window);
}
