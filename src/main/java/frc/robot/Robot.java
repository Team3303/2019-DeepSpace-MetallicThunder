/*----------------------------------------------------------------------------*
 * Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        *
 * Open Source Software - may be modified and shared by FRC teams. The code   *
 * must be accompanied by the FIRST BSD license file in the root directory of *
 * the project.                                                               *
 *----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.AutonomousSandstorm;
import frc.robot.commands.Drive.DriveWithJoysticks;
import frc.robot.commands.Elevator.ElevatorSetPos;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
//import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	public static DriveTrain driveTrain;
	public static Claw claw;
	public static BallIntake ballIntake;
	public static Elevator elevator;
	public static boolean isOnClaw = true;
	public static boolean isCompRobot = true;
	Compressor compressor;

	AutonomousSandstorm m_autonomousCommand;
	Command drive;

//	 NetworkTableInstance networkTableInstance;
//	 NetworkTable table;
//	 NetworkTableEntry xEntry;
//	 NetworkTableEntry yEntry;
//	 NetworkTableEntry sizeEntry;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		RobotMap.init();
		driveTrain = new DriveTrain();
		claw = new Claw();
		ballIntake = new BallIntake();
		elevator = new Elevator(RobotMap.talon_EL);
		m_oi = new OI(); m_oi.init();
		compressor = new Compressor(0);
		drive = new DriveWithJoysticks();

		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);

//		networkTableInstance = NetworkTableInstance.getDefault();
//		networkTableInstance.startServer();

//		table = networkTableInstance.getTable("Datatable");
//		xEntry = table.getEntry("x");
//		yEntry = table.getEntry("y");
//		sizeEntry = table.getEntry("size");
	}

	/**
	 * This function is called every robot packet, no matter the mode. Use
	 * this for items like diagnostics that you want ran during disabled,
	 * autonomous, teleoperated and test.
	 *
	 * <p>This runs after the mode specific periodic functions, but before
	 * LiveWindow and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
//		System.out.println("isConnected:" + networkTableInstance.isConnected());
//		System.out.println("networkMode:" + networkTableInstance.getNetworkMode());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.key
	 */
	@Override
	public void disabledInit() {
		RobotMap.resetControllers();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard.
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// m_autonomousCommand = m_chooser.getSelected();
		m_autonomousCommand = new AutonomousSandstorm();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		// if (commandObject != null)
		m_autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		m_autonomousCommand.drive();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		compressor.setClosedLoopControl(true);
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		drive.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (!drive.isRunning() && !m_oi.driveInverse.isRunning())
			drive.start();

		ShuffleboardConfig.outputSensorValues();
	}


	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
