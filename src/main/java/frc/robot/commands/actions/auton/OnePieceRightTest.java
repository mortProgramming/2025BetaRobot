package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dumpL4;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intakeCor;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.moveCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceRightTest extends SequentialCommandGroup{
    public OnePieceRightTest(){
        addCommands(
                // new ParallelCommandGroup(
                    new SequentialCommandGroup(
                    // new TimedDrive(3.5,1,-0.52,-0.33).withTimeout(3.5)
                    new setElevator(L1).withTimeout(2),
                    new setElevator(ground).withTimeout(2)
            )
        );
    }
}