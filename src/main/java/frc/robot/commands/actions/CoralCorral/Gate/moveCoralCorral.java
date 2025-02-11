package frc.robot.commands.actions.CoralCorral.Gate;
import frc.robot.subsystems.CoralCorral;
import edu.wpi.first.wpilibj2.command.Command;

public class moveCoralCorral extends Command {
    private CoralCorral coralCorral;
    private double increment;
    public moveCoralCorral(double increment){
        coralCorral = coralCorral.getInstance();
        this.increment = increment;
        addRequirements(coralCorral);
    }
    public void initialize(){}
    public void execute(){
        coralCorral.setSetpoint(coralCorral.getSetpoint()+increment);
    }
    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }
}
