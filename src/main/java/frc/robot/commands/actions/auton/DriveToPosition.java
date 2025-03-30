// package frc.robot.commands.actions.auton;

// package frc.robot.commands.actions.drivetrain.auto;

// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.kinematics.ChassisSpeeds;
// import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
// import edu.wpi.first.wpilibj2.command.Command;

// import static frc.robot.config.constants.PIDConstants.Drivetrain.ANGLE_CONSTRAINTS;
// import static frc.robot.config.constants.PIDConstants.Drivetrain.POS_CONSTRAINTS;
// import static frc.robot.config.constants.PhysicalConstants.Drivetrain.IMU_TO_ROBOT_FRONT_ANGLE;

// import frc.robot.Utility;
// import frc.robot.subsystems.Vision;
// import frc.robot.subsystems.CommandSwerveDrivetrain;

// /** An example command that uses an example subsystem. */
// public class DriveToPosition extends Command {
//   private CommandSwerveDrivetrain drivetrain;
//   private Vision vision;

//   private double wantedX;
//   private double wantedY;
//   private double wantedTheta;

//   private double maxSpeed, maxRotate, maxAcceleration;

//   public DriveToPosition(double wantedX, double wantedY, double wantedTheta) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     drivetrain =  CommandSwerveDrivetrain.getInstance();
//     vision = Vision.getInstance();

//     this.wantedX = wantedX;
//     this.wantedY = wantedY;
//     this.wantedTheta = wantedTheta;

//     this.maxSpeed = POS_CONSTRAINTS.maxVelocity;
//     this.maxRotate = ANGLE_CONSTRAINTS.maxVelocity;
//     this.maxAcceleration = POS_CONSTRAINTS.maxAcceleration;

//     addRequirements(drivetrain, vision);
//   }

//   public DriveToPosition(double wantedX, double wantedY, double wantedTheta, double maxSpeed) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     drivetrain =  CommandSwerveDrivetrain.getInstance();
//     vision = Vision.getInstance();

//     this.wantedX = wantedX;
//     this.wantedY = wantedY;
//     this.wantedTheta = wantedTheta;

//     this.maxSpeed = maxSpeed;
//     this.maxRotate = ANGLE_CONSTRAINTS.maxVelocity;
//     this.maxAcceleration = POS_CONSTRAINTS.maxAcceleration;

//     addRequirements(drivetrain, vision);
//   }

//   public DriveToPosition(double wantedX, double wantedY, double wantedTheta, double maxSpeed, double maxRotate) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     drivetrain =  CommandSwerveDrivetrain.getInstance();
//     vision = Vision.getInstance();

//     this.wantedX = wantedX;
//     this.wantedY = wantedY;
//     this.wantedTheta = wantedTheta;

//     this.maxSpeed = maxSpeed;
//     this.maxRotate = maxRotate;
//     this.maxAcceleration = POS_CONSTRAINTS.maxAcceleration;

//     addRequirements(drivetrain, vision);
//   }

//   public DriveToPosition(double wantedX, double wantedY, double wantedTheta, double maxSpeed, double maxRotate, double maxAcceleration) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     drivetrain =  CommandSwerveDrivetrain.getInstance();
//     vision = Vision.getInstance();

//     this.wantedX = wantedX;
//     this.wantedY = wantedY;
//     this.wantedTheta = wantedTheta;

//     this.maxSpeed = maxSpeed;
//     this.maxRotate = maxRotate;
//     this.maxAcceleration = maxAcceleration;

//     addRequirements(drivetrain, vision);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     drivetrain.getXController().reset(drivetrain.getPose().getX());
// 	  drivetrain.getYController().reset(drivetrain.getPose().getY());
// 	  drivetrain.getRotateController().reset(drivetrain.getRotation2d().getDegrees());

//     drivetrain.getYController().calculate(drivetrain.getPose().getY(), wantedY);
//     drivetrain.getXController().calculate(drivetrain.getPose().getX(), wantedX);
//     drivetrain.calculateRotateController(wantedTheta + IMU_TO_ROBOT_FRONT_ANGLE);

//     drivetrain.getXController().setConstraints(new Constraints(maxSpeed, maxAcceleration));
//     drivetrain.getYController().setConstraints(new Constraints(maxSpeed, maxAcceleration));
//     drivetrain.getRotateController().setConstraints(new Constraints(maxRotate, ANGLE_CONSTRAINTS.maxAcceleration));
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {

//       drivetrain.setDrive(
//         ChassisSpeeds.fromFieldRelativeSpeeds(
//           drivetrain.getYController().calculate(drivetrain.getPose().getY(), wantedY),
//           -drivetrain.getXController().calculate(drivetrain.getPose().getX(), wantedX), 
//           // -Utility.clamp(drivetrain.calculateRotateController(wantedTheta + IMU_TO_ROBOT_FRONT_ANGLE), 3),
//           -drivetrain.calculateRotateController(wantedTheta + IMU_TO_ROBOT_FRONT_ANGLE),
//           drivetrain.getRotation2d()
//         )
//       );

//       if(vision.hasTag()) {
//         drivetrain.setRobotCameraPosition(vision.getRobotPosition());
//     }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     drivetrain.setDrive(
//       new ChassisSpeeds(0, 0, 0)
//     );
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//       return false;
//   }
// }

package frc.robot.commands.actions.auton;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;

public class DriveToPosition extends Command {
    private final CommandSwerveDrivetrain drivetrain;
    private final double wantedX;
    private final double wantedY;
    private final double wantedTheta; // In degrees
    private final PIDController xController;
    private final PIDController yController;
    private final PIDController thetaController;

    private static final double POSITION_TOLERANCE = 0.05; // Meters
    private static final double ANGLE_TOLERANCE = 2.0; // Degrees

    public DriveToPosition(CommandSwerveDrivetrain drivetrain, double wantedX, double wantedY, double wantedTheta) {
        this.drivetrain = drivetrain;
        this.wantedX = wantedX;
        this.wantedY = wantedY;
        this.wantedTheta = wantedTheta;

        // Initialize PID controllers
        xController = new PIDController(1.0, 0.0, 0.0); // Tune these gains
        yController = new PIDController(1.0, 0.0, 0.0); // Tune these gains
        thetaController = new PIDController(1.0, 0.0, 0.0); // Tune these gains
        thetaController.enableContinuousInput(-180.0, 180.0); // Handle angle wrapping

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        xController.reset();
        yController.reset();
        thetaController.reset();
    }

    @Override
    public void execute() {
        double currentX = drivetrain.getPose().getX();
        double currentY = drivetrain.getPose().getY();
        double currentTheta = drivetrain.getRotation2d().getDegrees();

        // Calculate PID outputs
        double xSpeed = xController.calculate(currentX, wantedX);
        double ySpeed = yController.calculate(currentY, wantedY);
        double omega = thetaController.calculate(currentTheta, wantedTheta);

        // Drive the robot
        drivetrain.driveRelative(xSpeed, ySpeed, omega);
    }

    @Override
    public boolean isFinished() {
        // Check if the robot is within the position and angle tolerances
        double currentX = drivetrain.getPose().getX();
        double currentY = drivetrain.getPose().getY();
        double currentTheta = drivetrain.getRotation2d().getDegrees();
        boolean positionReached = Math.abs(currentX - wantedX) < POSITION_TOLERANCE &&
                                  Math.abs(currentY - wantedY) < POSITION_TOLERANCE;
        boolean angleReached = Math.abs(currentTheta - wantedTheta) < ANGLE_TOLERANCE;
        return positionReached && angleReached;
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drivetrain
        drivetrain.driveRelative(0, 0, 0);
    }
}