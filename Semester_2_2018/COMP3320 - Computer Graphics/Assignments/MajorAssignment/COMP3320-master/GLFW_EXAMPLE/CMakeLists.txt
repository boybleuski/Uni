cmake_minimum_required (VERSION 2.6)
project (comp3320)
add_executable(comp3320 
    main.cpp
    lib/camera.cpp
    lib/camera.hpp
    lib/scene.cpp
    lib/scene.hpp
    lib/shader_program.cpp
    lib/shader_program.hpp
    lib/texture.cpp
    lib/texture.hpp
    lib/window.cpp
    lib/window.hpp
    lib/dimensional_object.cpp
    lib/dimensional_object.hpp
    )
target_link_libraries(comp3320 -lglfw -lSOIL -lassimp -lGLEW -lGL)
