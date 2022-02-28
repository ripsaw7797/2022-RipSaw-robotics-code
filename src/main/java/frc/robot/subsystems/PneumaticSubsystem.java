// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticSubsystem extends SubsystemBase {
  
  /** Creates new PneumaticSubsystems. */
  private final DoubleSolenoid m_doubleSolenoid =
    new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 6);

  private final DoubleSolenoid m_anotherDoubleSolenoid =
    new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  public void ExtendPneumatics() {
   System.out.println("ExtendPneumatics");
    // Drop the intake system into position
    m_doubleSolenoid.set(kForward);
  }
  
  public void RetractPneumatics() {
   System.out.println("RetractPneumatics");
    // Bring the intake system into the stowed position 
    m_doubleSolenoid.set(kReverse);
 }

  public void InitPneumatics() {
   System.out.println("InitPneumatics");
    // Initialize the solenoid to toggle when one button is pushed
    m_anotherDoubleSolenoid.set(kReverse);

    m_doubleSolenoid.set(kOff);
  } 
  
  public void Toggle() {
   System.out.println("Toggle");
    // Toggle Solenoid when one button is pushed
    m_anotherDoubleSolenoid.toggle();
 }     
}
