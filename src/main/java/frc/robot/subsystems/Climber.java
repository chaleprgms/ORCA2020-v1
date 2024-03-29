/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  DoubleSolenoid climberSol;

  public Climber() {
    climberSol = new DoubleSolenoid(9,9);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public void erectClimber(){
    climberSol.set(Value.kForward);
  }

  public void retractClimber(){
    climberSol.set(Value.kReverse);
  }

  public void stopSol(){
    climberSol.set(Value.kOff);
  }
  
}
