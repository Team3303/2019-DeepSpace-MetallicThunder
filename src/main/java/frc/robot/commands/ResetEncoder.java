package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;

/**
 * Resets the encoder.
 */
public class ResetEncoder extends InstantCommand {

  Elevator levater;

  /**
   * I ain't sayin' nothin'.
   */
  public ResetEncoder(Elevator selectedElevator) {
    super();
    requires(selectedElevator);
    levater = selectedElevator;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    levater.setEncoderPos(0);
  }

}
