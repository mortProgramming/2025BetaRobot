package frc.robot.commands.actions.elevator;
import frc.robot.subsystems.ElevatorBrake;
import frc.robot.subsystems.CoralGate;

public class jamElevator {
    
    private ElevatorBrake elevatorBrake;
    private boolean isJammed=false;
    public void initialize(){
        isJammed=false;
        elevatorBrake = ElevatorBrake.getInstance();
    }
    public void execute(){
        if (isJammed){
            elevatorBrake.unJamElevator();
            isJammed=false;
        }
        else{
            elevatorBrake.jamElevator();
            isJammed=true;
        }
    }
    public jamElevator JamElevator(){
        if (isJammed){
            return elevatorBrake.unJamElevator();
            isJammed=false;
        }
        else{
            return elevatorBrake.jamElevator();
            isJammed=true;
        }

    }
    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }

}

