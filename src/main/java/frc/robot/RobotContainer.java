// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Harambe;
import frc.robot.subsystems.Staircase;
import frc.robot.commands.AutoDoNothing;
import frc.robot.commands.AutonomousDistance;
import frc.robot.commands.AutonomousTime;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems
  private final Harambe m_Harambe = new Harambe ();
  private final  Drivetrain m_drivetrain = new Drivetrain ();
  private final  Staircase m_Staircase = new Staircase ();

  // The robot's commands
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  // Sticks
  private final XboxController m_driverstick = new XboxController(Constants.DRIVERSTICKPORT);
  private final Joystick m_operatorstick = new Joystick(Constants.OPERATORSTICKPORT);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
	
	  // Initialize drivetrain
	  m_drivetrain.drivetainInit();

    // Set default Teleop command
    m_drivetrain.setDefaultCommand(
      new RunCommand(() -> m_drivetrain.manualDrive(-m_driverstick.getLeftY(), m_driverstick.getRightX()), m_drivetrain));
       
    // Setup autonomous select commands
    m_chooser = new SendableChooser<>();
    m_chooser.setDefaultOption("Autonomous Time", new AutonomousTime(m_drivetrain));
    m_chooser.addOption("Autonomous Distance", new AutonomousDistance(m_drivetrain));
    m_chooser.addOption("Do nothing", new AutoDoNothing());
    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_operatorstick, Constants.OPHarambeStrong).whenPressed(
      new InstantCommand(m_Harambe::raise, m_Harambe)
    ).whenReleased(
      new InstantCommand(m_Harambe::stop, m_Harambe)
    );

    new JoystickButton(m_operatorstick, Constants.OPHarambeWeak).whenPressed(
      new InstantCommand(m_Harambe::lower, m_Harambe)
    ).whenReleased(
      new InstantCommand(m_Harambe::stop, m_Harambe)
    );

    new JoystickButton(m_operatorstick, Constants.OPStaircaseUp).whenPressed(
      new InstantCommand(m_Staircase::lower, m_Staircase)
    ).whenReleased(
      new InstantCommand(m_Staircase::stop, m_Staircase)
    );

    new JoystickButton(m_operatorstick, Constants.OPStaricasDown).whenPressed(
      new InstantCommand(m_Staircase::lower, m_Harambe)
    ).whenReleased(
      new InstantCommand(m_Staircase::stop, m_Staircase)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    m_autonomousCommand = m_chooser.getSelected();
    return m_autonomousCommand;
  }
}
