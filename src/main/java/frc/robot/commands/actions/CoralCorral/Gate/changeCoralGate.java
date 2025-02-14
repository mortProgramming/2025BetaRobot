package frc.robot.commands.actions.CoralCorral.Gate;
import frc.robot.subsystems.CoralGate;
import static frc.robot.subsystems.CoralGate.*;
import edu.wpi.first.wpilibj2.command.Command;
public class changeCoralGate extends Command{
    private CoralGate coralGate;
    public void initialize(){
        if(!getIsClosed()){
            coralGate.close();
            setIsClosed(true);
        }
        else{
            coralGate.open();
            setIsClosed(false);
        }
    }
    public void execute(){}
    public void changeCoralGate(){
        coralGate=this.coralGate();
    }
    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }  
}
