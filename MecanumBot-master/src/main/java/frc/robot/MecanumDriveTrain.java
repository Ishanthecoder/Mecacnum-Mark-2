package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive; 
import frc.robot.LambdaJoystick.ThrottlePosition;

public class MecanumDriveTrain {
    private static final int kFrontLeftChannel = 1;
    private static final int kRearLeftChannel = 0;
    private static final int kFrontRightChannel = 3;
    private static final int kRearRightChannel = 2;

    private CANSparkMax frontLeft;
    private CANSparkMax rearLeft;
    private CANSparkMax frontRight;
    private CANSparkMax rearRight;

    private boolean driveInverted = false;

    private MecanumDrive m_robotDrive;

    public MecanumDriveTrain() {
        rearLeft = new CANSparkMax(kRearLeftChannel, MotorType.kBrushless); 
        frontLeft = new CANSparkMax(kFrontLeftChannel, MotorType.kBrushless); 
        frontRight = new CANSparkMax(kFrontRightChannel, MotorType.kBrushless); 
        rearRight = new CANSparkMax(kRearRightChannel, MotorType.kBrushless); 

        // Invert the left side motors
        frontLeft.setInverted(true);
        rearLeft.setInverted(true);

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public MecanumDrive getRobotDrive() { return m_robotDrive;}

    public void invertDrive() {
        driveInverted = !driveInverted;
    }
    public void spinMotors() {
        frontLeft.set(.5);
        frontRight.set(.5);
        rearLeft.set(.5);
        rearRight.set(.5);
    }

    public void updateSpeed(final ThrottlePosition throttlePos) {
        //double ySpeed, double xSpeed, double zRotation, double gyroAngle
        //z should make it turn, rest should 
        if (driveInverted) {
            m_robotDrive.driveCartesian(-throttlePos.y, -throttlePos.x, -throttlePos.z, 0); //gyro angle where 0 is
        } else {
            m_robotDrive.driveCartesian(throttlePos.y, throttlePos.x, throttlePos.z, 0); //gyro angle where 0 is
        }
        
    } //Might not be needed, just depends on if we want to use Lambda joystick
} ;