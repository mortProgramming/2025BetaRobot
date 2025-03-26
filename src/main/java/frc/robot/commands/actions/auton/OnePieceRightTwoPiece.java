
package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dumpL1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dumpL4;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intakeCor;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.moveCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceRightTwoPiece extends SequentialCommandGroup{
    public OnePieceRightTwoPiece(){
        addCommands(
            new SequentialCommandGroup(
                //4
                // new WaitCommand(3),
                new ParallelCommandGroup(
                    new TimedDrive(3.5,1,-0.50,-0.33).withTimeout(3.5),
                    new setElevator(L4).withTimeout(3.5),
                    new setCoralCorral(dump).withTimeout(3.5)
                ),
                    new moveCoralGate(-1).withTimeout(0.5),
                //2
                new ParallelCommandGroup(
                    new setCoralCorral(intake).withTimeout(2),
                    new setElevator(ground).withTimeout(2),
                    new TimedDrive(1,-1,0,1).withTimeout(1)
                ).withTimeout(2),
                //3.5
                new ParallelCommandGroup(
                new TimedDrive(3.5,1.7,0,0.33).withTimeout(3.5),
                new setCoralCorral(intakeCor).withTimeout(2)
                ).withTimeout(3.5),

                //1
                new ParallelCommandGroup(
                    new TimedDrive(1,0,0,0).withTimeout(1),
                    new moveCoralGate(1).withTimeout(1)
                ).withTimeout(1),

                //1
                new TimedDrive(1,-2,0,2).withTimeout(1),

                //3.5
                new ParallelCommandGroup(
                    new TimedDrive(3,1.5,0,0.64).withTimeout(3),
                    new setElevator(L4).withTimeout(3),
                    new setCoralCorral(dump).withTimeout(3)
                ).withTimeout(3),
                    new moveCoralGate(-1).withTimeout(0.5)

            )
        );
    }
}
