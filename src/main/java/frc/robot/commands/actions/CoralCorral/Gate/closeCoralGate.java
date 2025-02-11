package frc.robot.commands.actions.CoralCorral.Gate;
import frc.robot.subsystems.CoralGate;
import edu.wpi.first.wpilibj2.command.Command;
public class closeCoralGate {
    private CoralGate coralGate;
    public void initialize(){}
    public void execute(){
        coralGate.close();
    }
    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }
}