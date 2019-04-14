package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Raiser;

public class RaiserTransform extends Command {

	Raiser raiser;

	boolean raiserSide;
	double raiserSpeed;

	public RaiserTransform(boolean side, double speed) {
		requires(Robot.raiser);

		this.raiserSide = side;
		this.raiserSpeed = speed;

		raiser = Robot.raiser;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if(raiserSide = raiser.front) {
			raiser.setTalon(raiser.talon_CFL, raiserSpeed);
			raiser.setTalon(raiser.talon_CFR, raiserSpeed);
		} else {
			raiser.setTalon(raiser.talon_CB, raiserSpeed);
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
		if(raiserSide = raiser.front) {
			raiser.setTalon(raiser.talon_CFL, 0d);
			raiser.setTalon(raiser.talon_CFR, 0d);
		} else {
			raiser.setTalon(raiser.talon_CB, 0d);
		}
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}