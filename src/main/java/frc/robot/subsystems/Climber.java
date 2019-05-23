package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Subsystem definition for the robot elevator.
 */
public class Climber extends Subsystem {
	public WPI_TalonSRX talon_CFL;
	public WPI_TalonSRX talon_CFR;
	public WPI_TalonSRX talon_CB;

	public final boolean front = true;
	public final boolean back = false;

	public Climber() {
		this.talon_CFL = RobotMap.talon_CFL;
		this.talon_CFR = RobotMap.talon_CFR;
		this.talon_CB = RobotMap.talon_CB;
	}

	public void setTalon(WPI_TalonSRX talon, double speed) {
		talon.set(speed);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
