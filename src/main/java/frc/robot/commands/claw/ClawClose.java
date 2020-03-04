package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Claw;
import frc.robot.Robot;

public class ClawClose extends InstantCommand {
	public ClawClose() {
		super();
		//requires(Robot.claw);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		Robot.claw.clawClose();
	}
}

