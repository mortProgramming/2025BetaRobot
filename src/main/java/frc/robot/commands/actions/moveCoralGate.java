package frc.robot.commands.actions;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.speedFactor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralGate;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
public class moveCoralGate extends Command {
    private CoralGate coralGate;
    private double speed;
    private CommandXboxController xboxController;
    //Boolean left is whether or not we're using left trigger for this. It's needed so that we know if we're intaking or outputting corral since right trigger is output & left is input.
    private boolean left;

    public moveCoralGate(double speed){
        coralGate = CoralGate.getInstance();
        this.speed = speed;
        addRequirements(coralGate);
        xboxController=null;
        left=false;
    }
    public moveCoralGate(CommandXboxController xboxController, boolean left){
        coralGate = CoralGate.getInstance();
        this.xboxController = xboxController;
        addRequirements(coralGate);
        this.left=left;
    }

    public void initialize(){
    }
    public void execute(){
        if (xboxController==null)
            coralGate.setSpeed(speed);
        else if(left)
            coralGate.setSpeed((xboxController.getLeftTriggerAxis())*0.75);
        else
            coralGate.setSpeed((-xboxController.getRightTriggerAxis())*0.75);
    }

    public void end(boolean interrupted){
        coralGate.setSpeed(0);
    }
    
    public boolean isFinished(){
        return false;
    }   
}