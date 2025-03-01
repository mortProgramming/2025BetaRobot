package frc.robot.commands.actions.auton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
                new TimedDrive(3,-0.5,0,0)
            ) 
        );
    }
}
