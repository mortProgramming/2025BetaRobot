package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import static frc.robot.config.constants.PortConstants.Servo.*;
import static frc.robot.config.constants.PhysicalConstants.servoConstants.*;
//For the elevator brake, we need to extend to full length to lock the elevator
public class ElevatorBrake extends SubsystemBase{
    public Servo servo;
    public static ElevatorBrake elevatorBrake;
    public static boolean isJammed=false;
    public ElevatorBrake(){
        servo=new Servo(servoPort2);
    }
    public void initialize(){
        elevatorBrake = new ElevatorBrake();
        servo.setPosition(closedSetpoint);
    }
    public static ElevatorBrake getInstance(){
        if (elevatorBrake == null){
            elevatorBrake = new ElevatorBrake();
            return elevatorBrake;
        }
        return elevatorBrake;
    }
    public void jamElevator(){
        servo.setPosition(jamSetpoint);
    }
    public void unJamElevator(){
        servo.setPosition(closedSetpoint);
    }
    public static boolean getIsJammed(){
        return isJammed;
    }
    public static void setIsJammed(boolean jammed){
        isJammed=jammed;
    }
}   
