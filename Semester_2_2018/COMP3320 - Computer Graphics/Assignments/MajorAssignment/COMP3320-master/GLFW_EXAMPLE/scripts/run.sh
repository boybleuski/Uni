# !/bin/bash
# run.sh

export BUILD_FILES="main.cpp lib/dimensional_object.cpp lib/scene.cpp lib/texture.cpp lib/window.cpp lib/shader_program.cpp lib/cubemap_texture.cpp lib/camera.cpp"

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
    valgrind --leak-check=full --show-leak-kinds=all $OUTPUT -r compact
fi
