package frc.robot.commands.actions;

import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.speedFactor;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber;

public class moveClimber extends Command{
    private Climber climber;
    private double speed;
    public moveClimber(double speed){
        climber = Climber.getInstance();
        this.speed = speed;
        addRequirements(climber);
    }

    public void initialize(){
    }

    public void execute(){
        // climber.setSetpoint(climber.getSetpoint()+speed);
        climber.setSpeed(speed);
        System.out.println("speed" + speed);
    }

    public void end(boolean interrupted){
        climber.setSpeed(0);
    }
    
    public boolean isFinished(){
        return false;
    }   
}
