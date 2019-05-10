package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Robot;

public class TriggerButtonLeft extends Trigger {
	@Override
	public boolean get() {
 		return Robot.m_oi.getGamePadLeftTrigger() > 0.5;
	}
}
