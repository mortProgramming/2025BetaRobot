package frc.robot.commands.actions.auton;

import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dumpL4;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.ground;
// import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L4;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.ground;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.moveCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceCenterWorks extends SequentialCommandGroup{
    public OnePieceCenterWorks(){
        addCommands(
            new SequentialCommandGroup(
                // For setCoralCorral, 1 target position equals 0.037 on the absolute encoder
                // new setCoralCorral(ground).withTimeout(3),
                new ParallelCommandGroup(
                new TimedDrive(3,0.7,0,0).withTimeout(3),
                new setElevator(L4).withTimeout(4),
                new setCoralCorral(dumpL4).withTimeout(4)
                ),
                new moveCoralGate(-1).withTimeout(1),
                new ParallelCommandGroup(
                new TimedDrive(2,-0.2,0,0).withTimeout(2),
                new setElevator(2).withTimeout(3)
                )
            ) 
        );
    }
}