package frc.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AutonomousFull;
import frc.robot.commands.AutonomousSandstorm;

import static frc.robot.RobotMap.clawSolenoid;
import static frc.robot.RobotMap.talon_EL;
import static frc.robot.Robot.elevator;

public class ShuffleboardConfig {

	public static double[] ypr;
	public static SendableChooser<Command> m_chooser = new SendableChooser<>();
	public static SendableChooser<Boolean> elevatorModeChooser = new SendableChooser<>();
	public static SendableChooser<Boolean> robotChooser = new SendableChooser<>();

	ShuffleboardConfig() {
		m_chooser.addDefault("Sandstormer Mode", new AutonomousSandstorm());
		m_chooser.addObject("Full Autonomomy", new AutonomousFull());
		elevatorModeChooser.addDefault("PercentOutput", false);
		elevatorModeChooser.addObject("PID", true);
		robotChooser.addDefault("Competition Robot", true);
		robotChooser.addObject("Practice Robot", false);
		SmartDashboard.putData("Auto mode", m_chooser);
		// m_chooser.setDefaultOption("Default Auto", new AutonomousCommand());
		// chooser.addOption("My Auto", new MyAutoCommand());
	}

	public static void updateValues() {
		// SmartDashboard.putNumber("Table X", xEntry.getDouble(0.0));
		// SmartDashboard.putNumber("Table Y", yEntry.getDouble(0.0));
		// SmartDashboard.putNumber("Table Z", sizeEntry.getDouble(0.0));
		// SmartDashboard.putBoolean("Limite Switche", ballIntake.isSwitchSet());
		SmartDashboard.putNumber("Pigeon Angle", ypr[0]);
		SmartDashboard.putNumber("Encoder Value", talon_EL.getSelectedSensorPosition());
		SmartDashboard.putNumber("Calculated PID Value", (int) ((11 * 4096 * 36) / (7.493f * 15)));
	}

	public static void outputSensorValues() {
		ypr = new double[3];
		RobotMap.pigeon.getYawPitchRoll(ypr);
		SmartDashboard.putNumber("Pigeon Angle", ypr[0]);
		SmartDashboard.putNumber("Encoder Value", talon_EL.getSelectedSensorPosition());
		SmartDashboard.putNumber("Calculated PID Value", ((11 * 4096 * 36) / (elevator.cir * 15)));
		SmartDashboard.putNumber("Encoder Value in Inches", ((talon_EL.getSelectedSensorPosition() * 2 * elevator.cir * 15) / (4096 * 36)));
		SmartDashboard.putNumber("Target Elevator Level", elevator.getLevel());
		SmartDashboard.putNumber("Elevator Speed", talon_EL.getMotorOutputPercent());
		// SmartDashboard.putNumber("Table X", xEntry.getDouble(0.0));
		// SmartDashboard.putNumber("Table Y", yEntry.getDouble(0.0));
		// SmartDashboard.putNumber("Table Z", sizeEntry.getDouble(0.0));
		// SmartDashboard.putBoolean("Limite Switche", ballIntake.isSwitchSet());

		SmartDashboard.putNumber("Pigeon Angle", ypr[0]);
		SmartDashboard.putNumber("Encoder Value", talon_EL.getSelectedSensorPosition());

		SmartDashboard.putNumber("Calculated PID Value", ((11 * 4096 * 36) / (7.493f * 15)));
		switch (clawSolenoid.get()) {
			case kOff:
				SmartDashboard.putString("Solenoid State", "Off");
				break;
			case kReverse:
				SmartDashboard.putString("Solenoid State", "Reverse");
				break;
			case kForward:
				SmartDashboard.putString("Solenoid State", "Forward");
				break;
		}
	}
}
