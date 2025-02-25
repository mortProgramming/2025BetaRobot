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
        //getPosition might cause an error
        elevator.setSpeed(increment);
        
    }
    public boolean isFinished(){
        return false;
    }
}
