/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

import static frc.robot.RobotMap.*;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
	DigitalInput limitSwitch = new DigitalInput(1);
	Counter counter = new Counter(limitSwitch);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public BallIntake() {
		// talon_BIL.follow(talon_BIR);
	}

	/**
	 * Sets the value for the intake talons. Negative is
	 *
	 * @param speed The speed to set, or direction
	 */
	public void ballIntakeOpen(double speed) {
		if (Robot.isCompRobot) {
			talon_BIR.set(speed); // The left talon of the intake is following the right.
		} else {
			spark_BIR.set(speed);
			spark_BIL.set(speed);
		}
	}

	public void transformUp() {
		ballRotator.set(0.5);
	}

	public void transformDown() {
		ballRotator.set(0);
	}

	public void transformEnd() {
		ballRotator.set(0);
	}


	public boolean isSwitchSet() {
		return counter.get() > 0;
	}


	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
