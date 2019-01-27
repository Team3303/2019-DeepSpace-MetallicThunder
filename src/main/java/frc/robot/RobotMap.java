/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  public static final int WHEEL_CONTROLLER_PORT = 0;
  public static final int TALON_FL_ID = 1;
  public static final int TALON_BL_ID = 2;
  public static final int TALON_FR_ID = 3;
  public static final int TALON_BR_ID = 4;
  //public static final int NOTHING = voidnullnada;

  public static WPI_TalonSRX talon_FL;
  public static WPI_TalonSRX talon_FR;
  public static WPI_TalonSRX talon_BL;
  public static WPI_TalonSRX talon_BR;
  public static PigeonIMU pigeon;

  public static DoubleSolenoid clawSolenoid;

  public static void init(){
    talon_FL = new WPI_TalonSRX(RobotMap.TALON_FL_ID);
    talon_FR = new WPI_TalonSRX(RobotMap.TALON_FR_ID);
    talon_BL = new WPI_TalonSRX(RobotMap.TALON_BL_ID);
    talon_BR = new WPI_TalonSRX(RobotMap.TALON_BR_ID);
    pigeon = new PigeonIMU(0);
  } 
  public static void outputValues(){
    double [] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    SmartDashboard.putNumber("Pigeon Angle", ypr[0]);
  }



}