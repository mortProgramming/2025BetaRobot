package frc.robot.commands.actions.elevator;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.Command;

public class elevatorPosition extends Command{

    private final Elevator elevator;
    private final double targetPosition;

    public elevatorPosition(Elevator elevator, double targetPosition) {
        this.elevator = elevator;
        this.targetPosition = targetPosition;
        addRequirements(elevator);
    }
    public void execute(){
        elevator.setElevatorPosition(targetPosition);
    }
    
    public boolean isFinished(){
        return false;
    }
    
    public void end(boolean interrupted){
        
    }
}
