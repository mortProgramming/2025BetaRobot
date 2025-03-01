package frc.robot.commands.actions;
import frc.robot.subsystems.Elevator;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.gravitySpeed;
import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.speedFactor;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class moveElevator extends Command{
    private Elevator elevator;
    private CommandXboxController xboxController;
    public moveElevator(CommandXboxController xboxController){
        elevator = Elevator.getInstance();
        this.xboxController=xboxController;
        addRequirements(elevator);
    }
    public void initialize(){

    }

    public void end(boolean interrupted){
        elevator.setMotorPercent(gravitySpeed);
    }
    
    public void execute(){
        elevator.setSpeed(-xboxController.getLeftY()*speedFactor);
    }
    public boolean isFinished(){
        return false;
    }
}
