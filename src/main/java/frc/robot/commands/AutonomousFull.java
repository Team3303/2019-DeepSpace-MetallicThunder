/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
