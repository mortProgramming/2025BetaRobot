package frc.robot.commands.actions;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CoralCorral;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.config.constants.PhysicalConstants.CoralCorralConstants;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.speedFactor;
public class moveCoralCorral extends Command {
    private CoralCorral coralCorral;
    private double speed;
    private CommandXboxController xboxController;
    public moveCoralCorral(CommandXboxController xboxController){
        coralCorral = CoralCorral.getInstance();
        this.xboxController=xboxController;
        //this.speed = speed;
        addRequirements(coralCorral);
    }
    public moveCoralCorral(double speed){
        coralCorral = CoralCorral.getInstance();
        this.speed = speed;
        this.xboxController=null;
        addRequirements(coralCorral);
    }

    public void initialize(){
    }

    public void execute(){
        if (xboxController==null)
            coralCorral.setSpeed(speed);
        else
        coralCorral.setSpeed(xboxController.getRightY() * speedFactor);
    }

    public void end(boolean interrupted){
        coralCorral.setSpeed(0);
    }
    
    public boolean isFinished(){
        return false;
    }
}