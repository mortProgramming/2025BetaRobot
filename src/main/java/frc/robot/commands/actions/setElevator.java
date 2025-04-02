package frc.robot.commands.actions;
import frc.robot.subsystems.Elevator;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L2;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L3;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.L4;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.Coral;
// import static frc.robot.subsystems.Elevator.positioncontroller.*;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.gravitySpeed;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.ground;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

public class setElevator extends Command{
    private Elevator elevator;
    private double targetPosition;
    private PIDController positioncontroller = new PIDController(0.04, 0, 0);
    public setElevator(double targetPosition){
        elevator = Elevator.getInstance();
        this.targetPosition = targetPosition;
        addRequirements(elevator);
    }
    public void initialize(){

    }
    
    public void execute(){
        // elevator.setPosition(-elevator.getPIDController().calculate(elevator.getPosition(), targetPosition));
        // positioncontroller.setSetpoint(targetPosition);
        // double speed = positioncontroller.calculate(elevator.getPosition());
        // elevator.setSpeed(speed * 0.5);
        elevator.setElevatorPosition(targetPosition);
        // System.out.println("Speed: " + speed + " Target Position: " + targetPosition + " Encoder Position: " + elevator.getPosition());
    }
    public boolean isFinished(){
        return false;
    }
    public void end(boolean interrupted){
        elevator.setMotorPercent(gravitySpeed);
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
    public static Command Ground(){
        return new setElevator(ground);
    }
}
