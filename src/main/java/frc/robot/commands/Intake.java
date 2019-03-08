package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.BallIntake.*;
import frc.robot.commands.Claw.*;

public class Intake extends Command {
	BallIn ballIn;
	ClawOpen clawOpen;

	public Intake() {
		requires(Robot.ballIntake);
		requires(Robot.claw);

		ballIn = new BallIn();
		clawOpen = new ClawOpen();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		if (Robot.isOnClaw) {
			clawOpen.start();
		} else {
			ballIn.start();
		}
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
		clawOpen.cancel();
		ballIn.cancel();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
