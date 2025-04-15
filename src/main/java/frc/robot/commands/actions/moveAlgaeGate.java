package frc.robot.commands.actions;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.speedFactor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AlgaeGate;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class moveAlgaeGate extends Command {
    private AlgaeGate algaeGate;
    private double speed;
    private CommandXboxController xboxController;
    //Boolean left is whether or not we're using left trigger for this. It's needed so that we know if we're intaking or outputting corral since right trigger is output & left is input.
    private boolean left;

    public moveAlgaeGate(double speed){
        algaeGate = AlgaeGate.getInstance();
        this.speed = speed;
        addRequirements(algaeGate);
        xboxController=null;
        left=false;
    }
    
    public moveAlgaeGate(CommandXboxController xboxController, boolean left){
        algaeGate = AlgaeGate.getInstance();
        this.xboxController = xboxController;
        addRequirements(algaeGate);
        this.left=left;
    }

    public void initialize(){
    }
    public void execute(){
        if (xboxController==null)
            algaeGate.setSpeed(speed);
        else if(left)
            algaeGate.setSpeed((xboxController.getLeftTriggerAxis())*0.75);
        else
            algaeGate.setSpeed((-xboxController.getRightTriggerAxis())*0.75);
    }

    public void end(boolean interrupted){
        algaeGate.setSpeed(0);
    }
    
    public boolean isFinished(){
        return false;
    }   
}