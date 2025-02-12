package frc.robot.commands.actions.CoralCorral.Gate;
import frc.robot.subsystems.CoralGate;
import edu.wpi.first.wpilibj2.command.Command;
public class changeCoralGate extends Command{
    private boolean isOpen;
    private CoralGate coralGate;
    public void initialize(){
        isOpen=false;
        coralGate.close();
    }
    public void execute(){
        if(isOpen){
            coralGate.close();
            isOpen=false;
        }
        else{
            coralGate.open();
            isOpen=true;
        }
    }
    public void end(boolean interrupted){}
    public boolean isFinished(){
        return false;
    }  
}
