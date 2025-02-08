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
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.Servo;
import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;

public class IO {
  private Alliance defaultAlliance = Alliance.Blue;
  private static CommandJoystick joystick;
  private static CommandJoystick throttle;
  private static CommandXboxController xboxController;
  private static DoubleSupplier zeroSupplier;
  private static Drivetrain drivetrain;
  private static Elevator elevator;
  private static Servo servo;

  public double getAsDouble(){
    return 0.0;
  }
  public static void init() {
    //Need to replace JOYSTICK, THROTTLE, and XBOX_CONTROLLER with port numbers
	  joystick = new CommandJoystick(JOYSTICK);
    throttle = new CommandJoystick(THROTTLE);
    xboxController = new CommandXboxController(XBOX_CONTROLLER);
    drivetrain= Drivetrain.getInstance();
    }

    public static void configure() {

		  drivetrain.setDefaultCommand(
        new Drive(Inputs::getJoystickX, Inputs::getJoystickY, Inputs::getJoystickTwist)
      );
      //   new joystick.button(2).whileTrue(new InstantCommand(() -> drvietrain.setGyroscopeZero(0)));
      // servo.setDefaultCommand(
      //   new InstantCommand(() -> servo.setAngle(0))
      // );
      // elevator.setDefaultCommand(
      //   new InstantCommand(() -> elevator.setElevatorSpeed(0))
      // );
    }

    public static Boolean getIsBlue () {
		return DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Blue : true;
	}
}
