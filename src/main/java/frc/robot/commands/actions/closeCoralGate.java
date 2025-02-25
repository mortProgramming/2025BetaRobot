package frc.robot.commands.actions;
import frc.robot.subsystems.CoralGate;
import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.config.constants.PhysicalConstants.servoConstants.closedSetpoint;
import static frc.robot.subsystems.CoralGate.servo;
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
    }

    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }
}

