package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

import static frc.robot.RobotMap.*;

public class BallIntake extends Subsystem {
	// DigitalInput limitSwitch;
	// Counter counter = new Counter(limitSwitch);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public BallIntake() {
		// talon_BIL.follow(talon_BI);
		// limitSwitch = new DigitalInput(1);
	}

	/**
	 * Sets the output for the intake talons.
	 *
	 * @param speed The speed to set, or direction
	 */
	public void ballIntakeOpen(double speed) {
		if (Robot.isCompRobot) {
			talon_BI.set(speed);
		} else {
			spark_BIR.set(speed);
			spark_BIL.set(speed);
		}
	}

	public void transformUp() {
		ballRotator.set(0.75);
	}

	public void transformDown() {
		ballRotator.set(-0.75);
	}

	public void transformEnd() {
		ballRotator.set(0);
	}


	// public boolean isSwitchSet() {
	// 	return counter.get() > 0;
	// }


	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
