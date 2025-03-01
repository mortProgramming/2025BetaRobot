package frc.robot.commands.actions.auton;
import frc.robot.commands.actions.setElevator;
import frc.robot.commands.actions.TimedDrive;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
public class OnePiece extends SequentialCommandGroup{
    public OnePiece(){
        addCommands(
            new SequentialCommandGroup(
                new TimedDrive(3,0.5,0,0),
                new setElevator(0.5)
            ) 
        );
    }
}