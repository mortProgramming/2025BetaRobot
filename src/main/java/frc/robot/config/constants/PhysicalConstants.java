package frc.robot.config.constants;

import edu.wpi.first.math.util.Units;

public final class PhysicalConstants{
	public final class ElevatorConstants{
		public static final double ground = 2;
		public static final double L1 = 6;
		public static final double L2 = 18;
		public static final double L3 = 35;
		public static final double L4 = 70;
		public static final double Coral = 0;
		public static final double motorVoltage = 12.0;
		public static final double speedFactor = 0.75;
		public static final double gravitySpeed = 0.075;

	}
	public static final class CoralCorralConstants{
		public static final double REST_ANGLE = 0.1;
    	public static final double MAX_VELOCITY = 0.1;
    	public static final double MAX_ACCELERATION = 0.1;
    	public static final double MAX_VOLTAGE = 12.0;
		public static final double L1 = 25;
		public static final double L2_3 = 60;
		public static final double dump = 30;
		public static final double dumpL1 = 29;
		public static final double dumpL4 = 31;
		public static final double intake = 50;
		public static final double intakeCor = 36;
		public static final double ground = 29;
		public static final double speedFactor = -0.45;
		public static final double gravitySpeed = 0.05;
		public static final double MIN_POSITION = 0.25;
		public static final double MAX_POSITION = 0.6;
		
	}
	public static final class servoConstants{
		public static double closedSetpoint = 0.95;  //X button
		public static double openSetpoint = 0.45;		//Y button
        public static double jamSetpoint = 0.75;
		public static double unJamSetpoint = 0.01;
		//Find openSetpoint 
	}
	public static final class climberConstants{
		public static double climbSetpoint = 0.5;
		public static double descendSetpoint = 0.01;
	}
}
