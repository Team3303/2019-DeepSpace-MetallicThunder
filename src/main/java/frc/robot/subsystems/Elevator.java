package frc.robot.subsystems; 

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.RobotMap.*;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import frc.robot.Robot;
/**
 * Subsystem definition for thr Robot elevator.
 */
public class Elevator extends Subsystem {
  public float[] ballLevels = new float[] {
    (0),
    (27.5f),
    (55.5f),
    (83.5f),
    (111.5f),
  };
 public float[] hatchLevels = new float[] {
    (0),
    (19f),
    (67f),
    (86f),
  };
  public float cir;

  private int level;
  WPI_TalonSRX elevator;
  SensorCollection encoder;

  /**
   * Instantiates the Talon.
   */
  public Elevator(WPI_TalonSRX talon){
    this.elevator = talon;
    encoder = talon_EB.getSensorCollection();
    level = 0;
    if (Robot.isCompRobot) {
      cir = 7.493f;
    } else {
      cir = 2.749f;
    }
  }

  /**
   * Makes this elevator move up. This is accomplished by setting the assigned Talon's output speed.
   * @param speed The speed to set. Value should be between -1.0 and 1.0.
   */
  public void set(double speed) {
    elevator.set(speed);
  }

  /**
   * Makes this elevator move to target posistion. Using some Motion Magic and magic numbers, of course.
   * @param targetPos The position to target. Value should be a magc number.
   */
  public void target(double targetPos) {

    elevator.set(ControlMode.MotionMagic, targetPos);
  }
  public void targetLevel() {
    int ticks;
    if (Robot.isOnClaw) {
      ticks = (int)((hatchLevels[level] * 4096 * 36) / (cir * 15));
    } else {
      ticks = (int)((ballLevels[level] * 4096 * 36) / (cir * 15));
    }
    elevator.set(ControlMode.MotionMagic, ticks);
  }

  /**
   * Stops the elevator. Sets speed to 0.
   */
  public void stop() {
    elevator.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setEncoderPos(int sensorPos) {
    elevator.setSelectedSensorPosition(sensorPos);
  }

  public void setLevel(int level) {
    this.level = level;
    this.checkLevel();
  }

  public float getLevelHeight() {
    this.checkLevel();
    if(Robot.isOnClaw)
      return hatchLevels[this.level];
    else
      return ballLevels[this.level];
  }

  public int getLevel() {
    return this.level;
  }

  private void checkLevel() {
    if(Robot.isOnClaw) {
      if(level < 0)
        level = 0;
      if(level > 2)
        level = 2;
    } else {
      if(level < 0)
        level = 0;
      if(level > 4)
        level = 4;
    }
  }
}