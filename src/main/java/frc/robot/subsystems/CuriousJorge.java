// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class CuriousJorge extends SubsystemBase {

  private WPI_VictorSPX curiousJorgeVictorSPX = new WPI_VictorSPX(Constants.CuriousJorge);
  
  /**
   * Creates a new ElevatorSubsystem.
   */
  
  public CuriousJorge() {

  }
  public void in(){
    curiousJorgeVictorSPX.set(ControlMode.PercentOutput, 0.6);
  }
  public void out(){
    curiousJorgeVictorSPX.set(ControlMode.PercentOutput, -0.6);
  }
  public void stop(){
    curiousJorgeVictorSPX.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}