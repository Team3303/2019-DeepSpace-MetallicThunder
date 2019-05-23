package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Button;
import static frc.robot.Robot.m_oi;

public class GamepadJoystick extends Button {
	int port;

	public GamepadJoystick(int port) {
		this.port = port;
	}

	@Override
	public boolean get() {
		return m_oi.gamepad.getRawAxis(port) > 0.1 && m_oi.gamepad.getRawAxis(port) < -0.1;
	}
}
