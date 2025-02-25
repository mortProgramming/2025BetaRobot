package frc.robot.config.constants;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

public final class PIDConstants {
    
    public final class Drivetrain {

        public static final double AUTON_POS_KP = 0.315;
		public static final double AUTON_POS_KI = 0;
		public static final double AUTON_POS_KD = 0.001;
	
		public static final double AUTON_ROTATION_KP = 1.45;
		public static final double AUTON_ROTATION_KI = 0;
		public static final double AUTON_ROTATION_KD = 0;
    }
	public final class ElevatorPID{
		public static final double KP = 0.04;
		public static final double KI = 0;
		public static final double KD = 0;
		public static final double KS = 0;
		public static final double KG = -0.05;
		public static final double KV = 0;
		public static final double KA = 0;		
	}
	public final class CoralCorralPID{
		public static final double KP = 0.1;
		public static final double KI = 0.1;
		public static final double KD = 0.1;
		public static final double KS = 0.1;
		public static final double KG = 0.1;
		public static final double KV = 0.1;
		public static final double KA = 0.1;	
		public static final Constraints CoralCorralConstants= new Constraints(0,0);	
	}
}
