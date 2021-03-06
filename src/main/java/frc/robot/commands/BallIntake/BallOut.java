package frc.robot.commands.BallIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallOut extends Command {
	public BallOut() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.ballIntake);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.ballIntake.ballIntakeOpen(1.0);
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
	protected void end() {
		Robot.ballIntake.ballIntakeOpen(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
