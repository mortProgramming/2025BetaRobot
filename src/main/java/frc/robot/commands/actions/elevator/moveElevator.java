package frc.robot.commands.actions.elevator;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj2.command.Command;
public class moveElevator extends Command{
    private Elevator elevator;
    private double incrementPerSecond;
    public moveElevator(double incrementPerSecond){
        elevator = Elevator.getInstance();
        this.incrementPerSecond = incrementPerSecond;
        addRequirements(elevator);
    }
    public void initialize(){

    }
    
    public void execute(){
        double increment=incrementPerSecond*0.02;
        //getPosition will cause a bug. Need to fix later by implementing a method to elevator to get position in inches
        elevator.setSetpoint(elevator.getPosition()+increment);
        
    }
    public boolean isFinished(){
        return false;
    }
}
