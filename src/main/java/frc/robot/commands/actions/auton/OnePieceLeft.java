package frc.robot.commands.actions.auton;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L4;

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
                //For setCoralCorral, 1 target position equals 0.037 on the absolute encoder
                new TimedDrive(5,0.7,0,0).withTimeout(4),
                new TimedDrive(0,0,0,0).withTimeout(1),

                new ParallelCommandGroup(
                    new setElevator(L4).withTimeout(4),
                    new setCoralCorral(21).withTimeout(1)
                ),
                
                new openCoralGate().withTimeout(3)
            ) 
        );
    }
}