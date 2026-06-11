# Sensor Explorer - Lab 2.1

## Project Overview
This Android application provides a comprehensive suite for exploring and monitoring various hardware sensors available on a mobile device. It features real-time data visualization, activity recognition, and detailed sensor specifications.

## Key Features
- **Sensor Listing**: Automatically detects and lists all available sensors on the device with technical specifications (Vendor, Version, Resolution, Power consumption, etc.).
- **Dynamic Graphing**: Real-time line chart visualization for environmental and position sensors.
- **Motion Monitoring**: Tracking of Accelerometer, Gyroscope, and Gravity sensors with magnitude calculation.
- **Physical Tools**: 
    - **Step Counter**: Monitors user steps with session-based tracking (requires Activity Recognition permission).
    - **Compass**: High-precision orientation tracking using the combination of accelerometer and magnetometer.
- **Activity Recognition**: Heuristic-based movement classification to detect Jumping, Walking, and device stability.

## Technical Details
- **Architecture**: Single Activity with multiple Fragments for modular UI management.
- **Custom Views**: `LineChartView` implementation using Android Canvas API for efficient real-time plotting.
- **Data Processing**: Low-pass filtering (Alpha-Beta) used for gravity estimation and linear acceleration extraction in activity recognition.
- **Sensor Management**: Robust handling of sensor registration/unregistration within lifecycle callbacks to optimize battery usage.
- **Simulation Mode**: Fallback simulation for environmental sensors when hardware is missing.

## Essential Files
- `MainActivity.java`: Orchestrates fragment transitions and the main sensor menu.
- `SensorsListFragment.java`: Displays the exhaustive list of device sensors.
- `SensorGraphFragment.java`: Provides a graphical interface for single-value or magnitude-based sensors.
- `MotionSensorFragment.java`: Visualizes 3-axis motion data.
- `CompassFragment.java`: Implements orientation logic.
- `StepCounterFragment.java`: Handles step counting and permissions.
- `ActivityRecognitionFragment.java`: Logic for movement pattern classification.
- `LineChartView.java`: Custom rendering engine for data plots.

## Author
Project developed by 403f2e.
