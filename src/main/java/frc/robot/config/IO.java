package frc.robot.config;

import frc.robot.commands.actions.drivetrain.Drive;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.config.Inputs.*;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.Servo;
import static frc.robot.config.constants.PhysicalConstants.Drivetrain.*;
import static frc.robot.config.constants.PortConstants.Controller.*;

import frc.robot.commands.actions.CoralCorral.Gate.closeCoralGate;
import frc.robot.commands.actions.CoralCorral.Gate.openCoralGate;
import frc.robot.commands.actions.CoralCorral.Gate.setCoralCorral;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.commands.actions.elevator.SetElevator;
import frc.robot.commands.actions.elevator.jamElevator;
import frc.robot.commands.actions.elevator.moveElevator;
import frc.robot.commands.actions.CoralCorral.Gate.*;
import frc.robot.commands.actions.CoralCorral.Gate.changeCoralGate;
import frc.robot.commands.actions.CoralCorral.Gate.moveCoralCorral;
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
      joystick.button(0).onTrue(drivetrain.setGyroscopeZero(IMU_TO_ROBOT_FRONT_ANGLE));

      //Also need to replace the a's with correct buttons when Endeffector is assigned
      //Coral Gate Automatic Control
       xboxController.a().onTrue(new changeCoralGate());
      //Coral Corral automatic control
      xboxController.a().onTrue(new setCoralCorral(0).reef());
      xboxController.a().onTrue(new setCoralCorral(0).coral());
      //Elevator automatic control
      xboxController.a().onTrue(new SetElevator(0).Level_2());
      xboxController.a().onTrue(new SetElevator(0).Level_3());
      xboxController.a().onTrue(new SetElevator(0).Level_4());
      xboxController.a().onTrue(new SetElevator(0).Level_Coral());
      xboxController.a().onTrue(new jamElevator());
    
      //Coral Corral manual control
      /*(xboxController.getRightY()>0.25).whileTrue(new moveCoralCorral(0.25).moveCoralCorral());
      (xboxController.getRightY()>0.5).whileTrue(new moveCoralCorral(0.5));
      (xboxController.getRightY()>-0.25).whileTrue(new moveCoralCorral(-0.25));
      (xboxController.getRightY()>-0.5).whileTrue(new moveCoralCorral(-0.5));
      //Elevator manual control
      (xboxController.getLeftY()>0.25).whileTrue(new moveElevator(0.25));
      (xboxController.getLeftY()>0.5).whileTrue(new moveElevator(0.5));
      (xboxController.getLeftY()< -0.25).whileTrue(new moveElevator(-0.25));
      (xboxController.getLeftY()< -0.5).whileTrue(new moveElevator(-0.5));
      (xboxController.getLeftY() ).whileTrue(new moveElevator(0));
      */
    }

    public static Boolean getIsBlue () {
		return DriverStation.getAlliance().isPresent() ? DriverStation.getAlliance().get() == Alliance.Blue : true;
	}
}
