# !/bin/bash
# build.sh

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

g++ -std=c++11 \
  -lglfw -lglew -lSOIL -lassimp \
  -framework CoreVideo \
  -framework OpenGL \
  -framework IOKit \
  -framework Cocoa \
  -framework Carbon \
  -framework CoreFoundation \
  -w \
  ${BUILD_FILES} \
  -o ${OUTPUT}

if [ $? -ne 0 ]; then
  printf "${RED}Building source failed${NC}\n";
  exit 1
fi
