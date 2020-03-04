package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class ClimberTransform extends Command {
	Climber climber;
	boolean climberSide;
	double climberSpeed;

	public ClimberTransform(boolean side, double speed) {
		requires(Robot.climber);

		climber = Robot.climber;
		this.climberSide = side;
		this.climberSpeed = speed;
	}

	@Override
	protected void initialize() { }

	@Override
	protected void execute() {
		if(climberSide = climber.front) {
			climber.setLeg(climber.talon_CFL, climberSpeed);
			climber.setLeg(climber.talon_CFR, climberSpeed);
		} else {
			climber.setLeg(climber.talon_CB, climberSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		if(climberSide = climber.front) {
			climber.setLeg(climber.talon_CFL, 0d);
			climber.setLeg(climber.talon_CFR, 0d);
		} else {
			climber.setLeg(climber.talon_CB, 0d);
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
