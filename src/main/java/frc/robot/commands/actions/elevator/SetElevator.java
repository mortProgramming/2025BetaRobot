package frc.robot.commands.actions.elevator;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Elevator;
import frc.robot.config.constants.PhysicalConstants;
import frc.robot.config.constants.PhysicalConstants.ElevatorConstants;

public class SetElevator extends SequentialCommandGroup {
    public SetElevator(double elevatorSetpoint){
        addCommands(new SetElevator(elevatorSetpoint));
    }
    public final static SetElevator Level_2(){
        return new SetElevator(PhysicalConstants.ElevatorConstants.L2);
    }
    public final static SetElevator Level_3(){
        return new SetElevator(PhysicalConstants.ElevatorConstants.L3);
    }
    public final static SetElevator Level_4(){
        return new SetElevator(PhysicalConstants.ElevatorConstants.L4);
    }
    public final static SetElevator Level_Coral(){
        return new SetElevator(PhysicalConstants.ElevatorConstants.Coral);
    }
}