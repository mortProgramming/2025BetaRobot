package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePiecediffPos2 extends SequentialCommandGroup{
    public OnePiecediffPos2(){
        addCommands(
            new SequentialCommandGroup(
                //  Have to fill these numbers in with actual ones since I just copied & pasted these from another auton
new setCoralCorral(35).withTimeout(1),
                new TimedDrive(2,0.2,0,0),
                new TimedDrive(1,0,0,-1.5),
                new TimedDrive(3,1,0,0),
                new setElevator(20),
                new openCoralGate(),
                new setCoralCorral(L1)  
            ) 
        );
    }
}
