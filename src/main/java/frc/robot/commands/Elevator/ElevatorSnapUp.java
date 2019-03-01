/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.InstantCommand;

import frc.robot.commands.Elevator.ElevatorSetPos;
import frc.robot.Robot;
import frc.robot.Robot.*;

/**
 * Add your docs here.
 */
public class ElevatorSnapUp extends InstantCommand {
  /**
   * Takes nothing.
   */
  public ElevatorSnapUp() { super(); }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(Robot.isOnClaw) {
      Robot.elevatorHatch.setLevel(Robot.elevatorHatch.getLevel() + 1);
      Robot.elevatorHatch.targetLevel();
    }
    if(!Robot.isOnClaw) {
      Robot.elevatorBall.setLevel(Robot.elevatorBall.getLevel() + 1);
      Robot.elevatorBall.targetLevel();
    }
  }

}
