// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import frc.robot.commands.actions.moveCoralCorral;
import frc.robot.commands.actions.moveElevator;
import frc.robot.config.constants.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.commands.actions.moveClimber;
import frc.robot.commands.actions.auton.OnePiece;
import frc.robot.commands.actions.auton.Taxi;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.closeCoralGate;
import frc.robot.commands.actions.setElevator; 
import frc.robot.commands.actions.setCoralCorral;
public class RobotContainer {
    private double MaxSpeed = TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
    private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

    /* Setting up bindings for necessary control of the swerve drive platform */
    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

    private final Telemetry logger = new Telemetry(MaxSpeed);

    private static final CommandJoystick joystick = new CommandJoystick(0);
    private static CommandXboxController xboxController = new CommandXboxController(3);

    public static final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

    private static SendableChooser<Command> autoChooser;

    public RobotContainer() {
        configureBindings();
        configureAuto();
    }



    private void configureBindings() {
        // Note that X is defined as forward according to WPILib convention,
        // and Y is defined as to the left according to WPILib convention.
        drivetrain.setDefaultCommand(
            // Drivetrain will execute this command periodically
            drivetrain.applyRequest(() ->
                drive.withVelocityX(-joystick.getY() * MaxSpeed * (((-joystick.getThrottle() + 1 ) / 2) + 0.1)) // Drive forward with negative Y (forward)
                    .withVelocityY(-joystick.getX() * MaxSpeed * (((-joystick.getThrottle() + 1 ) / 2) + 0.1)) // Drive left with negative X (left)
                    .withRotationalRate(-joystick.getTwist() * MaxAngularRate * (((-joystick.getThrottle() + 1 ) / 2) + 0.1)) // Drive counterclockwise with negative X (left)
            )
        );

        // joystick.a().whileTrue(drivetrain.applyRequest(() -> brake));
        // joystick.b().whileTrue(drivetrain.applyRequest(() ->
            point.withModuleDirection(new Rotation2d(-joystick.getY(), -joystick.getX()));
        // ));

        // Run SysId routines when holding back/start and X/Y.
        // Note that each routine should be run exactly once in a single log.
        // joystick.top().whileTrue(drivetrain.sysIdDynamic(Direction.kForward));
        // joystick.trigger().whileTrue(drivetrain.sysIdDynamic(Direction.kReverse));
        // joystick.top().whileTrue(drivetrain.sysIdQuasistatic(Direction.kForward));
        // joystick.trigger().whileTrue(drivetrain.sysIdQuasistatic(Direction.kReverse));

        // reset the field-centric heading on left bumper press

        // change to button on joystick
        joystick.trigger().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldCentric()));
        
        //Left Joystick trigger should do seedFieldCentric

        //new Trigger(() -> xboxController.getRightY() > 0.25).whileTrue(new moveCoralCorral(-0.75));
        //new Trigger(() -> xboxController.getRightY() > 0.05).whileTrue(new moveCoralCorral(xboxController.getRightY()));
        //System.out.println("xbox: " + xboxController.getRightY());
        //new Trigger(() -> xboxController.getRightY() < -0.25).whileTrue(new moveCoralCorral(0.75));
        //new Trigger(() -> xboxController.getRightY() < -0.05).whileTrue(new moveCoralCorral(xboxController.getRightY()));


        new Trigger(() -> xboxController.getRightY() > 0.05).whileTrue(new moveCoralCorral(xboxController));
        new Trigger(() -> xboxController.getRightY() < -0.05).whileTrue(new moveCoralCorral(xboxController));
        drivetrain.registerTelemetry(logger::telemeterize);
        

        new Trigger(() -> xboxController.getLeftY() > 0.05).whileTrue(new moveElevator(xboxController));
        new Trigger(() -> xboxController.getLeftY() < -0.05).whileTrue(new moveElevator(xboxController));
        
        xboxController.start().onTrue(setElevator.Ground());
        xboxController.a().onTrue(setElevator.L1());
        xboxController.x().onTrue(setElevator.L2());
        xboxController.y().onTrue(setElevator.L3());
        xboxController.b().onTrue(setElevator.L4());

        xboxController.pov(180).onTrue(setCoralCorral.dump());
        xboxController.pov(0).onTrue(setCoralCorral.intake());

        // xboxController.pov(0).onTrue(setCoralCorral.dump());
        // xboxController.pov(180).onTrue(setCoralCorral.intake());

        xboxController.rightBumper().onTrue(new moveClimber(0.25));   
        xboxController.leftBumper().onTrue(new moveClimber(-0.25)); 
        
        new Trigger (() -> xboxController.getRightTriggerAxis()>0.05).onTrue(new openCoralGate());
        new Trigger (() -> xboxController.getLeftTriggerAxis()>0.05).onTrue(new closeCoralGate());  
        //A,B,X, & Y used for setpositions for elevator & arm simultaniously
    }

    public void configureAuto() {
        autoChooser = new SendableChooser<Command>();

		autoChooser.setDefaultOption("nothing", null);
		
		autoChooser.addOption("Timed Taxi", new Taxi());

        autoChooser.addOption("One Piece", new OnePiece());

		SmartDashboard.putData("Auton Chooser", autoChooser);
    }

    public Command getAutonomousCommand() {
        // return Commands.print("No autonomous command configured");
        return autoChooser.getSelected();
    }

    public static double getxboxRightJoy(){
        return xboxController.getRightY();
    }

    public static double getxboxLeftJoy(){
        return xboxController.getLeftY();
    }

    public static CommandSwerveDrivetrain getSwerveDrivetrain() {
        return drivetrain;
    }
}
