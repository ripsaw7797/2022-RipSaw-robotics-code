
/**
 * Phoenix Software License Agreement
 *
 * Copyright (C) Cross The Road Electronics.  All rights
 * reserved.
 * 
 * Cross The Road Electronics (CTRE) licenses to you the right to 
 * use, publish, and distribute copies of CRF (Cross The Road) firmware files (*.crf) and 
 * Phoenix Software API Libraries ONLY when in use with CTR Electronics hardware products
 * as well as the FRC roboRIO when in use in FRC Competition.
 * 
 * THE SOFTWARE AND DOCUMENTATION ARE PROVIDED "AS IS" WITHOUT
 * WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING WITHOUT
 * LIMITATION, ANY WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT SHALL
 * CROSS THE ROAD ELECTRONICS BE LIABLE FOR ANY INCIDENTAL, SPECIAL, 
 * INDIRECT OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF
 * PROCUREMENT OF SUBSTITUTE GOODS, TECHNOLOGY OR SERVICES, ANY CLAIMS
 * BY THIRD PARTIES (INCLUDING BUT NOT LIMITED TO ANY DEFENSE
 * THEREOF), ANY CLAIMS FOR INDEMNITY OR CONTRIBUTION, OR OTHER
 * SIMILAR COSTS, WHETHER ASSERTED ON THE BASIS OF CONTRACT, TORT
 * (INCLUDING NEGLIGENCE), BREACH OF WARRANTY, OR OTHERWISE
 */

/**
 * Enable robot and slowly drive forward.
 * [1] If DS reports errors, adjust CAN IDs and firmware update.
 * [2] If motors are spinning incorrectly, first check gamepad (hold down btn1)
 * [3] If motors are still spinning incorrectly, correct motor inverts.
 * [4] Now that motors are driving correctly, check sensor phase.  If sensor is out of phase, adjust sensor phase.
 * [4] Is only necessary if you have sensors.
 */
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  // Encoders
  private static final double kCountsPerRevolution = 1440.0;
  private static final double kWheelDiameterInch = 2.75591; // 70 mm
  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);
  
  // VictorSPX motor controllers
	WPI_VictorSPX _leftFront = new WPI_VictorSPX(Constants.LEFTMASTERPORT);
  WPI_VictorSPX _leftFollower = new WPI_VictorSPX(Constants.LEFTSLAVEPORT);
  WPI_VictorSPX _rghtFront = new WPI_VictorSPX(Constants.RIGHTMASTERPORT);
  WPI_VictorSPX _rghtFollower = new WPI_VictorSPX(Constants.RIGHTSLAVEPORT);


  DifferentialDrive _diffDrive = new DifferentialDrive(_leftFront, _rghtFront);

  public void manualDrive(double move, double turn) {

    /* deadband gamepad 10% */
      if (Math.abs(move) < 0.10) {
        move = 0;
      }
      if (Math.abs(turn) < 0.10) {
          turn = 0;
      }

    /* drive robot */
    _diffDrive.arcadeDrive(move, turn);
  }

  // @Override
  public void drivetainInit() {
    /* factory default values */
    _rghtFront.configFactoryDefault();
    _rghtFollower.configFactoryDefault();
    _leftFront.configFactoryDefault();
    _leftFollower.configFactoryDefault();

    /* set up followers */
    _rghtFollower.follow(_rghtFront);
    _leftFollower.follow(_leftFront);

    /* [3] flip values so robot moves forward when stick-forward/LEDs-green */
    _rghtFront.setInverted(false); // !< Update this
    _leftFront.setInverted(true); // !< Update this

    /*
     * set the invert of the followers to match their respective master controllers
     */
    _rghtFollower.setInverted(InvertType.FollowMaster);
    _leftFollower.setInverted(InvertType.FollowMaster);



    
    /*
     * [4] adjust sensor phase so sensor moves positive when Talon LEDs are green
     */
    _rghtFront.setSensorPhase(true);
    _leftFront.setSensorPhase(true);

    /*
     * WPI drivetrain classes defaultly assume left and right are opposite. call
     * this so we can apply + to both sides when moving forward. DO NOT CHANGE
     */
    
  }

  public Drivetrain() {
    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();
  }

  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    _diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }


public void resetEncoders() {
  m_leftEncoder.reset();
  m_rightEncoder.reset();
}

public int getLeftEncoderCount() {
  return m_leftEncoder.get();
}

public int getRightEncoderCount() {
  return m_rightEncoder.get();
}

public double getLeftDistanceInch() {
  return m_leftEncoder.getDistance();
}

public double getRightDistanceInch() {
  return m_rightEncoder.getDistance();
}

public double getAverageDistanceInch() {
  return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
}
  }


