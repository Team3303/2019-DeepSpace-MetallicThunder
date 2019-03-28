/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU_StatusFrame;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.sensors.Constants;

/**
 * Subsystem for the Robot's drive train
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	WPI_VictorSPX talon_FL;
	WPI_VictorSPX talon_FR;
	//	WPI_TalonSRX talon_FL;
//	WPI_TalonSRX talon_FR;
	WPI_TalonSRX talon_BL;
	WPI_TalonSRX talon_BR;
	PigeonIMU pigeon;

	DifferentialDrive differentialDrive;

	public DriveTrain() {
		talon_FL = RobotMap.talon_FL;
		talon_FR = RobotMap.talon_FR;
		talon_BL = RobotMap.talon_BL;
		talon_BR = RobotMap.talon_BR;
		pigeon = RobotMap.pigeon;
		differentialDrive = new DifferentialDrive(talon_BL, talon_BR);

		talon_FL.follow(talon_BL);
		talon_FR.follow(talon_BR);

		/* Disable all motor controllers */
		talon_BL.set(ControlMode.PercentOutput, 0);
		talon_BR.set(ControlMode.PercentOutput, 0);

		/* Factory Default all hardware to prevent unexpected behavior */
		talon_BL.configFactoryDefault();
		talon_BR.configFactoryDefault();
		pigeon.configFactoryDefault();

		/* Set Neutral Mode */
		talon_BL.setNeutralMode(NeutralMode.Brake);
		talon_BR.setNeutralMode(NeutralMode.Brake);

		/** Feedback Sensor Configuration */

		/* Configure the left Talon's selected sensor as local QuadEncoder */
		talon_BL.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source
													Constants.PID_PRIMARY,					// PID Slot for Source [0, 1]
													Constants.kTimeoutMs);					// Configuration Timeout

		/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
		talon_BR.configRemoteFeedbackFilter(talon_FL.getDeviceID(),					// Device ID of Source
												RemoteSensorSource.TalonSRX_SelectedSensor,	// Remote Feedback Source
												Constants.REMOTE_0,							// Source number [0, 1]
												Constants.kTimeoutMs);						// Configuration Timeout

		/* Configure the Pigeon IMU to the other remote slot available on the right Talon */
		talon_BR.configRemoteFeedbackFilter(pigeon.getDeviceID(),
												RemoteSensorSource.Pigeon_Yaw,
												Constants.REMOTE_1,
												Constants.kTimeoutMs);

		/* Setup Sum signal to be used for Distance */
		talon_BR.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, Constants.kTimeoutMs);				// Feedback Device of Remote Talon
		talon_BR.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kTimeoutMs);	// Quadrature Encoder of current Talon

		/* Configure Sum [Sum of both QuadEncoders] to be used for Primary PID Index */
		talon_BR.configSelectedFeedbackSensor(	FeedbackDevice.SensorSum,
													Constants.PID_PRIMARY,
													Constants.kTimeoutMs);

		/* Scale Feedback by 0.5 to half the sum of Distance */
		talon_BR.configSelectedFeedbackCoefficient(	0.5, 						// Coefficient
														Constants.PID_PRIMARY,		// PID Slot of Source
														Constants.kTimeoutMs);		// Configuration Timeout

		/* Configure Remote 1 [Pigeon IMU's Yaw] to be used for Auxiliary PID Index */
		talon_BR.configSelectedFeedbackSensor(	FeedbackDevice.RemoteSensor1,
													Constants.PID_TURN,
													Constants.kTimeoutMs);

		/* Scale the Feedback Sensor using a coefficient */
		talon_BR.configSelectedFeedbackCoefficient(	1,
														Constants.PID_TURN,
														Constants.kTimeoutMs);

		/* Configure output and sensor direction */
