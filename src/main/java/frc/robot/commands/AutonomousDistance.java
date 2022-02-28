// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Tebo;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonomousDistance extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous Drive based on distance. This will drive out for a specified distance,
   * turn around and drive back.
   *
   * @param drivetrain The drivetrain subsystem on which this command will run
   */
  public AutonomousDistance(Drivetrain drivetrain, Tebo _tebo) {
    addCommands(
        new DriveDistance(-0.4, 94, drivetrain),
        new ShooterAuto(_tebo)); 
       // new TurnDegrees(0.5, 180, drivetrain));
        //new DriveDistance(-0.5, 10, drivetrain),
        //new TurnDegrees(0.5, 180, drivetrain));
  }
}
