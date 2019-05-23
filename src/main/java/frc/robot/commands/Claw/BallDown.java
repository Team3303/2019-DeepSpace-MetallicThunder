package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallDown extends Command {
	public BallDown() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.claw.clawOut();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() { }

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() { }

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() { }
}
