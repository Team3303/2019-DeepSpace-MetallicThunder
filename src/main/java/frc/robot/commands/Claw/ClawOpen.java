package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Claw;
import frc.robot.Robot;

public class ClawOpen extends InstantCommand {
	public ClawOpen() {
		super();
		//requires(Robot.claw);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.claw);
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		Robot.claw.clawOpen();
	}
}
