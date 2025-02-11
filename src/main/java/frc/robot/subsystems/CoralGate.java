package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import static frc.robot.config.constants.PortConstants.Servo.*;
public class CoralGate extends SubsystemBase{
    public static CoralGate coralGate; 
    public Servo servo;
    public double closedSetpoint;
    public double openSetpoint;
    public CoralGate(){
        servo=new Servo(servoPort);
        closedSetpoint=0;
        openSetpoint=45;
    }
    public void open(){
        servo.setAngle(openSetpoint);
    }
    public void close(){
        servo.setAngle(closedSetpoint);
    }

}
