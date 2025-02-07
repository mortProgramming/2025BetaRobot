package frc.robot.commands.actions.servo;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.config.constants.PortConstants;
public class moveServo {
    public static Servo servo=new Servo(PortConstants.Servo.servoPort);
    public static void openServo(){
        servo.setAngle(45);
    }
    public static void closeServo(){
        servo.setAngle(0);
    }
    public boolean isFinished() {
        return false;
      }
}
