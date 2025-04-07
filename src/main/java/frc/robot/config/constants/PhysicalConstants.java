package frc.robot.config.constants;

import edu.wpi.first.math.util.Units;

public final class PhysicalConstants{
	public final class ElevatorConstants{
		public static final double ground = 6;
		public static final double L1 = 6;
		public static final double L2 = 15;
		public static final double L3 = 32;
		public static final double L4 = 70;
		public static final double Coral = 6;
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
		public static final double dump = 0.3;
		public static final double dumpL3 = 0.33;
		public static final double dumpL4 = 0.29;
		public static final double intake = 0.5;
		public static final double intakeCor = 0.6;
		public static final double ground = 15;
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
	public static final class limeLightConstants{
		public static double CAMERA_LEFT_OFFSET = 0.0;
		public static double CAMERA_RIGHT_OFFSET = 0.0;
	}
}
