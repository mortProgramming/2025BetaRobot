package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceRed extends SequentialCommandGroup{
    public OnePieceRed(){
        addCommands(
            new SequentialCommandGroup(
                new setCoralCorral(35).withTimeout(1),
                new TimedDrive(3,0.4,0,0),
                new TimedDrive(3,0,0,-0.5),
                new TimedDrive(4,0.6,0,0),
                new setElevator(L1),
                new openCoralGate(),
                new setCoralCorral(0)
            ) 
        );
    }
}