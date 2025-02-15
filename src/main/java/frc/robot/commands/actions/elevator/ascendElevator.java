package frc.robot.commands.actions.elevator;
import frc.robot.commands.actions.elevator.SetElevator;
import frc.robot.subsystems.Elevator;
public class ascendElevator {
    private Elevator elevator;
    public  ascendElevator(){
        elevator=elevator.getInstance();
    }
    public static void initialize(){
        if (Elevator.getElevation()==0){
            SetElevator.Level_1();
            Elevator.setElevation(1);
        }
        else if (Elevator.getElevation()==1){
            SetElevator.Level_2();
            Elevator.setElevation(2);
        }
        else if (Elevator.getElevation()==2){
            SetElevator.Level_Coral();
            Elevator.setElevation(3);
        }
        else if (Elevator.getElevation()==3){
            SetElevator.Level_3();
            Elevator.setElevation(4);
        }
        else if (Elevator.getElevation()==4){
            SetElevator.Level_4();
            Elevator.setElevation(5);
        }
        else{
            SetElevator.ground();
            Elevator.setElevation(0);
        }
    }
}
