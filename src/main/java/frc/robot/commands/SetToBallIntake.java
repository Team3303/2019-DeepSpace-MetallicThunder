package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class SetToBallIntake extends InstantCommand {
	/**
	 * Add your docs here.
	 */
	public SetToBallIntake() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.ballIntake);
		requires(Robot.claw);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		Robot.isOnClaw = false;
	}
}
