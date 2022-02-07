// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //PWM

    //DIO

    // CAN
    public static final int LEFTMASTERPORT = 1;
    public static final int LEFTSLAVEPORT = 2;
    public static final int RIGHTMASTERPORT = 3;
    public static final int RIGHTSLAVEPORT = 4;

    //USB
    public static final int DRIVERSTICKPORT = 0;
    public static final int kelevatorRaise = 5;
    public static final int kelevatorLower= 3;
    // MISC CONSTANTS
    public static final double COUNTSPERREVOLUTION = 1440.0;
}
