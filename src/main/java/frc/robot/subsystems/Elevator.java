package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 * Subsystem definition for thr Robot elevator.
 */
public class Elevator extends Subsystem {

  WPI_TalonSRX elevator;

  /**
   * Instantiates the Talon.
   */
  public Elevator(WPI_TalonSRX TalonSRX){
    this.elevator = TalonSRX;
  }

  /**
   * Makes this elevator move up. This is accomplished by setting the assigned Talon's output speed.
   * @param speed The speed to set. Value should be between -1.0 and 1.0.
   */
  public void elevatorUp(double speed) {
    elevator.set(speed);
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

}
