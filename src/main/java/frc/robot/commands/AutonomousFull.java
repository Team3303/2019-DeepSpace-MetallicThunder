package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;
import frc.robot.commands.Claw.*;

public class AutonomousFull extends CommandGroup {
	/**
	 * The autonomous command group for full autonomy during Sandstorm/Autonomous.
	 */
	public AutonomousFull() {
		// addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		addParallel(new BallDown());
	}
}
