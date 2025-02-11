package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.config.constants.PIDConstants.CoralCorralPID.*;
import static frc.robot.config.constants.PortConstants.CoralCorral.sparkMaxId;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.*;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.MAX_VOLTAGE;
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

public class CoralCorral extends SubsystemBase  {
    private static CoralCorral coralCorral;
    private SparkMax driveNeoMaster;
    private SparkMaxConfig driveConfigMaster;
    private ProfiledPIDController positionController;
    private double setpoint;
    private SimpleMotorFeedforward feedforward;
    private CoralCorral(){
        driveNeoMaster = new SparkMax(sparkMaxId, MotorType.kBrushless);
        driveConfigMaster = new SparkMaxConfig();
        SparkBase.ResetMode resetMode = ResetMode.kNoResetSafeParameters;
        SparkBase.PersistMode persistMode = PersistMode.kNoPersistParameters;
        driveNeoMaster.configure(driveConfigMaster, resetMode, persistMode);
        positionController = new ProfiledPIDController(KP, KI, KD, CoralCorralConstants);
                feedforward = new SimpleMotorFeedforward(KS, KG, KV, KA);
                setpoint=REST_ANGLE; 
            }

            public ProfiledPIDController getPositionController(){
        return positionController;
    }
    public SparkMax getDriveNeoMaster(){
        return driveNeoMaster;
    }
    public void setSetpoint(double setpoint){
        this.setpoint = setpoint;
    }
    public double getSetpoint(){
        return setpoint;
    }
    public boolean atSetpoint(){
        return positionController.atSetpoint();
    }
    public void setSpeed(double speed){
        driveNeoMaster.set(speed);
    }
    public boolean nearSetpoint(){
        return Math.abs(positionController.getPositionError()) < 0.1;
    }
    public double getPosition(){
        return driveNeoMaster.getEncoder().getPosition();
    }
    public double getPositionAngle(){
        return -6.274 * (getPosition() + 9.8);
    }
    public void setPosition(double setpoint){
        double output = ((feedforward.calculate(0)+positionController.calculate(getPosition(), setpoint)) / MAX_VOLTAGE);
        if (output >= 1){
            output=0.1;
        }
        else if (output <= -1){
            output=-0.1;
        }
        SmartDashboard.putNumber("Coral Corral Output", output);
        driveNeoMaster.set(output);
    }
    public void periodic(){
        SmartDashboard.putNumber("Coral Corral Position", getPosition());
        SmartDashboard.putNumber("Coral Corral Setpoint", setpoint);
        driveNeoMaster.setVoltage(positionController.calculate(getPosition(), setpoint));
        setPosition(setpoint);
    }
    public  CoralCorral getInstance(){
        if (coralCorral == null){
            coralCorral = new CoralCorral();
        }
        return coralCorral;
    }
}
