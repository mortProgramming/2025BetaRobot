package frc.robot.commands.actions;
import frc.robot.subsystems.CoralGate;
import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.config.constants.PhysicalConstants.servoConstants.openSetpoint;
import static frc.robot.subsystems.CoralGate.servo;

public class openCoralGate extends Command{

    private CoralGate coralGate;
    public openCoralGate(){
        coralGate = CoralGate.getInstance();
        addRequirements(coralGate);
    }
    public void initialize(){
    }
    public void execute(){
        servo.set(openSetpoint);
        // System.out.println(servo.getAngle());
    }

    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }
}
