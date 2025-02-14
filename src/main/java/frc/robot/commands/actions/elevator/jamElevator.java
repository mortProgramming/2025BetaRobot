package frc.robot.commands.actions.elevator;
import frc.robot.config.constants.PortConstants.Elevator;
import frc.robot.subsystems.ElevatorBrake;
import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.subsystems.ElevatorBrake.*;

public class jamElevator extends Command{
    
    private ElevatorBrake elevatorBrake;

    public jamElevator() {
        elevatorBrake = ElevatorBrake.getInstance();
        addRequirements(elevatorBrake);
    }
    public void initialize(){
    }
    public void execute(){}
    public void JamElevator(){
        if (ElevatorBrake.getIsJammed()){
            elevatorBrake.unJamElevator();
            setIsJammed(false);
        }
        else{
            elevatorBrake.jamElevator();
            setIsJammed(true);
        }

    }
    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }

}

