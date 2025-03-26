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
import static frc.robot.config.constants.PortConstants.CoralGate.sparkMaxId;
import static frc.robot.config.constants.PortConstants.climberPorts.*;

public class CoralGate extends SubsystemBase{
    private static CoralGate coralGate;
    private SparkMax driveNeoMaster;
    private SparkMaxConfig driveConfigMaster;
    private double setpoint;

    private CoralGate(){
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

    public void setSpeed(double speed){ //setSpeed is a method that sets the speed of the motor
        driveNeoMaster.set(speed);
    }

    public double getPosition(){
        return driveNeoMaster.getEncoder().getPosition();
    }
    public void setPosition(double setpoint){
        driveNeoMaster.getEncoder().setPosition(setpoint);
    }

    public void periodic(){
        SmartDashboard.putNumber("CoralGate Position", getPosition());
        SmartDashboard.putNumber("CoralGate Setpoint", setpoint);
    }

    public static CoralGate getInstance(){
        if (coralGate == null){
            coralGate = new CoralGate();
        }
        return coralGate;
    }
}
