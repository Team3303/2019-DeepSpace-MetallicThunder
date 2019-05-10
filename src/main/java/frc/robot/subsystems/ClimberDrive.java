package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimberDrive extends Subsystem {
	WPI_TalonSRX talon_CD;

	public ClimberDrive() {
		talon_CD = RobotMap.talon_CD;
	}

	public void driveClimber(double speed) {
		talon_CD.set(speed);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
