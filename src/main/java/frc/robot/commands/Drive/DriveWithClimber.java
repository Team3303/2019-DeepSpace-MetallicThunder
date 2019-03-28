/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static frc.robot.Robot.climberDrive;

public class DriveWithClimber extends Command {

  float leftDrive, rightDrive;

  public DriveWithClimber() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climberDrive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() { }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    climberDrive.driveClimber(Robot.m_oi.getLeftJoystickY());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    climberDrive.driveClimber(0);
   }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
   }
}
