package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

import static frc.robot.RobotMap.talon_BL;
import static frc.robot.RobotMap.talon_FL;

public class DriveStraight extends Command {

	@Override
	protected void initialize() {
		// RobotMap.pigeon.setYawToCompass();
		RobotMap.pigeon.setYaw(0d);

		talon_BL.setInverted(true);
		talon_FL.setInverted(true);
	}

	@Override
	protected void execute() {
		Robot.driveTrain.driveStraight(0);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveTrain.tankDrive(0d,0d);
		talon_BL.setInverted(false);
		talon_FL.setInverted(false);
	}

	@Override
	protected void interrupted() {
		Robot.driveTrain.tankDrive(0d,0d);
		talon_BL.setInverted(false);
		talon_FL.setInverted(false);
	}
}
