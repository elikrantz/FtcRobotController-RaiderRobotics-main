package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class AutoIntakeAidenBlueFar extends LinearOpMode {
    MecanumChassis chassis = new MecanumChassis(this);
    //Intake intake = new Intake(this);
    ColorCam color = new ColorCam(this);
    AprilTagCam aTag = new AprilTagCam(this);
    //LinearSlide slide = new LinearSlide(this);

    public void placeOnSpikeMark(){
        //Move to center of spike marks
        double power = -.3;
        if(color.spikeLocation.equals("LEFT")) {
            chassis.move(.3, "forward", 18);
            chassis.move(.3, "left", 12);
            //intake.powerOnTimed(power, 3);
            chassis.move(.3, "right", 12);
            chassis.move(.3, "backward", 18);
        } else if(color.spikeLocation.equals("RIGHT")){
            chassis.move(.3, "forward", 18);
            chassis.move(.3, "right", 12);
            //intake.powerOnTimed(power, 3);
            chassis.move(.3, "left", 12);
            chassis.move(.3, "backward", 18);
        } else if(color.spikeLocation.equals("CENTER")){
            chassis.move(.3, "forward", 25);
            //intake.powerOnTimed(power, 3);
            chassis.move(.3, "backward", 25);
        } else {
            telemetry.addData("Team Element", "Not Found");
            telemetry.update();
        }
    }
    public void altAprilTag(){

    }

    public void runOpMode(){
        chassis.initializeMovement();
        //intake.initIntake();
        color.initCam();
        color.camOn();
        aTag.initCam();

        while(!isStarted()){
            color.updateSpikeLocation();
            telemetry.addData("Location", color.spikeLocation);
            telemetry.update();
        }

        waitForStart();
        final String location = color.spikeLocation;
        placeOnSpikeMark();
        chassis.parkFarBlue();
    }
}
