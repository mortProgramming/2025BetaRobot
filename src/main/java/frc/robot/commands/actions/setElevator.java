package frc.robot.commands.actions;
import frc.robot.subsystems.Elevator;

import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L2;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L3;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L4;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.Coral;

import edu.wpi.first.wpilibj2.command.Command;

public class setElevator extends Command{
    private Elevator elevator;
    private double targetPosition;
    public setElevator(double targetPosition){
        elevator = Elevator.getInstance();
        this.targetPosition = targetPosition;
        addRequirements(elevator);
    }
    public void initialize(){

    }
    
    public void execute(double increment){
        elevator.setMotorPercent(-elevator.getPIDController().calculate(elevator.getPosition(), targetPosition));
    }
    public boolean isFinished(){
        return false;
    }
    public void end(boolean interrupted){
        elevator.setMotorPercent(0.2);
    }
    public static Command L1(){
        return new setElevator(L1);
    }
    public static Command L2(){
        return new setElevator(L2);
    }
    public static Command L3(){
        return new setElevator(L3);
    }
    public static Command L4(){
        return new setElevator(L4);
    }
    public static Command LCoral(){
        return new setElevator(Coral);
    }
}
