package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ElevatorSnapDown extends InstantCommand {
	public ElevatorSnapDown() {
		super();
	}

	// Called once when the command executes
	@Override
	protected void initialize() {
		// if(!Robot.isOnClaw) {
			Robot.elevator.setLevel(Robot.elevator.getLevel() - 1);
			Robot.elevator.targetLevel();
		// }
	    
		// if(Robot.isOnClaw)
		//	new ElevatorSetPos(Robot.elevatorClaw.getLevel());
		// if(!Robot.isOnClaw)
		//	new ElevatorSetPos(Robot.elevator.getLevel());
	}

}
