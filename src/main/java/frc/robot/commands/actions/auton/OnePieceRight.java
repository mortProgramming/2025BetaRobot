package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dumpL4;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.moveCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceRight extends SequentialCommandGroup{
    public OnePieceRight(){
        addCommands(
            new SequentialCommandGroup(
                // new TimedDrive(4,1,1,-2).withTimeout(4)
                new TimedDrive(3.5,1,-0.58,-0.33).withTimeout(3.5),
                new setElevator(L4).withTimeout(4),
                new setCoralCorral(dumpL4).withTimeout(4),
                new moveCoralGate(-1).withTimeout(1),
                new ParallelCommandGroup(
                new setCoralCorral(intake).withTimeout(2)
                )
            )
        );
    }
}