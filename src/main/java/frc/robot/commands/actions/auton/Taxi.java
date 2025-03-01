package frc.robot.commands.actions.auton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.setCoralCorral;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.openCoralGate;
public class Taxi extends SequentialCommandGroup{
    // private double time;
    // private double x;
    // private double y;
    // private double omega;
    
    // public Taxi(double time, double x, double y, double omega){
    //     this.time = time;
    //     this.x = x;
    //     this.y = y;
    //     this.omega = omega;
    // }
    public Taxi(){
        // addCommands(new SequentialCommandGroup(new TimedDrive(time,x,y,omega)));
        addCommands(
            new SequentialCommandGroup(
                new setCoralCorral(45).withTimeout(1),

                new TimedDrive(3,0.4,0,0),

                new TimedDrive(3,0,0,-0.5),

                new TimedDrive(4,0.6,0,0),

                // new TimedDrive(2,-0.25,0,0),

                // new TimedDrive(0,0,0,0),

                new openCoralGate(),

                new TimedDrive(3, 0, -0.50, 0).withTimeout(3),

                new TimedDrive(0,0,0,0),

                new setCoralCorral(0)
            ) 
        );
    }
}
