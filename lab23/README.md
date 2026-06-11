# Lab23: Android Native Security & JNI Demo

This project demonstrates the integration of Native code (C++) with an Android application using JNI (Java Native Interface) to implement basic security features and performance-oriented calculations.

## Features

### 1. Anti-Debugging and Anti-Instrumentation
The application employs several techniques at the native level to detect if it is being monitored or tampered with:
- **Ptrace Check**: Attempts to call `ptrace(PTRACE_TRACEME)` to detect if a debugger is already attached to the process.
- **Memory Map Analysis**: Scans `/proc/self/maps` for suspicious library signatures associated with common reverse engineering and instrumentation tools such as:
    - Frida
    - Xposed
    - Magisk
    - GDB Server

### 2. Native Calculations
- **Factorial Implementation**: A high-performance factorial calculation implemented in C++ and exposed to Java via JNI.

### 3. Dynamic UI Response
The application UI dynamically adapts based on the security status:
- **Secure State**: Displays a "Hello" message from JNI and the result of the native factorial calculation.
- **Suspect State**: If debugging or instrumentation is detected, the app restricts access to sensitive native functions and updates the UI with a warning.

## Project Structure

- `app/src/main/java/com/example/lab23/MainActivity.java`: Main entry point handling the UI logic and JNI function declarations.
- `app/src/main/cpp/native-lib.cpp`: C++ source file containing the implementation of security checks and mathematical functions.
- `app/src/main/res/layout/activity_main.xml`: Application layout.

## How it Works

1. On startup, `MainActivity` loads the `native-lib` library.
2. It calls `isDebugDetected()` via JNI.
3. The native code performs `ptrace` and memory map scans.
4. If either check fails, `isDebugDetected()` returns `true`.
5. The Java layer receives this status and either proceeds with normal execution or blocks sensitive operations.

## Security Disclaimer
This project is for educational purposes. While these techniques increase the effort required for reverse engineering, they are not a substitute for comprehensive application hardening.