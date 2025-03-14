package frc.robot.commands.actions.auton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.openCoralGate;
public class Taxi extends SequentialCommandGroup{
    public Taxi(){
        addCommands(
            new SequentialCommandGroup(
                //new TimedDrive(3,0.5,0,0)
                new TimedDrive(2.5,0,0,1.5708)
            ) 
        );
    }
}
