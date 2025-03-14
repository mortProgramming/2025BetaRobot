package frc.robot.commands.actions.auton;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.actions.TimedDrive;
import frc.robot.commands.actions.openCoralGate;
import frc.robot.commands.actions.setCoralCorral;
import frc.robot.commands.actions.setElevator;
public class OnePieceLeftL4 extends SequentialCommandGroup{
    public OnePieceLeftL4(){
        addCommands(
            new SequentialCommandGroup(
                //This is meant to be on the relative left side of the barge, with the bottom left side of the robot's bumper touching the bottom right side of the middle pole of the barge
            ) 
        );
    }
}