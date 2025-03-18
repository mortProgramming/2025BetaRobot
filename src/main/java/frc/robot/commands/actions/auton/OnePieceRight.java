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
public class OnePieceRight extends SequentialCommandGroup{
    public OnePieceRight(){
        addCommands(
            new SequentialCommandGroup(
                new TimedDrive(1,0,0,-2).withTimeout(1),
                new TimedDrive(3,2,0,0).withTimeout(3),
                new setElevator(L4).withTimeout(2),
                new setCoralCorral(dump).withTimeout(3),
                new openCoralGate().withTimeout(3)
            )
        );
    }
}