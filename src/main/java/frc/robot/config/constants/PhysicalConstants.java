package frc.robot.config.constants;

import edu.wpi.first.math.util.Units;

public final class PhysicalConstants{
	public final class ElevatorConstants{
		public static final double ground=4;
		public static final double L1=30;
		public static final double L2=45;
		public static final double L3=50;
		public static final double L4=65;
		public static final double Coral=0;
		public static final double motorVoltage=12.0;
		public static final double speedFactor = (0.5);
		public static final double gravitySpeed = 0.05;

	}
	public static final class CoralCorralConstants{
		public static final double REST_ANGLE=0.1;
    	public static final double MAX_VELOCITY=0.1;
    	public static final double MAX_ACCELERATION=0.1;
    	public static final double MAX_VOLTAGE=12.0;
		public static final double L1=0.1;
		public static final double L2_3=25;
		public static final double dump=22.5;
		public static final double intake=60;
		public static final double speedFactor= -0.45;
		public static final double gravitySpeed=0.05;
	}
	public static final class servoConstants{
		public static double closedSetpoint=0.75;  //X button
		public static double openSetpoint=0.35;		//Y button
        public static double jamSetpoint=0.75;
		public static double unJamSetpoint=0.01;
		//Find openSetpoint 
	}
	public static final class climberConstants{
		public static double climbSetpoint=0.5;
		public static double descendSetpoint=0.01;
	}
}
