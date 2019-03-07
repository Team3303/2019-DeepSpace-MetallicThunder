/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Claw;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.Claw;
import frc.robot.Robot;
//import static frc.robot.Robot

/**
 * Add your docs here.
 */
public class ClawOpen extends InstantCommand {
  /**
   * Add your docs here.
   */
  public ClawOpen() {
    super();
    //requires(Robot.claw);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.claw);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.claw.clawOpen();
  }

}
