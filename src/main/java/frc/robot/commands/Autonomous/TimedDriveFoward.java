package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

class TimedDriveFoward extends Command {
	public TimedDriveFoward() {
		//super(2);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		setTimeout(2);
		requires(Robot.driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.driveTrain.tankDrive(1, 1);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() { }

	// Make this return true when this Command no longer needs to run execute()
	// @Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.driveTrain.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		SmartDashboard.putString("Timed Drive Interrupted", "Interrupted");
	}
}
