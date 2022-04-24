#ifndef CAMERA_HPP
#define CAMERA_HPP

#include "../glm/glm.hpp"

class Camera {
    public:
        Camera(glm::vec3 position);
        void mouseUpdate(const glm::vec2 &newMousePosition);
        glm::mat4 getWorldToViewMatrix() const;

        void moveForward();
        void moveBackward();
        void strafeRight();
        void strafeLeft();
        void moveUp();
        void moveDown();
    private:
        glm::vec3 position;
        glm::vec3 viewDirection;
        const glm::vec3 UP = glm::vec3(0,1,0);
        glm::vec2 oldMousePosition;


};
#endif
