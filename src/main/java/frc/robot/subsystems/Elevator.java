package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.config.SoftLimitConfig;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.config.constants.PortConstants.Elevator.*;
import static frc.robot.config.constants.PIDConstants.Elevator.*;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;

public class Elevator extends SubsystemBase{
    private static Elevator elevator;

    private SparkMax driveNeoMaster;
    private SparkMax driveNeoFollower;

    private SparkBaseConfig drivecConfigMaster;
    private SparkBaseConfig driveConfigureFollower;
    

    private ProfiledPIDController positionController;
    private ElevatorFeedforward feedforward;

    private double setpoint;
    
    private Elevator(){
        driveNeoMaster = new SparkMax(ELEVATOR_MASTER, MotorType.kBrushless);
        driveNeoFollower = new SparkMax(ELEVATOR_FOLLOWER, MotorType.kBrushless);
        drivecConfigMaster = new SparkMaxConfig();
        driveConfigureFollower = new SparkMaxConfig();

        
        
        SparkBase.ResetMode resetMode = ResetMode.kNoResetSafeParameters;
        SparkBase.PersistMode persistMode = PersistMode.kNoPersistParameters;

        driveNeoMaster.configure(driveConfigureFollower, resetMode, persistMode);
        driveNeoFollower.configure(driveConfigureFollower, resetMode, persistMode);
        // driveNeoMaster.restoreFactoryDefaults();1
        // driveNeoFollower.restoreFactoryDefaults();

        // driveNeoMaster.setIdleMode(IdleMode.kBrake);
		// driveNeoFollower.setIdleMode(IdleMode.kBrake);
        // //setIdleMode, SoftLimitDirection, & maybe follow seemingly removed between 2024 & 2025
        // driveNeoMaster.setSoftLimit(SoftLimitDirection.kforward, BOTTOM_LIMIT);
		// driveNeoMaster.enableSoftLimit(SoftLimitDirection.kForward, false); 
		// driveNeoMaster.setSoftLimit(SoftLimitDirection.kReverse, TOP_LIMIT);
		// driveNeoMaster.enableSoftLimit(SoftLimitDirection.kForward, false);   
                 
        //driveNeoMaster.setsoftLimit(20);

        driveConfigureFollower.follow(ELEVATOR_MASTER, true);

        positionController = new ProfiledPIDController(KP, KI, KD, new Constraints(MAX_VELOCITY, MAX_ACCELERATION));
        
        feedforward = new ElevatorFeedforward(KS, KG, KV, KA);
        
        setpoint = BOTTOM_LIMIT;
    }
    public void setSetpoint(double setpoint){
        this.setpoint=setpoint;
    }
    public double getSetPoint(){
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
    public void setPosition(double setpoint){
        driveNeoMaster.setVoltage(feedforward.calculate(0) + positionController.calculate(driveNeoMaster.getEncoder().getPosition(), setpoint));
    }
    public void periodic(){
        SmartDashboard.putNumber("Elevator Encoder", getPosition());
        SmartDashboard.putNumber("elevator setpoint", setpoint);
    }
    
}