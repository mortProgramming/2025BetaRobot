package frc.robot.commands.actions;
import frc.robot.config.constants.PortConstants.Elevator;
import frc.robot.subsystems.ElevatorBrake;
import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.subsystems.ElevatorBrake.servo;
import static frc.robot.config.constants.PhysicalConstants.servoConstants.jamSetpoint;
import static frc.robot.subsystems.ElevatorBrake.isJammed;

public class jamElevator extends Command{
    
    private ElevatorBrake elevatorBrake;

    public jamElevator() {
        elevatorBrake = ElevatorBrake.getInstance();
        addRequirements(elevatorBrake);
    }
    
    public void initialize(){
    }

    public void execute(){
        servo.set(jamSetpoint);
    }

    public void end(boolean interrupted){
    }
    
    public boolean isFinished(){
        return false;
    }

}

