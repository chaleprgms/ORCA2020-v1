/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.auto.AutoAlign;
import frc.robot.subsystems.ColorSoli;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;






/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final Dummy_Test_System m_testbed = new Dummy_Test_System();

  public Drivetrain m_Drivetrain = new Drivetrain(); 
  public XboxController controller;
  public ColorSoli m_ColorSoli;
  public Intake m_Intake;
  public Conveyor m_Conveyor;
  public Flywheel m_FlyWheel;
  public Limelight m_Limelight;


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    controller = new XboxController(1);
    m_ColorSoli = new ColorSoli(); 
    m_Intake = new Intake();
    m_Conveyor = new Conveyor();
    m_FlyWheel = new Flywheel();
    m_Limelight = new Limelight();

    m_Drivetrain.setDefaultCommand(
        new RunCommand(() -> m_Drivetrain.Drive(controller)));

    m_FlyWheel.setDefaultCommand(
        new RunCommand(() -> m_FlyWheel.test(controller)));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Left Bumper Button - Rise Climb
    new JoystickButton(controller, XboxController.Button.kBumperLeft.value)
    .whenHeld(new RunCommand(() -> m_Drivetrain.WinchDown(controller)));

    new JoystickButton(controller, XboxController.Button.kBumperLeft.value)
    .whenReleased(new RunCommand(() -> m_Drivetrain.reverse()));

    new JoystickButton(controller, XboxController.Button.kBumperLeft.value)
    .whenReleased(new RunCommand(() -> m_Drivetrain.Drive(controller)));



   // Right Bumper Button - Lower Climb 
    new JoystickButton(controller, XboxController.Button.kBumperRight.value)
    .whenHeld(new RunCommand(() -> m_Drivetrain.WinchUp(controller)));

    new JoystickButton(controller, XboxController.Button.kBumperRight.value)
    .whenReleased(new RunCommand(() -> m_Drivetrain.reverse()));
    
    new JoystickButton(controller, XboxController.Button.kBumperRight.value)
    .whenReleased(new RunCommand(() -> m_Drivetrain.Drive(controller)));



    // B Button - Intake Out
    new JoystickButton(controller, XboxController.Button.kB.value)
        .whenHeld(new RunCommand(() -> m_Intake.intakeOut()));

    new JoystickButton(controller, XboxController.Button.kB.value)
    .whenReleased(new RunCommand(() -> m_Intake.intakeStop()));
 

  
   //A Button - Intake In
    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenHeld(new RunCommand(() -> m_Intake.intakeIn()));

    new JoystickButton(controller, XboxController.Button.kA.value)
    .whenReleased(new RunCommand(() -> m_Intake.intakeStop()));



   //Left Stick Button - Conveyor Up
   new JoystickButton(controller, XboxController.Button.kStickLeft.value)
   .whenPressed(new RunCommand(() -> m_FlyWheel.set(Constants.debugShooterSet)));
   
   
   


   //Right Stick Button 
   new JoystickButton(controller, XboxController.Button.kStickRight.value)
   .whenPressed(new RunCommand(() -> m_FlyWheel.stop()));
 

   //Right Stick Button - Conveyor Down
  

    //X Button - Conveyor Soli fire
    new JoystickButton(controller, XboxController.Button.kX.value)
    .whenPressed(new RunCommand(() -> m_Conveyor.forward()));


    //Y Button - Conveyor Soli retact
    new JoystickButton(controller, XboxController.Button.kY.value)
    .whenPressed(new RunCommand(() -> m_Conveyor.reverse()));




   //Start Button - Shooter Speed Up
   new JoystickButton(controller, XboxController.Button.kStart.value)
   .whenPressed(new RunCommand(() -> m_FlyWheel.SpeedUp()));

   // Back Button
   new JoystickButton(controller, XboxController.Button.kBack.value)
   .whenHeld(new AutoAlign(m_Drivetrain, m_Limelight));
   

   // Back Button 
   new JoystickButton(controller, XboxController.Button.kBack.value)
   .whenPressed(new RunCommand(() -> m_FlyWheel.SlowStop(), m_FlyWheel));

 }

}