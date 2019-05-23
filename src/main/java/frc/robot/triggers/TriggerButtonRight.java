package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.OI;
import frc.robot.Robot;

public class TriggerButtonRight extends Trigger {
	@Override
	public boolean get() {
		return Robot.m_oi.getGamePadRightTrigger() > 0.5;
	}
}
