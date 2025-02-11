package frc.robot.commands.actions.CoralCorral.Gate;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.reef;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.coral;
import frc.robot.subsystems.CoralCorral;
import edu.wpi.first.wpilibj2.command.Command;
public class setCoralCorral extends Command{
    private CoralCorral coralCorral;
    private double setpoint;
    public setCoralCorral(double setpoint){
        coralCorral = coralCorral.getInstance();
        this.setpoint = setpoint;
        addRequirements(coralCorral);
    }    
    public void initialize(){}
    public void execute(){
        coralCorral.setSetpoint(setpoint);
    }
    public boolean isFinished(){
        return false;
    }
    public void end(boolean interrupted){
        coralCorral.setSetpoint(setpoint);
    }
    public static Command reef(){
        return new setCoralCorral(reef);
    }
    public static Command coral(){
        return new setCoralCorral(coral);
    }
}
