package frc.robot.commands.actions.CoralCorral.Gate;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.coral;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L2;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L3;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L4;
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
    public static Command Tray(){
        return new setCoralCorral(L1);
    }
    public static Command Reef(){
        return new setCoralCorral(L2);
    }
    public static Command coralStation(){
        return new setCoralCorral(coral);
    }
}
