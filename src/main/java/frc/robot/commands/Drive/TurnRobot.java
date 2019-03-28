/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

import static frc.robot.RobotMap.*;

public class TurnRobot extends Command {
	double targetAngle;

	public TurnRobot(double angle) {
		this.targetAngle = angle;
	}

	@Override
	protected void initialize() {
//		RobotMap.pigeon.setYawToCompass();
		RobotMap.pigeon.setYaw(0d);

		talon_BL.setInverted(true);
		talon_FL.setInverted(true);
	}

	@Override
	protected void execute() {
		Robot.driveTrain.turn(this.targetAngle);
	}

	@Override
	protected boolean isFinished() {
		double[] values = new double[3];
		pigeon.getYawPitchRoll(values);
		if(values[0] > targetAngle+1d || values[0] < targetAngle-1d)
			return false;
		else
			return true;

	}

	@Override
	protected void end() {
		talon_BL.setInverted(false);
		talon_FL.setInverted(false);
	}

	@Override
	protected void interrupted() {
		talon_BL.setInverted(false);
		talon_FL.setInverted(false);
	}
}
