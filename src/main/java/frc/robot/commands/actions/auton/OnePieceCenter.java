package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.ground;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L4;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.moveCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
import frc.robot.commands.actions.moveCoralCorral;
public class OnePieceCenter extends SequentialCommandGroup{
    public OnePieceCenter(){
        addCommands(
            new SequentialCommandGroup(

                new ParallelCommandGroup(
                    new TimedDrive(3,0.7,0.1,0).withTimeout(3),
                    new setElevator(L4).withTimeout(3)
                ),
                new ParallelCommandGroup(
                    new TimedDrive(0,0,0,0).withTimeout(1),
                    new setCoralCorral(28).withTimeout(1)
                ),

                new moveCoralGate(1).withTimeout(2),
                new moveCoralCorral(0.25).withTimeout(1),


                new ParallelCommandGroup(
                    new TimedDrive(1,0,3,0).withTimeout(1),
                    new setElevator(ground).withTimeout(2)
                ),
                
                new ParallelCommandGroup(
                    new TimedDrive(3,3,3,-0.5).withTimeout(3),
                    new setCoralCorral(intake).withTimeout(3)
                ),
                
                new TimedDrive(1,0,0,0).withTimeout(1),

                new ParallelCommandGroup(
                    new moveCoralGate(-1).withTimeout(2),
                    new TimedDrive(2,-3,-3,1).withTimeout(2),
                    new setElevator(L4).withTimeout(2)
                )
            ) 
        );
    }
}