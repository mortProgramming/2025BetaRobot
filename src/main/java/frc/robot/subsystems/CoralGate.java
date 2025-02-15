package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import static frc.robot.config.constants.PortConstants.Servo.*;
import static frc.robot.config.constants.PhysicalConstants.servoConstants.*;

//The servo starts at 1/8th of an inch & ends at 2 1/4th of an inch
//For this, we need the servo to extend to a total of 1/2th of an inch
public class CoralGate extends SubsystemBase{
    public static CoralGate coralGate;
    public static boolean isClosed=true;
    public Servo servo;
    public CoralGate(){
        servo=new Servo(servoPort);
    }
    public void initialize(){
        coralGate = new CoralGate();
        servo.setPosition(startSetpoint);
    }
    public static CoralGate getInstance(){
        if (coralGate == null){
            coralGate = new CoralGate();
            return coralGate;
        }
        return coralGate;
    }
    public void open(){
        servo.setPosition(openSetpoint);
    }
    public void close(){
        servo.setPosition(closedSetpoint);
    }
    public static boolean getIsClosed(){
        return isClosed;
    }
    public static void setIsClosed(boolean closed){
        isClosed=closed;
    }

}
