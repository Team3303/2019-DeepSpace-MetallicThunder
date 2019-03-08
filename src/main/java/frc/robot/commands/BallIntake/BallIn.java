package frc.robot.commands.BallIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallIn extends Command {
	public BallIn() {
	}

	@Override
	protected void initialize() {
		Robot.ballIntake.ballIntakeOpen(-0.6);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.ballIntake.ballIntakeOpen(0);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
