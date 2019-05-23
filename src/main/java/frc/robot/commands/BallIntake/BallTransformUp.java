package frc.robot.commands.BallIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import static frc.robot.Robot.ballIntake;
import static frc.robot.RobotMap.*;

public class BallTransformUp extends Command {
	public BallTransformUp() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(ballIntake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		ballIntake.transformUp();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// return ballIntake.isSwitchSet();
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		ballIntake.transformEnd();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		ballIntake.transformEnd();
	}
}
