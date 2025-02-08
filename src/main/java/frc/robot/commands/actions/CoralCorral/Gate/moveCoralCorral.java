package frc.robot.commands.actions.CoralCorral.Gate;
import frc.robot.subsystems.CoralCorral;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MoveCoralCorral extends CommandBase {
    private CoralCorral coralCorral;
    private double increment;
    public moveCoralCorral(double increment){
        coralCorral = CoralCorral.getInstance();
        this.increment = increment;
        addRequirements(coralCorral);
    }
}
