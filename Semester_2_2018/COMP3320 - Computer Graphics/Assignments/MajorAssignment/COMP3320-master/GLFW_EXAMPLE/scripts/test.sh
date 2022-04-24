# !/bin/bash
# test.sh


export BUILD_FILES="tests/test.cpp tests/texture_tests.cpp lib/texture.cpp tests/dimensional_object_tests.cpp lib/dimensional_object.cpp tests/scene_tests.cpp lib/scene.cpp tests/window_tests.cpp lib/window.cpp tests/shader_program_tests.cpp lib/shader_program.cpp tests/cubemap_texture_tests.cpp lib/cubemap_texture.cpp lib/camera.cpp"

[[ $1 != "-debug" ]] && BUILD_SCRIPT="build.sh" || BUILD_SCRIPT="build_debug.sh"
[[ $1 != "-debug" ]] && OUTPUT="out/a.out" || OUTPUT="out/a.debug.out"
export OUTPUT=$OUTPUT

/bin/bash scripts/$BUILD_SCRIPT


if [ $? -ne 0 ]; then
    exit $?
fi

if [[ $1 != "-debug" ]]; then
    $OUTPUT
else
    valgrind --leak-check=full $OUTPUT
fi
