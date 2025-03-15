package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceLeft extends SequentialCommandGroup{
    public OnePieceLeft(){
        addCommands(
            new SequentialCommandGroup(
                new ParallelCommandGroup(
                    new TimedDrive(3,3,2,0),
                    new TimedDrive(3,0,0,1),
                    new setElevator(L4)
                ),
                new setCoralCorral(dump).withTimeout(4),
                new openCoralGate().withTimeout(3)
            ) 
        );
    }
}
