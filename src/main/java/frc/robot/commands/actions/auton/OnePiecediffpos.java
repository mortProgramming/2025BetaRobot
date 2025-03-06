package frc.robot.commands.actions.auton;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.ground;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
import frc.robot.config.constants.PhysicalConstants.ElevatorConstants;
import frc.robot.config.constants.PhysicalConstants.CoralCorralConstants;
public class OnePiecediffpos extends SequentialCommandGroup{
    public OnePiecediffpos(){
        addCommands(
            new SequentialCommandGroup(
                 
new setCoralCorral(35).withTimeout(1),
                new TimedDrive(2,0.2,0,0),
                new TimedDrive(1,0,0,-1.5),
                new TimedDrive(3,1,0,0),
                new setElevator(L1),
                new openCoralGate(),
                new setCoralCorral(ground).withTimeout(1)  
            ) 
        );
    }
}