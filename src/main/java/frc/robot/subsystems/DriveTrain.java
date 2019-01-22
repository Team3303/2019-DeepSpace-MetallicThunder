/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX talon_FL;
  WPI_TalonSRX talon_FR;
  WPI_TalonSRX talon_BL;
  WPI_TalonSRX talon_BR;

  DifferentialDrive differentialDrive;
  boolean directionToggle = false;
  
  public DriveTrain() {
    talon_FL = new WPI_TalonSRX(RobotMap.TALON_FL_ID);
    talon_FR = new WPI_TalonSRX(RobotMap.TALON_FR_ID);
    talon_BL = new WPI_TalonSRX(RobotMap.TALON_BL_ID);
    talon_BR = new WPI_TalonSRX(RobotMap.TALON_BR_ID);

    differentialDrive = new DifferentialDrive(talon_FL, talon_FR);

    talon_BL.follow(talon_FL);
    talon_BR.follow(talon_FR);
  }

  public void switchDirection(){
    directionToggle = !directionToggle;
  }

  public void tankDrive(double leftValue, double rightValue) {
    if (!directionToggle)
      differentialDrive.tankDrive(leftValue, rightValue);
    if (directionToggle)
      differentialDrive.tankDrive(-leftValue, -rightValue);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}