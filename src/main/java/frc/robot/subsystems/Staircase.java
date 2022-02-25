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

public class Staircase extends SubsystemBase {

  private WPI_VictorSPX StaircaseVictorSPX = new WPI_VictorSPX(Constants.StaircasePort);
  
  /**
   * Creates a new ElevatorSubsystem.
   */
  
  public Staircase() {

  }
  public void raise(){
    StaircaseVictorSPX.set(ControlMode.PercentOutput, 0.8);
  }
  public void lower(){
    StaircaseVictorSPX.set(ControlMode.PercentOutput, -0.8);
  }
  public void stop(){
    StaircaseVictorSPX.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}