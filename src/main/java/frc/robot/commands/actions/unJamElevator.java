package frc.robot.commands.actions;
import frc.robot.config.constants.PortConstants.Elevator;
import frc.robot.subsystems.ElevatorBrake;
import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.config.constants.PhysicalConstants.servoConstants.unJamSetpoint;
import static frc.robot.subsystems.ElevatorBrake.*;
import static frc.robot.subsystems.ElevatorBrake.servo;
import edu.wpi.first.wpilibj2.command.Command;
public class unJamElevator extends Command{
    ElevatorBrake elevatorBrake;
    public void unJamElevator(){
        elevatorBrake = ElevatorBrake.getInstance();
        addRequirements(elevatorBrake);
    }

    public void initialize(){    
    }

    public void execute(){
        servo.set(unJamSetpoint);
    }
    
    public void end(boolean interrupted){
    }
    
    public boolean isFinished(){
        return false;
    }
}
