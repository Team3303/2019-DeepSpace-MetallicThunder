package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static frc.robot.Robot.climber;

public class SetClimberDrive extends Command {
	public SetClimberDrive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.climber);
	}

	float leftDrive, rightDrive;
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() { }

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		climber.setDrive(Robot.m_oi.getLeftJoystickY());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		climber.setDrive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
