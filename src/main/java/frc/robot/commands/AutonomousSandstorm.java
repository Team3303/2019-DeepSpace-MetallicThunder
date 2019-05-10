package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Claw.ClawClose;
import frc.robot.commands.Drive.DriveWithJoysticks;
import frc.robot.commands.Claw.ClawOpen;
import frc.robot.commands.Claw.BallDown;

public class AutonomousSandstorm extends CommandGroup {
	/**
	 * The autonomous command group for teleoperated control during Sandstorm/Autonomous.
	 */
	public AutonomousSandstorm() {
		// addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		addParallel(new DriveWithJoysticks());
	}

	// TODO: Make more efficient; maybe in one line. Perhaps call in AutonomousPeriodic?
	public void drive() {
		 DriveWithJoysticks driver = new DriveWithJoysticks();
		 driver.start();
	}
}
