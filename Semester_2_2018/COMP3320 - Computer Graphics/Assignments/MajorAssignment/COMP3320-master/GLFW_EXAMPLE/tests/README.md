# Project unit tests

Unit tests are written for [Catch2][catch-source] framework, which is a header-only assertion library. It is included in [catch.hpp](catch.hpp) -file.

## Running tests

Run tests via [test.cpp](test.cpp) -file. Every `.cpp` file has to be linked to the compiler while running (note that catch.hpp doesn't need linking). See [scripts](../scripts) -folder for examples on OS X.

## Writing tests

Include [catch.hpp](catch.hpp) -file in every unit test file you write. Please note that `CATCH_CONFIG_RUNNER` definition has to be done only once (currently at [test.cpp](test.cpp)).

Refer to the [official Catch2 documentation][catch-documentation] for assertions, macros and other stuff.

[catch-documentation]:https://github.com/catchorg/Catch2/blob/master/docs/Readme.md#top
[catch-source]:https://github.com/catchorg/Catch2