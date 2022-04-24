# COMP3320

Computer graphics course project.

* Jonathan Godley **C3188072**
* Samuel Dolbel **C3130069**
* Perttu Karna **C3312863**
* Lasse Linkola **C3313821**


## Details

This project is a OpenGL program visualizing extraterrestial life. It is implemented in C++11 and supports Windows, OS X and Linux environments.

### Required tools/frameworks

For building on **Linux** [CMake][cmake-site] and `build-essential` package are required.

For buildig on **OS X**, `clang`/ `g++` compiler has to be installed. Following frameworks are also required: `CoreVideo`, `OpenGL`, `IOKit`, `Cocoa`, `Carbon` and `CoreFoundation`.

For building on **Windows**, VisualStudio with C++ tools are required. 

### External libraries

External libraries used in this projects are listed below. Please note that if the library is not marked supported on your operating system, you'll have to install it on your machine globally.


| Library               | Description                  | Support Win | Support OS X | Support Linux |
|-----------------------|------------------------------|:-----------:|:------------:|:-------------:|
| [Assimp][assimp-site] | Loading models               | x           |              |               |
| [glm][glm-site]       | OpenGL mathematics           | x           | x            | x             |
| [GLEW][glew-site]     | OpenGL extension detection   | x           |              |               |
| [GLFW][glfw-site]     | Window- and context creation | x           |              |               |
| [SOIL][soil-site]     | Loading textures             | x           |              |               |
| [Catch2][catch-site]  | Unit testing                 | x           | x            | x             |

### Project structure

At the moment, all source code can be found from [GLFW_EXAMPLE](GLFW_EXAMPLE) -directory. Functionality has been separated into smaller components (classes) at [lib](GLFW_EXAMPLE/lib) -directory. Unit tests are located at [tests](GLFW_EXAMPLE/tests) -directory.

### Known Limitations

* On Windows machines, the render window needs to be fullscreen, otherwise the camera will move randomly.

### Running the program

**NOTE**: Both the main program and unit tests should be run from the [GLFW_EXAMPLE](GLFW_EXAMPLE) -directory (loading assets rely on that).

The program main function is in the [main.cpp](GLFW_EXAMPLE/main.cpp) -file.
There is a secondary mainSkybox.cpp file, in the same location, that shows a functioning Skybox, however we were unable to combine the two without error before the submission date.

**Running on OS X**
```
cd GLFW_EXAMPLE && sh scripts/run.sh
```

**Running on Linux**
```
cd GLFW_EXAMPLE
cmake .
make
./comp3320
```

**Running on Windows** via Visual Studio: open the [GLFW_EXAMPLE.sln](GLFW_EXAMPLE.sln) -solution file.

### Running tests

The main function for unit tests is in the [test.cpp](GLFW_EXAMPLE/tests/test.cpp) -file.

**Running on OS X**
```
cd GLFW_EXAMPLE
sh scripts/test.sh
```

**Running on Linux**
```
cd GLFW_EXAMPLE
cmake .
make
./comp3320
```

**Running on Windows** via Visual Studio open the [GLFW_EXAMPLE.sln](GLFW_EXAMPLE.sln) -solution file.


[assimp-site]:http://www.assimp.org/
[glm-site]:https://glm.g-truc.net/0.9.9/index.html
[glew-site]:http://glew.sourceforge.net/
[glfw-site]:https://www.glfw.org/
[soil-site]:http://lonesock.net/soil.html
[catch-site]:https://github.com/catchorg/Catch2
[cmake-site]:https://cmake.org/