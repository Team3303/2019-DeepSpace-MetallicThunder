package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.RobotMap.*;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import frc.robot.sensors.Constants;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * Subsystem definition for thr Robot elevator.
 */
public class Elevator extends Subsystem {
	public float[] ballLevels = new float[]{(0), (27.5f), (55.5f), (83.5f), (111.5f),};
	public float[] hatchLevels = new float[]{(0), (19f), (67f), (86f),};
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
			cir = 2.749f;
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
		this.elevator.configPeakOutputReverse(-1, Constants.kTimeoutMs);

		/* Set Motion Magic gains in slot0 - see documentation */
		this.elevator.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		this.elevator.config_kF(0, Constants.kGains.kF, Constants.kTimeoutMs);
		this.elevator.config_kP(0, Constants.kGains.kP, Constants.kTimeoutMs);
		this.elevator.config_kI(0, Constants.kGains.kI, Constants.kTimeoutMs);
		this.elevator.config_kD(0, Constants.kGains.kD, Constants.kTimeoutMs);

		/* Set acceleration and vcruise velocity - see documentation */
		this.elevator.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
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

	/**
	 * Makes this elevator move to target posistion. Using some Motion Magic and
	 * magic numbers, of course.
	 *
	 * @param targetPos The position to target. Value should be a magc number.
	 */
	public void target(double targetPos) {

		elevator.set(ControlMode.MotionMagic, targetPos);
	}

	public void targetLevel() {
		int ticks;
		if (Robot.isOnClaw) {
			ticks = (int) ((hatchLevels[level] * 4096 * 36) / (cir * 15));
		} else {
			ticks = (int) ((ballLevels[level] * 4096 * 36) / (cir * 15));
		}
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

	public void setLevel(int level) {
		this.level = level;
		this.checkLevel();
	}

	public float getLevelHeight() {
		this.checkLevel();
		if (Robot.isOnClaw)
			return hatchLevels[this.level];
		else
			return ballLevels[this.level];
	}

	public int getLevel() {
		return this.level;
	}

	private void checkLevel() {
		if (Robot.isOnClaw) {
			if (level < 0)
				level = 0;
			if (level > 2)
				level = 2;
		} else {
			if (level < 0)
				level = 0;
			if (level > 4)
				level = 4;
		}
}
}