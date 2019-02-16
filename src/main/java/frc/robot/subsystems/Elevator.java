package frc.robot.subsystems; 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.RobotMap.*;
import com.ctre.phoenix.motorcontrol.SensorCollection;
/**
 * Subsystem definition for thr Robot elevator.
 */
public class Elevator extends Subsystem {
  public enum ballLevels {
    floor(120.32347),
    level1(32764589.48937),
    cargoHold(32764589.48937),
    level2(32764589.48937),
    level3(32764589.48937);
    public final double value;
    ballLevels(double description) {
      value = description;
    }
  }
 public enum hatchLevels {
    level1(120.32347),
    level2(32764589.48937),
    level3(32764589.48937);
    public final double value;
    hatchLevels(double description) {
      value = description;
    }
  }

  int level;
  WPI_TalonSRX elevator;
  SensorCollection encoder;

  /**
   * Instantiates the Talon.
   */
  public Elevator(WPI_TalonSRX talon){
    this.elevator = talon;
    encoder = talon_EB.getSensorCollection();
    level = 0;
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
}