# JNI Demo Project - Lab22

This project demonstrates the integration of Native C++ code with an Android application using the Java Native Interface (JNI). It showcases several common use cases including string manipulation, mathematical calculations, and array processing.

## Features

- **Hello JNI**: A simple "Hello World" style call from Java to C++.
- **Factorial Calculation**: Computes the factorial of a number in native code with error handling for negative inputs and overflow.
- **String Reversal**: Reverses a string passed from Java and returns the result.
- **Array Summation**: Calculates the sum of an integer array in native code.

## Project Structure

- **`app/src/main/java/com/example/lab22/MainActivity.java`**: The main entry point that declares native methods and handles the UI.
- **`app/src/main/cpp/native-lib.cpp`**: C++ implementation of the native methods.
- **`app/src/main/cpp/CMakeLists.txt`**: Build configuration for the native library.
- **`app/src/main/res/layout/activity_main.xml`**: Layout containing TextViews to display the results of JNI calls.

## How it works

1. **Library Loading**: The native library `native-lib` is loaded using `System.loadLibrary` in a static block in `MainActivity`.
2. **Method Declaration**: Native methods are declared in Java with the `native` keyword.
3. **C++ Implementation**: Corresponding functions are implemented in C++ using the JNI naming convention (`Java_package_name_ActivityName_methodName`).
4. **Data Passing**: The project demonstrates passing primitives (int), objects (String), and arrays (int[]) between Java and C++.

## Logging

The native code uses Android NDK logging (`<android/log.h>`) to output debug information to Logcat with the tag `LAB22_DEMO`.
