package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.config.constants.PhysicalConstants.climberConstants.*;
import static frc.robot.config.constants.PortConstants.climberPorts.*;

public class Climber extends SubsystemBase{
    private static Climber climber;
    private SparkMax driveNeoMaster;
    private SparkMaxConfig driveConfigMaster;
    private double setpoint;
    private double motorSpeed=0;
    public static int positionLevel=1; /*Not actual position, used for  set angles*/
    private Climber(){
        driveNeoMaster = new SparkMax(sparkMaxId, MotorType.kBrushless);
        driveConfigMaster = new SparkMaxConfig();
        SparkBase.ResetMode resetMode = ResetMode.kNoResetSafeParameters;
        SparkBase.PersistMode persistMode = PersistMode.kNoPersistParameters;
        driveNeoMaster.configure(driveConfigMaster, resetMode, persistMode);
                setpoint=descendSetpoint; 
            }
    public SparkMax getDriveNeoMaster(){
        return driveNeoMaster;
    }
    
    public void setSpeed(double speed){
        driveNeoMaster.set(speed);
        // System.out.println("Speed: " + speed);
    }

    public double getPosition(){
        return driveNeoMaster.getEncoder().getPosition();
    }

    public double getPositionAngle(){
        return -6.274 * (getPosition() + 9.8);
    }

    public void setPosition(double setpoint){
        driveNeoMaster.getEncoder().setPosition(setpoint);
    }

    public void periodic(){
        SmartDashboard.putNumber("Climber Position", getPosition());
        SmartDashboard.putNumber("Climber Setpoint", setpoint);
        // driveNeoMaster.setVoltage(positionController.calculate(getPosition(), setpoint));
        // setPosition(setpoint);
    }

    public static Climber getInstance(){
        if (climber == null){
            climber = new Climber();
        }
        return climber;
    }
}