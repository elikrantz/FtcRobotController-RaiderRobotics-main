package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@Autonomous
@Config
public class testEncoderMotors extends LinearOpMode {
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private IMU imu;

    private double frontLeftVal = 0;
    private double frontRightVal = 0;
    private double backLeftVal = 0;
    private double backRightVal = 0;

    private boolean Backwards = false;
    private double Reverse = 1;

    public static double POWER = 0.25;

    @Override
    public void runOpMode() {
        Initializtion();
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.share) {
                Backwards = !Backwards;
            }
            if (Backwards) {
                Reverse = -1;
            } else {
                Reverse = 1;
            }

            if (gamepad1.circle) {
                frontLeftVal = frontLeft.getCurrentPosition() + (100 * Reverse);
            }
            if (gamepad1.triangle) {
                frontRightVal = frontRight.getCurrentPosition() + (100 * Reverse);
            }
            if (gamepad1.cross) {
                backLeftVal = backLeft.getCurrentPosition() + (100 * Reverse);
            }
            if (gamepad1.square) {
                backRightVal = backRight.getCurrentPosition() + (100 * Reverse);
            }

            frontLeft.setTargetPosition((int)frontLeftVal);
            frontRight.setTargetPosition((int)frontRightVal);
            backLeft.setTargetPosition((int)backLeftVal);
            backRight.setTargetPosition((int)backRightVal);

            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setPower(POWER);
            backRight.setPower(POWER);
            frontLeft.setPower(POWER);
            frontRight.setPower(POWER);

            telemetry.addLine(String.format("Front Left Encoder \n Actual: %d, Target: %6.1f",frontLeft.getCurrentPosition(),frontLeftVal));
            telemetry.addLine(String.format("Front Right Encoder \n Actual: %d, Target: %6.1f",frontRight.getCurrentPosition(),frontRightVal));
            telemetry.addLine(String.format("Back Left Encoder \n Actual: %6d, Target: %6.1f",backLeft.getCurrentPosition(),backLeftVal));
            telemetry.addLine(String.format("Back Right Encoder \n Actual: %6d, Target: %6.1f",backRight.getCurrentPosition(),backRightVal));
            telemetry.update();
        }
    }

    private void Initializtion() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft"); /** Port: ControlHub MotorPort 0 **/
        backLeft = hardwareMap.dcMotor.get("backLeft"); /** Port: ControlHub MotorPort 2 **/
        frontRight = hardwareMap.dcMotor.get("frontRight"); /** Port: ControlHub MotorPort 1 **/
        backRight = hardwareMap.dcMotor.get("backRight"); /** Port: ControlHub MotorPort 3 **/

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        //waitForStart();
    }

}
