// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.PneumaticSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutonomousDistance;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.TogglePneumatics;
import frc.robot.commands.AutonomousTime;
import frc.robot.subsystems.CuriousJorge;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.AutoDoNothing;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Staircase;
import frc.robot.subsystems.Harambe;
import frc.robot.subsystems.Tebo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  // The robot's subsystems
  private final PneumaticSubsystem m_pneumaticSubsystem = new PneumaticSubsystem();
  private final CuriousJorge m_CuriousJorge = new CuriousJorge();
  private final Drivetrain m_drivetrain = new Drivetrain ();
  private final Staircase m_Staircase = new Staircase ();
  private final Harambe m_Harambe = new Harambe ();
  private final Tebo m_Tebo = new Tebo();

  CameraServer server;
  CameraServer server2;
  // The robot's commands
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  // Sticks
  private final XboxController m_driverstick = new XboxController(Constants.DRIVERSTICKPORT);
  private final Joystick m_operatorstick = new Joystick(Constants.OPERATORSTICKPORT);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    
    m_pneumaticSubsystem.InitPneumatics();

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
    m_chooser.addOption("Autonomous Distance", new AutonomousDistance(m_drivetrain, m_Tebo));
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
      new InstantCommand(m_Harambe::strong, m_Harambe)
    ).whenReleased(
      new InstantCommand(m_Harambe::stop, m_Harambe)
    );

    new JoystickButton(m_operatorstick, Constants.OPHarambeWeak).whenPressed(
      new InstantCommand(m_Harambe::weak, m_Harambe)
    ).whenReleased(
      new InstantCommand(m_Harambe::stop, m_Harambe)
    );

    new JoystickButton(m_operatorstick, Constants.OPLaunchPadShooter).whenPressed(
      new InstantCommand(m_Tebo::launchpad, m_Tebo)
    ).whenReleased(
      new InstantCommand(m_Tebo::stop, m_Tebo)
    );
    
    new JoystickButton(m_operatorstick, Constants.OPHighShooter).whenPressed(
      new InstantCommand(m_Tebo::highshooter, m_Tebo)
    ).whenReleased(
      new InstantCommand(m_Tebo::stop, m_Tebo)
    );
    
    new JoystickButton(m_operatorstick, Constants.OPLowShooter).whenPressed(
      new InstantCommand(m_Tebo::lowshooter, m_Tebo)
    ).whenReleased(
      new InstantCommand(m_Tebo::stop, m_Tebo)
    );

    new JoystickButton(m_driverstick, Constants.DRStaircaseUp).whenPressed(
      new InstantCommand(m_Staircase::raise, m_Staircase)
    ).whenReleased(
      new InstantCommand(m_Staircase::stop, m_Staircase)
    );

    new JoystickButton(m_driverstick, Constants.DRStaricasDown).whenPressed(
      new InstantCommand(m_Staircase::lower, m_Harambe)
    ).whenReleased(
      new InstantCommand(m_Staircase::stop, m_Staircase)
    );

    new JoystickButton(m_driverstick, Constants.DRIntakein).whenPressed(
      new InstantCommand(m_CuriousJorge::in, m_CuriousJorge)
    ).whenReleased(
      new InstantCommand(m_CuriousJorge::stop, m_CuriousJorge)
    );
    
    new JoystickButton(m_driverstick, Constants.DRIntakeout).whenPressed(
      new InstantCommand(m_CuriousJorge::out, m_CuriousJorge)
    ).whenReleased(
      new InstantCommand(m_CuriousJorge::stop, m_CuriousJorge)
    );    
    
    new JoystickButton(m_driverstick, Constants.DRTeboShoot).whenPressed(
      new InstantCommand(m_Tebo::lowshooter, m_Tebo)
    ).whenReleased(
      new InstantCommand(m_Tebo::stop, m_Tebo)
    );    

    new JoystickButton(m_driverstick, Constants.DRPneumatics)
        .whenPressed(new TogglePneumatics(m_pneumaticSubsystem));
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
