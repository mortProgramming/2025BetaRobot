package frc.robot.commands.actions.elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.constants.PhysicalConstants.Elevator.TOP_POSITION.*;
public class SetElevator extends SequentialCommandGroup {
    public SetElevator(double ElevatorPosition){
        addCommands(new SetElevator(elevatorSetpoint));
    }
    //Need to make constants for POSITIONS & changes Constants to proper file name
    public final static SetElevator floor(){
        return new SetElevator(FLOOR_POSITION);
    }
    public final static SetElevator middle(){
        return new SetElevator(MIDDLE_POSITION);
    }
    public final static SetElevator top(){
        return new SetElevator(constants.PhysicalConstants.TOP_POSITION);
    }
}