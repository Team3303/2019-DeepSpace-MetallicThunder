/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joysick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  Joystick gamepad = new Joystick(0);
  Joystick joystick_left = new Joystick(1);
  Joystick joystick_right = new Joystick(2);
  Button button1 = new JoystickButton(gamepad, 1);
  
  // LEFT JOYSTICK
  public double getLeftJoystickX(){ return joystick_left.getX(); }
  public double getLeftJoystickY() { return joystick_left.getY(); }
  public double getLeftJoystickZ() { return joystick_left.getZ(); }
  public boolean getLeftJoystick1() { return joystick_left.getRawButton(1); }
  public boolean getLeftJoystick2() { return joystick_left.getRawButton(2); }
  public boolean getLeftJoystick3() { return joystick_left.getRawButton(3); }
  public boolean getLeftJoystick4() { return joystick_left.getRawButton(4); }
  public boolean getLeftJoystick5() { return joystick_left.getRawButton(5); }
  public boolean getLeftJoystick6() { return joystick_left.getRawButton(6); }
  public boolean getLeftJoystick7() { return joystick_left.getRawButton(7); }
  public boolean getLeftJoystick8() { return joystick_left.getRawButton(8); }
  public boolean getLeftJoystick9() { return joystick_left.getRawButton(9); }
  public boolean getLeftJoystick10() { return joystick_left.getRawButton(10); }
  public boolean getLeftJoystick11() { return joystick_left.getRawButton(11); }
  public boolean getLeftJoystick12() { return joystick_left.getRawButton(12); }

  // RIGHT JOYSTICK - GENERATED WITH A RAD BOOMER VIM MACRO USING LEFT JOYSTICK
  public double getRightJoystickX() { return joystick_right.getX(); }
  public double getRightJoystickY() { return joystick_right.getY(); }
  public double getRightJoystickZ() { return joystick_right.getZ(); }
  public boolean getRightJoystick1() { return joystick_right.getRawButton(1); }
  public boolean getRightJoystick2() { return joystick_right.getRawButton(2); }
  public boolean getRightJoystick3() { return joystick_right.getRawButton(3); }
  public boolean getRightJoystick4() { return joystick_right.getRawButton(4); }
  public boolean getRightJoystick5() { return joystick_right.getRawButton(5); }
  public boolean getRightJoystick6() { return joystick_right.getRawButton(6); }
  public boolean getRightJoystick7() { return joystick_right.getRawButton(7); }
  public boolean getRightJoystick8() { return joystick_right.getRawButton(8); }
  public boolean getRightJoystick9() { return joystick_right.getRawButton(9); }
  public boolean getRightJoystick10() { return joystick_right.getRawButton(10); }
  public boolean getRightJoystick11() { return joystick_right.getRawButton(11); }
  public boolean getRightJoystick12() { return joystick_right.getRawButton(12); }

  // This constructor is to define macros for the Joystick and Gamepad buttons.
  public OI() {
  
  }
}
