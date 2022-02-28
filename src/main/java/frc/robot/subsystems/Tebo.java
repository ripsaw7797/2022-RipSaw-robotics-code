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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;

public class Tebo extends SubsystemBase {

  private TalonFX TeboTalonFX = new TalonFX(Constants.TeboPort);
  
  public Tebo() {

  }
  public void shoot(){
    TeboTalonFX.set(ControlMode.Velocity, -.75);
  }

  public void highshooter (){
    TeboTalonFX.set(ControlMode.PercentOutput,-.65);

  }

  public void lowshooter(){
    TeboTalonFX.set(ControlMode.PercentOutput, -30);

  }

  public void stop(){
    TeboTalonFX.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}