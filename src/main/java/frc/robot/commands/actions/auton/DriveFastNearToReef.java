// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.actions.auton;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.config.constants.PhysicalConstants.limeLightConstants.*;

import frc.robot.RobotContainer;
import frc.robot.Utility;
import frc.robot.config.constants.PortConstants;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.CommandSwerveDrivetrain;

/** An example command that uses an example subsystem. */
public class DriveFastNearToReef extends Command {
  private CommandSwerveDrivetrain drivetrain;
  private Vision vision;

  private Timer timer;

  private boolean isRight;

  private final PIDController xController;
  private final PIDController yController;
  private final PIDController thetaController;

  public DriveFastNearToReef(boolean isRight) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = RobotContainer.getSwerveDrivetrain();
    vision = Vision.getInstance();

    timer = new Timer();

    this.isRight = isRight;

    xController = new PIDController(1.0, 0.0, 0.0);
    yController = new PIDController(1.0, 0.0, 0.0);
    thetaController = new PIDController(0.03, 0.0, 0.0);

    addRequirements(drivetrain, vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();

    // xController.reset(vision.getRelativeRobotPosition().getX());
	  // drivetrain.getYController().reset(vision.getRelativeRobotPosition().getY());
	  // drivetrain.getRotateController().reset(vision.getRelativeRobotPosition().getRotation().getDegrees());
    // drivetrain.getRotateController().reset(vision.getPicturePosition()[0]);

    // drivetrain.getYController().calculate(vision.getRelativeRobotPosition().getY(), -0.3);
    // drivetrain.getXController().calculate(vision.getRelativeRobotPosition().getX(), isRight ? CAMERA_RIGHT_OFFSET : CAMERA_LEFT_OFFSET);
    // drivetrain.getRotateController().calculate(vision.getPicturePosition()[0], 0);

    // drivetrain.getXController().setConstraints(new Constraints(3, 6));
    // drivetrain.getYController().setConstraints(new Constraints(3, 6));
    // drivetrain.getRotateController().setConstraints(new Constraints(100, ANGLE_CONSTRAINTS.maxAcceleration));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(vision.getRelativeRobotPosition().toString());

    double xValue = isRight ? CAMERA_RIGHT_OFFSET : CAMERA_LEFT_OFFSET;

    if(vision.hasTag()) {

      drivetrain.drive(
          -Utility.clamp(yController.calculate(vision.getRelativeRobotPosition().getY(), -0.3), 3),
          Utility.clamp(xController.calculate(vision.getRelativeRobotPosition().getX(), xValue), 3),
          Utility.clamp(thetaController.calculate(vision.getRelativeRobotPosition().getRotation().getDegrees(), 0), 6)
      );
    }
  
    else {
      // drivetrain.getXController().reset(vision.getRelativeRobotPosition().getX());
      // drivetrain.getYController().reset(vision.getRelativeRobotPosition().getY());
      // drivetrain.getRotateController().reset(vision.getRelativeRobotPosition().getRotation().getDegrees());
      // drivetrain.getRotateController().reset(vision.getPicturePosition()[0]);
  
      // drivetrain.setDrive(
      //   ChassisSpeeds.fromFieldRelativeSpeeds(
      //     0, 0, 0,
      //     drivetrain.getRotation2d()
      //   )
      // );
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      drivetrain.driveRelative(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      // return drivetrain.getXController().atSetpoint() 
      // && drivetrain.getYController().atSetpoint() 
      // && drivetrain.getRotateController().atSetpoint();
        // return drivetrain.getXController().getPositionError() < 0.03 &&
        // drivetrain.getYController().getPositionError() < 0.03 &&
        // drivetrain.getRotateController().getPositionError() < 3;

        // return isAtGoal;

        // return timer.get() > 1.3 &&
        // drivetrain.getSpeed().vxMetersPerSecond < 0.01 &&
        // drivetrain.getSpeed().vxMetersPerSecond < 0.01 &&
        // drivetrain.getSpeed().omegaRadiansPerSecond < 0.3;

        return timer.get() > 1.3;
  }
}