package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.triggers.TriggerButtonRight;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.Elevator.*;
import frc.robot.commands.Drive.*;
import frc.robot.triggers.TriggerButtonLeft;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	// Additionally, by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	Joystick gamepad = new Joystick(0);
	TriggerButtonRight gamepadTriggerButtonRight;
	TriggerButtonLeft gamepadTriggerButtonLeft;

	Joystick joystick_left = new Joystick(1);
	Joystick joystick_right = new Joystick(2);


	Command driveInverse;

	Button leftJoystickButton1 = new JoystickButton(joystick_left, 1);
	Button leftJoystickButton2 = new JoystickButton(joystick_left, 2);
	Button leftJoystickButton3 = new JoystickButton(joystick_left, 3);
	Button leftJoystickButton4 = new JoystickButton(joystick_left, 4);
	Button leftJoystickButton5 = new JoystickButton(joystick_left, 5);
	Button leftJoystickButton6 = new JoystickButton(joystick_left, 6);
	Button leftJoystickButton7 = new JoystickButton(joystick_left, 7);
	Button leftJoystickButton8 = new JoystickButton(joystick_left, 8);
	Button leftJoystickButton9 = new JoystickButton(joystick_left, 9);
	Button leftJoystickButton10 = new JoystickButton(joystick_left, 10);
	Button leftJoystickButton11 = new JoystickButton(joystick_left, 11);
	Button leftJoystickButton12 = new JoystickButton(joystick_left, 12);

	Button rightJoystickButton1 = new JoystickButton(joystick_right, 1);
	Button rightJoystickButton2 = new JoystickButton(joystick_right, 2);
	Button rightJoystickButton3 = new JoystickButton(joystick_right, 3);
	Button rightJoystickButton4 = new JoystickButton(joystick_right, 4);
	Button rightJoystickButton5 = new JoystickButton(joystick_right, 5);
	Button rightJoystickButton6 = new JoystickButton(joystick_right, 6);
	Button rightJoystickButton7 = new JoystickButton(joystick_right, 7);
	Button rightJoystickButton8 = new JoystickButton(joystick_right, 8);
	Button rightJoystickButton9 = new JoystickButton(joystick_right, 9);
	Button rightJoystickButton10 = new JoystickButton(joystick_right, 10);
	Button rightJoystickButton11 = new JoystickButton(joystick_right, 11);
	Button rightJoystickButton12 = new JoystickButton(joystick_right, 12);

	Button gamepadButton1 = new JoystickButton(gamepad, 1);
	Button gamepadButton2 = new JoystickButton(gamepad, 2);
	Button gamepadButton3 = new JoystickButton(gamepad, 3);
	Button gamepadButton4 = new JoystickButton(gamepad, 4);
	Button gamepadButton5 = new JoystickButton(gamepad, 5);
	Button gamepadButton6 = new JoystickButton(gamepad, 6);
	Button gamepadButton7 = new JoystickButton(gamepad, 7);
	Button gamepadButton8 = new JoystickButton(gamepad, 8);
	Button gamepadButton9 = new JoystickButton(gamepad, 9);
	Button gamepadButton10 = new JoystickButton(gamepad, 10);
	Button gamepadButton11 = new JoystickButton(gamepad, 11);
	Button gamepadButton12 = new JoystickButton(gamepad, 12);
	Button gamepadPOVL = new POVButton(gamepad, 270);
	Button gamepadPOVR = new POVButton(gamepad, 90);
	Button gamepadPOVU = new POVButton(gamepad, 0);
	Button gamepadPOVD = new POVButton(gamepad, 180);

	// GAMEPAD TRIGGERS
	public double getGamePadRightTrigger() { return gamepad.getRawAxis(3); }
	public double getGamePadLeftTrigger() { return gamepad.getRawAxis(2); }
	// LEFT JOYSTICK
	public double getLeftJoystickX() { return joystick_left.getX(); }
	public double getLeftJoystickY() { return joystick_left.getY(); }
	public double getLeftJoystickZ() { return joystick_left.getZ(); }
	// RIGHT JOYSTICK
	public double getRightJoystickX() { return joystick_right.getX(); }
	public double getRightJoystickY() { return joystick_right.getY(); }
	public double getRightJoystickZ() { return joystick_right.getZ(); }
	// This constructor is to define macros for the Joystick and Gamepad buttons.
	public OI() { }

	void init() {
		gamepadTriggerButtonRight = new TriggerButtonRight();
		gamepadTriggerButtonLeft = new TriggerButtonLeft();

		driveInverse = new DriveWithJoysticksInverted();

		rightJoystickButton1.whileHeld(new Outtake());
		rightJoystickButton5.whileHeld(driveInverse);

		gamepadPOVL.whenPressed(new SetToClaw());
		gamepadPOVR.whenPressed(new SetToBallIntake());
		gamepadPOVU.whileHeld(new ElevatorUp());
		gamepadPOVD.whileHeld(new ElevatorDown());
		gamepadButton5.whileHeld(new Intake());
		gamepadButton6.whileHeld(new Outtake());
		gamepadButton8.whenPressed(new ResetEncoder(Robot.elevator));

//		gamepadTriggerButtonLeft.whenActive(new );
		gamepadTriggerButtonRight.whenActive(new ClawToggle());

//		gamepadButton1.whenPressed(new TimedDriveFoward());
	}
}
