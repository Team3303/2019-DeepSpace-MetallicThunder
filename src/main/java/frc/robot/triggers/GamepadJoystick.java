/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Button;
import static frc.robot.Robot.m_oi;

/**
 * Add your docs here.
 */
public class GamepadJoystick extends Button{

    int port;

    public GamepadJoystick(int port) {
        this.port = port;
    }

    @Override
    public boolean get() {
        return m_oi.gamepad.getRawAxis(port) > 0.1 && m_oi.gamepad.getRawAxis(port) < -0.1;
    }
}
