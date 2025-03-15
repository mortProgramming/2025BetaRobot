// package frc.robot.commands.actions;

// import static frc.robot.config.constants.PhysicalConstants.ElevatorConstants.speedFactor;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.CoralGate;

// public class moveCoralGate extends Command{
//     private CoralGate coralGate;
//     private double speed;
//     public moveCoralGate(double speed){
//         coralGate = CoralGate.getInstance();
//         this.speed = speed;
//         addRequirements(coralGate);
//     }

//     public void initialize(){
//     }

//     public void execute(){
//         // coralGate.setSetpoint(coralGate.getSetpoint()+speed);
//         coralGate.setSpeed(speed);
//         // System.out.println("speed" + speed);
//     }

//     public void end(boolean interrupted){
//         coralGate.setSpeed(0);
//     }
    
//     public boolean isFinished(){
//         return false;
//     }   
// }