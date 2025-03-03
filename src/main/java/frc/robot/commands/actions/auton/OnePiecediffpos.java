package frc.robot.commands.actions.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;

public class OnePiecediffpos extends SequentialCommandGroup{
    public OnePiecediffpos(){
        addCommands(
            new SequentialCommandGroup(
                
            ) 
        );
    }
}