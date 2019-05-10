package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.BallIntake.*;
import frc.robot.commands.Claw.*;

public class Outtake extends Command {
	BallOut ballOut;
	ClawClose clawClose;

	public Outtake() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.ballIntake);
		requires(Robot.claw);

		ballOut = new BallOut();
		clawClose = new ClawClose();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		if (Robot.isOnClaw) {
			clawClose.start();
		} else {
			ballOut.start();
		}
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
		clawClose.cancel();
		ballOut.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
