/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

import static frc.robot.RobotMap.*;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public BallIntake() {
    // talon_BIL.follow(talon_BIR);
  }

  /**
   * Sets the value for the intake talons. Negative is 
   * @param speed The speed to set, or direction
   */
  public void ballIntakeOpen(double speed) {
    if (Robot.isCompRobot) {
      talon_BIR.set(speed); // The left talon of the intake is following the right.
    } else {
      spark_BIR.set(speed);
      spark_BIL.set(speed);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
