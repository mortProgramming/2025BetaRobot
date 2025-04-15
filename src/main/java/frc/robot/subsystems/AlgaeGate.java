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
import static frc.robot.config.constants.PortConstants.AlgaeGate.sparkMaxId;
import static frc.robot.config.constants.PortConstants.climberPorts.*;

public class AlgaeGate extends SubsystemBase{
    private static AlgaeGate algaeGate;
    private SparkMax driveNeoMaster;
    private SparkMaxConfig driveConfigMaster;
    private double setpoint;

    private AlgaeGate(){
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

    public static AlgaeGate getInstance(){
        if (algaeGate == null){
            algaeGate = new AlgaeGate();
        }
        return algaeGate;
    }
}
