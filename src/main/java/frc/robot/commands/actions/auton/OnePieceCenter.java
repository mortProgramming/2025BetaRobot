package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L4;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceCenter extends SequentialCommandGroup{
    public OnePieceCenter(){
        addCommands(
            new SequentialCommandGroup(
                //For setCoralCorral, 1 target position equals 0.037 on the absolute encoder
                new TimedDrive(5,0.7,0,0).withTimeout(4),
                new TimedDrive(0,0,0,0).withTimeout(2),
                // new TimedDrive(3,0,0,-0.5),
                // new TimedDrive(4,0.6,0,0),
                new setElevator(L4).withTimeout(4),
                new setCoralCorral(21).withTimeout(1),
                new openCoralGate().withTimeout(3)
                // new setElevator(30).withTimeout(2)
                //Drive away to score coral
                //new TimedDrive(3,0.5,0,0).withTimeout(3),


                // new setCoralCorral(0)
            ) 
        );
    }
}