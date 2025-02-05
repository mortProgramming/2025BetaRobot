package frc.robot.config.constants;

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
		public static final double KP = 0;
		public static final double KI = 0;
		public static final double KD = 0;
		public static final double KS = 0;
		public static final double KG = 0;
		public static final double KV = 0;
		public static final double KA = 0;
        
		// public void setElevatorPosition(double targetPosition) {
            
        // }
		
	}
}
