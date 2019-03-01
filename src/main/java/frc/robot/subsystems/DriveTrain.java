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
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.RobotMap;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import com.ctre.phoenix.sensors.PigeonIMU;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_VictorSPX talon_FL;
  WPI_VictorSPX talon_FR;
  WPI_TalonSRX talon_BL;
  WPI_TalonSRX talon_BR;
  PigeonIMU pigeon;

  DifferentialDrive differentialDrive;
  
  public DriveTrain() {
    talon_FL = RobotMap.talon_FL;
    talon_FR = RobotMap.talon_FR;
    talon_BL = RobotMap.talon_BL;
    talon_BR = RobotMap.talon_BR;
    pigeon = RobotMap.pigeon;
    differentialDrive = new DifferentialDrive(talon_FL, talon_FR);

    talon_BL.follow(talon_FL);
    talon_BR.follow(talon_FR);
  }

  public void tankDrive(double leftValue, double rightValue) {
    differentialDrive.tankDrive(leftValue, rightValue);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

}