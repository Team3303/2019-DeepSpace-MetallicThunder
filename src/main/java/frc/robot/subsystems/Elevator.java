package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.sensors.Constants;

import static frc.robot.RobotMap.talon_EL;

/**
 * Subsystem definition for the robot elevator.
 */
public class Elevator extends Subsystem {
	//	public float[] ballLevels = new float[]{(0), (27.5f), (55.5f), (83.5f), (111.5f),};
//	public float[] hatchLevels = new float[]{(0), (19f), (67f), (86f),};
	public float[] levels = new float[]{
			(0),    // Floor, just sets motor to no activity.
			(4f), // 1st ball level
			(20f), // 2nd ball level
//			(32f), // 2nd ball level
			(60f) // 3rd ball level
	};

	public float cir;
	private int level;
	WPI_TalonSRX elevator;
	SensorCollection encoder;

	/**
	 * Instantiates the Talon.
	 */
	public Elevator(WPI_TalonSRX talon) {
		this.elevator = talon;
		encoder = talon_EL.getSensorCollection();
		level = 0;
		if (Robot.isCompRobot) {
			cir = 7.493f;
		} else {
			cir = 6.000f;
		}

		this.elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
				Constants.kTimeoutMs);

		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when driving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		this.elevator.setSensorPhase(true);
		this.elevator.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		this.elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		this.elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* Set the peak and nominal outputs */
		this.elevator.configNominalOutputForward(0, Constants.kTimeoutMs);
		this.elevator.configNominalOutputReverse(0, Constants.kTimeoutMs);
		this.elevator.configPeakOutputForward(1, Constants.kTimeoutMs);
		this.elevator.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		this.elevator.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		this.elevator.config_kF(Constants.kSlotIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		this.elevator.config_kP(Constants.kSlotIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		this.elevator.config_kI(Constants.kSlotIdx, Constants.kGains.kI, Constants.kTimeoutMs);
		this.elevator.config_kD(Constants.kSlotIdx, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		this.elevator.configMotionCruiseVelocity(30000, Constants.kTimeoutMs);
		this.elevator.configMotionAcceleration(6000, Constants.kTimeoutMs);
		/* Zero the sensor */
		this.elevator.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

	}

	/**
	 * Makes this elevator move up. This is accomplished by setting the assigned
	 * Talon's output speed.
	 *
	 * @param speed The speed to set. Value should be between -1.0 and 1.0.
	 */
	public void set(double speed) {
		elevator.set(speed);
	}

	public void targetLevel() {
		this.checkLevel();
		this.targetInches(this.levels[level]);
	}

	/**
	 * Sets the elevator talon's target to a specific height, using the encoder.
	 *
	 * @param targetPos The height to set, in inches.
	 */
	public void targetInches(double targetPos) {
		elevator.set(ControlMode.MotionMagic, (( (targetPos/2) * 4097f * 36f) / (cir * 15f)));
	}

	public void targetLevelOld() {
		int ticks = (int) ((levels[level] * 4096 * 36) / (cir * 15));
		elevator.set(ControlMode.MotionMagic, ticks);

	}

	/**
	 * Stops the elevator. Sets speed to 0.
	 */
	public void stop() {
		elevator.set(0);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setEncoderPos(int sensorPos) {
		elevator.setSelectedSensorPosition(sensorPos);
	}

	public float getLevelHeight() {
		this.checkLevel();
		return levels[this.level];
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
		this.checkLevel();
	}

	/**
	 * Checks this.level to see if it exceeds the maximum data range and is lower than 0.
	 */
	private void checkLevel() {
		if (this.level < 0)
			this.level = 0;

//		if(Robot.isCompRobot) {
			if (this.level > 2)
				this.level = 2;
//		} else {
//			if (this.level > 1)
//				this.level = 1;
//		}
	}
}
