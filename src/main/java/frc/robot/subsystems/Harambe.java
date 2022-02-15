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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Harambe extends SubsystemBase {

  private WPI_VictorSPX HarambeVictorSPX = new WPI_VictorSPX(Constants.HarambePort);
  
  /**
   * Creates a new ElevatorSubsystem.
   */
  
  public Harambe() {

  }
  public void raise(){
    HarambeVictorSPX.set(ControlMode.PercentOutput, 0.8);
  }
  public void lower(){
    HarambeVictorSPX.set(ControlMode.PercentOutput, -0.8);
  }
  public void stop(){
    HarambeVictorSPX.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}