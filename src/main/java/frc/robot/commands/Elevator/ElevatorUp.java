package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorUp extends Command {

  public ElevatorUp() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevatorBall);
    requires(Robot.elevatorClaw);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.isOnClaw)
    

    if (!Robot.isOnClaw)
      Robot.elevatorBall.set(0.5);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevatorClaw.set(0);
    Robot.elevatorBall.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
