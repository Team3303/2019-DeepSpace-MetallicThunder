package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ElevatorSnapUp extends InstantCommand {
	// Called once when the command executes
	@Override
	protected void initialize() {
		// if(!Robot.isOnClaw) {
			Robot.elevator.setLevel(Robot.elevator.getLevel() + 1);
			Robot.elevator.targetLevel();
		// }
	}
}
