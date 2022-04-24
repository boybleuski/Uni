
#include "camera.hpp"
#include <iostream>

#include "../glm/gtx/transform.hpp"


Camera::Camera(glm::vec3 pos) {
    viewDirection = glm::vec3(1.0f, 0.0f, 0.0f);
    position = pos;
}


void Camera::mouseUpdate(const glm::vec2 &newMousePosition) {

    glm::vec2 mouseDelta = newMousePosition - oldMousePosition;
    viewDirection = 
        glm::mat3(glm::rotate(mouseDelta.y, glm::vec3(0, 0, 1.0f))) *
        glm::mat3(glm::rotate(-mouseDelta.x, UP)) *
        viewDirection;

    oldMousePosition = newMousePosition;
}
glm::mat4 Camera::getWorldToViewMatrix() const{
    return glm::lookAt(position, position + viewDirection, UP);
}

const float MOVEMENT_SPEED =  0.1f;

void Camera::moveForward() {
    position += MOVEMENT_SPEED * viewDirection;
}
void Camera::moveBackward() {

    position += -MOVEMENT_SPEED * viewDirection;
}
void Camera::strafeRight() {
    position += glm::cross(viewDirection, UP) * MOVEMENT_SPEED;
}
void Camera::strafeLeft() {
    position += glm::cross(viewDirection, UP) * -MOVEMENT_SPEED;
}
void Camera::moveUp(){
    position += UP * MOVEMENT_SPEED;
}
void Camera::moveDown() {

    position += UP * -MOVEMENT_SPEED;
}
