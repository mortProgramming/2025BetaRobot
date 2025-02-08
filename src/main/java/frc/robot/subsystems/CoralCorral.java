package frc.robot.subsystems;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.config.constants.PortConstants.Elevator.ELEVATOR_MASTER;
import static frc.robot.config.constants.PortConstants.Elevator.MAX_VELOCITY;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KA;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KD;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KG;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KI;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KP;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KS;
import static frc.robot.config.constants.PIDConstants.CoralCorralPID.KV;
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
import edu.wpi.first.wpilibj.Servo;

public class CoralCorral extends SubsystemBase  {
    private static CoralCorral coralCorral;
    private SparkMax driveNeoMaster;
    private SparkMaxConfig driveConfigMaster;
    private ProfiledPIDController positionController;
    private double setpoint;
    private SimpleMotorFeedforward feedforward;
    private static Servo coralGate;
    private CoralCorral(){
        driveNeoMaster = new SparkMax(/*int Id here */, MotorType.kBrushless);
        driveConfigMaster = new SparkMaxConfig();
        SparkBase.ResetMode resetMode = ResetMode.kNoResetSafeParameters;
        SparkBase.PersistMode persistMode = PersistMode.kNoPersistParameters;
        driveNeoMaster.configure(driveConfigMaster, resetMode, persistMode);
        //Define max velocity & acceleration in physicalConstants.CoralCorral
        positionController = new ProfiledPIDController(KP, KI, KD, newConstraints(MAX_VELOCITY, MAX_ACCELERATION));
        feedforward = new SimpleMotorFeedforward(KS, KG, KV, KA);
        setpoint=REST_ANGLE; /*Add rest angle into constants */
        coralGate = new Servo(/*int Id here */);
    }
    public ProfiledPIDController getPositionController(){
        return positionController;
    }
    public SparkMax getDriveNeoMaster(){
        return driveNeoMaster;
    }
    public void setsetpoint(double setpoint){
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
        double output = ((feedforward.calculate(0)+positionController.calculate(getPosition(), setpoint))/MAX_VOLTAGE /*Add in max voltage */);
        if (output >= 1){
            output=0.1;
        }
        else if (output <= -1){
            output=-0.1;
        }
        SmartDashboard.putNumber("Coral Corral Output", output);
        driveNeoMaster.set(output);
    }
    public double getServoAngle(){
        return coralGate.getAngle();
    }
    public void setServoAngle(double angle){
        coralGate.setAngle(angle);
    }
    public void periodic(){
        SmartDashboard.putNumber("Coral Corral Position", getPosition());
        SmartDashboard.putNumber("Coral Corral Setpoint", setpoint);
        driveNeoMaster.setVoltage(positionController.calculate(getPosition(), setpoint));
        setPosition(setpoint);
    }
    public CoralCorral getInstance(){
        if (coralCorral == null){
            coralCorral = new CoralCorral();
        }
        return coralCorral;
    }
}