//		talon_BL.setSensorPhase(true);
//		talon_BR.setInverted(true);
//		talon_BR.setSensorPhase(true);

		/* Set status frame periods to ensure we don't have stale data */
		talon_BR.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, Constants.kTimeoutMs);
		talon_BR.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, Constants.kTimeoutMs);
		talon_BR.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, Constants.kTimeoutMs);
		talon_BR.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, Constants.kTimeoutMs);
		talon_BL.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, Constants.kTimeoutMs);
		pigeon.setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR , 5, Constants.kTimeoutMs);

		/* Configure neutral deadband */
		talon_BR.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);
		talon_BL.configNeutralDeadband(Constants.kNeutralDeadband, Constants.kTimeoutMs);

		/* Motion Magic Configurations */
		talon_BR.configMotionAcceleration(2000, Constants.kTimeoutMs);
		talon_BR.configMotionCruiseVelocity(2000, Constants.kTimeoutMs);

		/**
		 * Max out the peak output (for all modes).
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		talon_BL.configPeakOutputForward(+1.0, Constants.kTimeoutMs);
		talon_BL.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);
		talon_BR.configPeakOutputForward(+1.0, Constants.kTimeoutMs);
		talon_BR.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);

		/* FPID Gains for distance servo */
		talon_BR.config_kP(Constants.kSlot_Distanc, Constants.kGains_Distanc.kP, Constants.kTimeoutMs);
		talon_BR.config_kI(Constants.kSlot_Distanc, Constants.kGains_Distanc.kI, Constants.kTimeoutMs);
		talon_BR.config_kD(Constants.kSlot_Distanc, Constants.kGains_Distanc.kD, Constants.kTimeoutMs);
		talon_BR.config_kF(Constants.kSlot_Distanc, Constants.kGains_Distanc.kF, Constants.kTimeoutMs);
		talon_BR.config_IntegralZone(Constants.kSlot_Distanc, Constants.kGains_Distanc.kIzone, Constants.kTimeoutMs);
		talon_BR.configClosedLoopPeakOutput(Constants.kSlot_Distanc, Constants.kGains_Distanc.kPeakOutput, Constants.kTimeoutMs);

		/* FPID Gains for turn servo */
		talon_BR.config_kP(Constants.kSlot_Turning, Constants.kGains_Turning.kP, Constants.kTimeoutMs);
		talon_BR.config_kI(Constants.kSlot_Turning, Constants.kGains_Turning.kI, Constants.kTimeoutMs);
		talon_BR.config_kD(Constants.kSlot_Turning, Constants.kGains_Turning.kD, Constants.kTimeoutMs);
		talon_BR.config_kF(Constants.kSlot_Turning, Constants.kGains_Turning.kF, Constants.kTimeoutMs);
		talon_BR.config_IntegralZone(Constants.kSlot_Turning, Constants.kGains_Turning.kIzone, Constants.kTimeoutMs);
		talon_BR.configClosedLoopPeakOutput(Constants.kSlot_Turning, Constants.kGains_Turning.kPeakOutput, Constants.kTimeoutMs);

		/**
		 * 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
		int closedLoopTimeMs = 1;
		talon_BR.configClosedLoopPeriod(0, closedLoopTimeMs, Constants.kTimeoutMs);
		talon_BR.configClosedLoopPeriod(1, closedLoopTimeMs, Constants.kTimeoutMs);

		/**
		 * configAuxPIDPolarity(boolean invert, int timeoutMs)
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
		talon_BR.configAuxPIDPolarity(false, Constants.kTimeoutMs);

		/* Initialize */
//		_firstCall = true;
//		_state = false;
		talon_BR.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 10, Constants.kTimeoutMs);
		talon_BR.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, Constants.kTimeoutMs);
//		zeroSensors();
	}

	public void tankDrive(double leftValue, double rightValue) {
		differentialDrive.tankDrive(leftValue, rightValue);
	}
	public void driveStraight(double target_turn) {
		talon_BR.set(ControlMode.PercentOutput, 0.2, DemandType.AuxPID, target_turn);
//		talon_BR.set(ControlMode.PercentOutput, -0.5, DemandType.AuxPID, target_turn);
//		talon_BL.follow(talon_BR, FollowerType.AuxOutput1);
		talon_BL.follow(talon_BR, FollowerType.PercentOutput);
	}

	public void turn(double target_turn) {
		talon_BR.set(ControlMode.PercentOutput, 0.1, DemandType.AuxPID, target_turn);
//		talon_BR.set(ControlMode.PercentOutput, -0.5, DemandType.AuxPID, target_turn);
//		talon_BL.follow(talon_BR, FollowerType.AuxOutput1);
		talon_BL.follow(talon_BR, FollowerType.PercentOutput);
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

}