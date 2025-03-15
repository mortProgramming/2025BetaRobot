package frc.robot.commands.actions.auton;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L3;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
import frc.robot.commands.actions.moveCoralCorral;

public class AlgaeDescore extends SequentialCommandGroup{
    public AlgaeDescore(){
        addCommands(
            new SequentialCommandGroup(
                new ParallelCommandGroup(
                    new TimedDrive(3,3,2,0),
                    new TimedDrive(3,0,0,1),
                    new setElevator(L3),
                    new setCoralCorral(intake)
                ).withTimeout(3),
                new ParallelCommandGroup(
                    new TimedDrive(3,-3,-2,0),
                    new moveCoralCorral(0.25).withTimeout(1)
                )
            ) 
        );
    }
    
}
