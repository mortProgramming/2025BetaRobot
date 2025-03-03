package frc.robot.commands.actions.auton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.openCoralGate;
public class Taxi extends SequentialCommandGroup{
    
    public Taxi(){
        // addCommands(new SequentialCommandGroup(new TimedDrive(time,x,y,omega)));
        addCommands(
            new SequentialCommandGroup(
                new setCoralCorral(25).withTimeout(1),
                new TimedDrive(3,0.4,0,0),
                new TimedDrive(3,0,0,-0.5),
                new TimedDrive(4,0.6,0,0),
                // new TimedDrive(2,-0.25,0,0),
                // new TimedDrive(0,0,0,0),
                new setElevator(30),
                new openCoralGate(),
                new TimedDrive(3, 0, -0.5, 0).withTimeout(3),
                new TimedDrive(0,0,0,0),
                new setCoralCorral(0)
            ) 
        );
    }
}
