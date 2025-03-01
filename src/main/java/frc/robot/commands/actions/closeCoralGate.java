package frc.robot.commands.actions;
import static frc.robot.config.constants.PhysicalConstants.servoConstants.closedSetpoint;
import static frc.robot.subsystems.CoralGate.servo;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralGate;
public class closeCoralGate extends Command{

    private CoralGate coralGate;

    public closeCoralGate() {
        coralGate = CoralGate.getInstance();
        addRequirements(coralGate);
    }

    public void initialize(){
    }

    public void execute(){
        servo.set(closedSetpoint);
        System.out.println(servo.get());
    }

    public void end(boolean interrupted){
        servo.set(closedSetpoint);
        // SmartDashboard.putNumber("Servo", servo.get());


    }
    public boolean isFinished(){
        return false;
    }
}

