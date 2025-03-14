package frc.robot.commands.actions;
import frc.robot.subsystems.CoralCorral;

import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L1;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L2_3;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.L2_3;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.dump;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.ground;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.intake;
import static frc.robot.config.constants.PhysicalConstants.CoralCorralConstants.gravitySpeed;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

public class setCoralCorral extends Command{
    private CoralCorral coralCorral;
    private double targetPosition;
    private PIDController positioncontroller = new PIDController(0.04, 0, 0);
    public setCoralCorral(double targetPosition){
        coralCorral = coralCorral.getInstance();
        this.targetPosition = targetPosition;
        addRequirements(coralCorral);
    }
    public void initialize(){

    }
    
    public void execute(){
        // coralCorral.setPosition(-coralCorral.getPIDController().calculate(coralCorral.getPosition(), targetPosition));
        positioncontroller.setSetpoint(targetPosition);
        double speed = positioncontroller.calculate(coralCorral.getPositionEncode());
        coralCorral.setSpeed(speed*.5);
        // System.out.println("Speed: " + speed + " Target Position: " + targetPosition + " Encoder Position: " + coralCorral.getPosition());
    }
    
    public boolean isFinished(){
        return false;
    }
    public void end(boolean interrupted){
        coralCorral.setMotorPercent(gravitySpeed);
    }

    // public static Command L1(){
    //     return new setCoralCorral(L1);
    // }
    
    // public static Command L2_3(){
    //     return new setCoralCorral(L2_3);
    // }

    // public static Command L4(){
    //     return new setCoralCorral(L4);
    // }

    public static Command dump(){
        return new setCoralCorral(dump);
    }
    public static Command intake(){
        return new setCoralCorral(intake);
    }

    public static Command ground(){
        return new setCoralCorral(ground);
    }
}
