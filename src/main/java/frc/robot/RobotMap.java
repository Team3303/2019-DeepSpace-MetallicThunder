package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int WHEEL_CONTROLLER_PORT = 0;
	public static final int TALON_FL_ID = 1;
	public static final int TALON_BL_ID = 2;
	public static final int TALON_FR_ID = 3;
	public static final int TALON_BR_ID = 4;
	public static final int TALON_BI_ID = 5;
	public static final int TALON_EL_ID = 7;
	public static final int TALON_BROT_ID = 6;

//	public static WPI_VictorSPX talon_FL;
//	public static WPI_VictorSPX talon_FR;

	public static final int SPARK_BIL_ID = 3;
	public static final int SPARK_BIR_ID = 2;
	public static final int TALON_CFR_ID = 1;
	public static final int TALON_CFL_ID = 8;
	public static final int TALON_CBL_ID = 6;
	public static final int TALON_CBR_ID = 5;


	public static WPI_VictorSPX talon_FL;
	public static WPI_VictorSPX talon_FR;
//	public static WPI_TalonSRX talon_FL;
//	public static WPI_TalonSRX talon_FR;
	public static WPI_TalonSRX talon_BL;
	public static WPI_TalonSRX talon_BR;
	public static WPI_TalonSRX talon_BI;
	public static WPI_TalonSRX talon_EL;
	public static WPI_TalonSRX ballRotator;

	public static Spark spark_BIL;
	public static Spark spark_BIR;
	public static WPI_TalonSRX talon_CFL;
	public static Talon talon_CFR;
	public static WPI_TalonSRX talon_CBL;
	public static WPI_TalonSRX talon_CBR;

	public static DoubleSolenoid clawSolenoid;
	public static DoubleSolenoid clawInAndOut;

	public static PigeonIMU pigeon;
	public static Encoder encoder;


	public static void init() {
		// Motorcontrollers
		talon_FL = new WPI_VictorSPX(RobotMap.TALON_FL_ID);
		talon_FR = new WPI_VictorSPX(RobotMap.TALON_FR_ID);
//		talon_FL = new WPI_TalonSRX(RobotMap.TALON_FL_ID);
//		talon_FR = new WPI_TalonSRX(RobotMap.TALON_FR_ID);
		talon_BL = new WPI_TalonSRX(RobotMap.TALON_BL_ID);
		talon_BR = new WPI_TalonSRX(RobotMap.TALON_BR_ID);
		talon_EL = new WPI_TalonSRX(RobotMap.TALON_EL_ID);
		ballRotator = new WPI_TalonSRX(RobotMap.TALON_BROT_ID);
		talon_CFR = new Talon(RobotMap.TALON_CFR_ID);
		talon_CFL = new WPI_TalonSRX(RobotMap.TALON_CFL_ID);
		talon_CBR = new WPI_TalonSRX(RobotMap.TALON_CBR_ID);
		talon_CBL = new WPI_TalonSRX(RobotMap.TALON_CBL_ID);

		if (Robot.isCompRobot) {
			talon_BI = new WPI_TalonSRX(RobotMap.TALON_BI_ID);
		} else {
			spark_BIR = new Spark(RobotMap.SPARK_BIR_ID);
			spark_BIL = new Spark(RobotMap.SPARK_BIL_ID);
		}

		// Solenoids
		clawSolenoid = new DoubleSolenoid(0, 1);
		clawInAndOut = new DoubleSolenoid(2, 3);

		// Sensors
		pigeon = new PigeonIMU(0);
		encoder = new Encoder(0,1);
	}

	public static void resetControllers() {
		talon_EL.set(0);
	}

}