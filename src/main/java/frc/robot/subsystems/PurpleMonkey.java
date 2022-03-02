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

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PurpleMonkey extends SubsystemBase {

  private Spark PurpleMonkeySpark = new Spark(Constants.PurpleMonkeyPort);
  
  /**
   * Creates a new ElevatorSubsystem.
   */
  
  public PurpleMonkey() {

  }
  public void strong(){
    PurpleMonkeySpark.set(0.1);
  }

  public void weak(){
    PurpleMonkeySpark.set(-0.1);
  }

  public void stop(){
    PurpleMonkeySpark.set(0);
  }


    // This method will be called once per scheduler run
  }
