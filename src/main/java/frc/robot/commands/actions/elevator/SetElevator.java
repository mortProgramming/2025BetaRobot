package frc.robot.commands.actions.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.config.constants.PhysicalConstants;
import frc.robot.config.constants.PhysicalConstants.ElevatorConstants;
public class SetElevator extends Command {
    SetElevator setElevator;
    public SetElevator(double elevatorSetpoint){
        // new SetElevator(elevatorSetpoint);
    }
    public final static SetElevator ground(){
        return new SetElevator(PhysicalConstants.ElevatorConstants.ground);
    }
    public final static SetElevator Level_1(){
        return new SetElevator(PhysicalConstants.ElevatorConstants.L1);
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
    public void initialize(){
    }
}