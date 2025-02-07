package frc.robot.config;

import frc.robot.commands.actions.drivetrain.Drive;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.config.Inputs.*;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;

public class IO {
  private Alliance defaultAlliance = Alliance.Blue;
  private static CommandJoystick joystick;
  private static CommandJoystick throttle;
  private static CommandXboxController xboxController;
  private static DoubleSupplier zeroSupplier = new DoubleSupplier();
  private static Drivetrain drivetrain;

  public dobule getAsDouble(){
    return 0.0;
  }
  public static void init() {
    //Need to replace JOYSTICK, THROTTLE, and XBOX_CONTROLLER with port numbers
	  joystick = new CommandJoystick(JOYSTICK);
    throttle = new CommandJoystick(THROTTLE);
    xboxController = new CommandXboxController(XBOX_CONTROLLER);
    }

    public static void configure() {
      init();
      Inputs.init();

		  drivetrain.setDefaultCommand(
			  new Drive(Inputs::getJoystickX, Inputs::getJoystickY, Inputs::getJoystickTwist)
      );
         drivetrain.setDefaultCommand(
             new Drive(Inputs::getLeftControllerXSwerve, Inputs::getLeftControllerYSwerve, Inputs::getRightControllerXSwerve)
         );

      joystick.button(0).whileTrue(drivetrain.setGyroscopeZero(IMU_TO_ROBOT_FRONT_ANGLE));

      joystick.button(1).whileTrue(new InstantCommand(() -> drivetrain.getSwerveDrive().resetPosition(
        new Pose2d(0, 0, Rotation2d.fromDegrees(0))
      )));
    }

    public static Boolean getIsBlue () {
		return DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Blue : true;
	}
}
