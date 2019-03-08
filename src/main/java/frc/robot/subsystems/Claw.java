/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import static frc.robot.RobotMap.*;

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void clawOpen() {
		clawSolenoid.set(Value.kReverse);
	}

	public void clawClose() {
		clawSolenoid.set(Value.kForward);
	}

	public void clawIn() {
		clawInAndOut.set(Value.kForward);
	}

	public void clawOut() {
		clawInAndOut.set(Value.kReverse);
	}

	public boolean clawIsOut() {
		switch (clawInAndOut.get()) {
			case kOff:
				return false;
			case kForward:
				return false;
			case kReverse:
				return true;
			default:
				return true;
		}

	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
