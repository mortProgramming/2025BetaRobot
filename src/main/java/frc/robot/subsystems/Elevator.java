package frc.robot.subsystems;

import static frc.robot.config.constants.PIDConstants.ElevatorPID.KA;
import static frc.robot.config.constants.PIDConstants.ElevatorPID.KD;
import static frc.robot.config.constants.PIDConstants.ElevatorPID.KG;
import static frc.robot.config.constants.PIDConstants.ElevatorPID.KI;
import static frc.robot.config.constants.PIDConstants.ElevatorPID.KP;
import static frc.robot.config.constants.PIDConstants.ElevatorPID.KS;
import static frc.robot.config.constants.PIDConstants.ElevatorPID.KV;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.motorVoltage;
import static frc.robot.config.constants.PortConstants.Elevator.BOTTOM_LIMIT;
import static frc.robot.config.constants.PortConstants.Elevator.ELEVATOR_FOLLOWER;
import static frc.robot.config.constants.PortConstants.Elevator.ELEVATOR_MASTER;
import static frc.robot.config.constants.PortConstants.Elevator.MAX_ACCELERATION;
import static frc.robot.config.constants.PortConstants.Elevator.MAX_VELOCITY;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Elevator extends SubsystemBase{    
        private static Elevator elevator;
    
        private SparkMax driveNeoMaster;
        private SparkMax driveNeoFollower;
    
        private SparkMaxConfig driveConfigMaster;
        private SparkMaxConfig driveConfigureFollower;
        private SparkClosedLoopController pidController;
        private double motorSpeed=0;
    
        private ProfiledPIDController positionController;
        private ElevatorFeedforward feedforward;
    
        private double setpoint;
        public static int elevation=0; /*Used for setPosition */
        private Elevator(){
            driveNeoMaster = new SparkMax(ELEVATOR_MASTER, MotorType.kBrushless);
            driveNeoFollower = new SparkMax(ELEVATOR_FOLLOWER, MotorType.kBrushless);
           
            driveConfigMaster = new SparkMaxConfig();
            driveConfigureFollower = new SparkMaxConfig();
            
            SparkBase.ResetMode resetMode = ResetMode.kNoResetSafeParameters;
            SparkBase.PersistMode persistMode = PersistMode.kNoPersistParameters;
            //Why is driveNeoMaster.configure set to configure to the follower?
            driveConfigMaster.closedLoop
                .p(KP)
                .i(KI)
                .d(KD);

            // driveConfigMaster.closedLoop.maxMotion
            //     .maxVelocity(MAX_VELOCITY)
            //     .maxAcceleration(MAX_ACCELERATION);

            driveConfigureFollower.follow(driveNeoMaster, true);

            driveNeoFollower.configure(driveConfigureFollower, null, null); 
            driveNeoMaster.configure(driveConfigMaster, resetMode, persistMode);
            driveNeoFollower.configure(driveConfigureFollower, resetMode, persistMode);

            pidController = driveNeoMaster.getClosedLoopController();

            // Set PID gains.
        
            // .outputRange(kMinOutput, kMaxOutput);
        

        //driveNeoMaster.restoreFactoryDefaults();
        //driveNeoFollower.restoreFactoryDefaults();

        //driveNeoMaster.setIdleMode(IdleMode.kBrake);
		//driveNeoFollower.setIdleMode(IdleMode.kBrake);
        //setIdleMode, SoftLimitDirection, & maybe follow seemingly removed between 2024 & 2025
        //driveNeoMaster.setSoftLimit(SoftLimitDirection.kforward, BOTTOM_LIMIT);
		//driveNeoMaster.enableSoftLimit(SoftLimitDirection.kForward, false); 
		//driveNeoMaster.setSoftLimit(SoftLimitDirection.kReverse, TOP_LIMIT);
		//driveNeoMaster.enableSoftLimit(SoftLimitDirection.kForward, false);   
                 
        // driveNeoMaster.setsoftLimit(20);

        positionController = new ProfiledPIDController(KP, KI, KD, new Constraints(MAX_VELOCITY, MAX_ACCELERATION));
        
        feedforward = new ElevatorFeedforward(KS, KG, KV, KA);
        
        setpoint = BOTTOM_LIMIT;
    }

    public void setSetpoint(double setpoint){
        this.setpoint=setpoint;
    }

    public double getSetpoint(){
        return setpoint;
    }

    public boolean atSetpoint(){
        return positionController.atSetpoint();
    }
    
    public boolean nearSetpoint(){
        return Math.abs(driveNeoMaster.getEncoder().getPosition() - setpoint) < 10;
    }

    public static Elevator getInstance(){
        if (elevator == null){
            elevator = new Elevator();
        }
        return elevator;
    }
    
    public void setSpeed(double speed){
        driveNeoMaster.set(speed);
    }
    
    public double getPosition(){
        return driveNeoMaster.getEncoder().getPosition();
    }
    
    public ProfiledPIDController getPIDController(){
        return positionController;
    }
    
    public void setPosition(double setpoint){
        driveNeoMaster.setVoltage(feedforward.calculate(0) + positionController.calculate(driveNeoMaster.getEncoder().getPosition(), setpoint));
    }
    
    public void setMotorPercent(double motorSpeed){
        this.motorSpeed=motorSpeed+KG;
    }
    
    //Target position is in inches
    public void setElevatorPosition(double targetPosition){
        pidController.setReference(targetPosition, SparkMax.ControlType.kPosition, ClosedLoopSlot.kSlot0, -0.05);
    }
    
    public void periodic(){
        SmartDashboard.putNumber("Elevator Encoder", getPosition());
        SmartDashboard.putNumber("elevator Setpoint", setpoint);
        driveNeoMaster.setVoltage(motorSpeed*motorVoltage);
    }
    
    public static int getElevation(){
        return elevation;
    }
    
    public static void setElevation(int newElevation){
        elevation=newElevation;
    }
}
