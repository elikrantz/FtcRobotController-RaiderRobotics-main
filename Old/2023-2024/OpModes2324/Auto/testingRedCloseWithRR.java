/*
package org.firstinspires.ftc.teamcode.OpModes2324.Auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Robot.Intake;
import org.firstinspires.ftc.teamcode.Hardware.Robot.LinearSlideOutdated;
import org.firstinspires.ftc.teamcode.Hardware.Robot.MecanumChassis;
import org.firstinspires.ftc.teamcode.Hardware.aprilTagDetectionMovement;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


@Autonomous(preselectTeleOp = "teleOpCombinedDrivesComp2")
@Config
@Disabled
public class testingRedCloseWithRR extends LinearOpMode {
    protected MecanumChassis chassis = new MecanumChassis(this);
    Intake intake = new Intake(this);
    //ColorCam color = new ColorCam(this);
    aprilTagDetectionMovement aTag = new aprilTagDetectionMovement(this);
    LinearSlideOutdated slide = new LinearSlideOutdated(this);
    //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    //SampleMecanumDrive drive;

    private double timeToContinue = 5;
    private ElapsedTime continueTime = new ElapsedTime();

    public static double POWER = 0.5;

    public void placeOnSpikeMarkUpdated(String proximity){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        chassis.initializeMovement();
        //Move to center of spike marks
        double power = .5;
        if(proximity.toLowerCase().equals("close")) {
            //Aligned to the right
            //Check for left and center
            if(aTag.spikeLocation.equals("LEFT")) {
                //Move to the center
                //chassis.move(0.5, "forward", 23+10);
                //chassis.move(.5, "left", 4);
                //chassis.move(.5,"backward",6);
                Trajectory trajCenter1 = drive.trajectoryBuilder(new Pose2d())
                        .lineTo(new Vector2d(23+10,0))
                        .lineTo(new Vector2d(23+10,-4))
                        .lineTo(new Vector2d(23+4,-4))
                        .build();
                drive.followTrajectory(trajCenter1);
                intake.lowerLip();
                sleep(1000);
                //chassis.move(.5,"backward",6);
                Trajectory trajCenter2 = drive.trajectoryBuilder(new Pose2d())
                        .lineTo(new Vector2d(23-2,-4))
                        .build();
                drive.followTrajectory(trajCenter2);
                intake.raiseLip();
                //chassis.move(.5, "backward", 30);
                //chassis.move(.5,"backward",2);
                //chassis.move(.5, "right", 4);
                Trajectory trajCenter3 = drive.trajectoryBuilder(new Pose2d())
                        .lineTo(new Vector2d(23-2,0))
                        .build();
                drive.followTrajectory(trajCenter3);
            } else if (aTag.spikeLocation.equals("CENTER")) {
                //Move to the right
                /*chassis.move(0.5, "forward", 23+4);
                chassis.move(.5, "left", 8);
                chassis.move(.5,"backward",4);
                Trajectory trajCenter1 = drive.trajectoryBuilder(new Pose2d())
                        .lineTo(new Vector2d(23+10,0))
                        .lineTo(new Vector2d(23+10,-4))
                        .lineTo(new Vector2d(23+4,-4))
                        .build();
                drive.followTrajectory(trajCenter1);
                //Do Intake Servo
                intake.lowerLip();
                sleep(1000);
                chassis.move(.5,"backward",6);
                intake.raiseLip();
                //chassis.move(.5, "backward", 24);
                chassis.move(.5, "right", 12);
                chassis.move(.5,"forward",6);
            } else {
                //Move to the left
                chassis.move(0.5, "forward", 26);
                chassis.move(.5, "left", 4);
                //chassis.rotate(90,.5);
                /*Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                        .lineToLinearHeading(new Pose2d(new Vector2d(28, 0), Math.toRadians(90)))
                        .build();
                drive.followTrajectory(traj1);
                //sleep(2000);

                chassis.move(.5,"forward",0+8);
                chassis.move(.5,"backward",4);
                //Do Intake Servo
                intake.lowerLip();
                sleep(1000);
                chassis.move(.5,"backward",4);
                intake.raiseLip();
                //chassis.move(.5, "backward", 30);
                //chassis.move(.5, "right", 6);
                //chassis.move(.5,"forward",4);
                chassis.rotate(-90,.5);
            }
        }
        if(proximity.toLowerCase().equals("far")) {
            //Aligned to the left
            //Check for center and right
            if(aTag.spikeLocation.equals("RIGHT")) {
                //Move to the right
                chassis.move(.5, "forward", 26);
                //chassis.move(.5, "left", 4);
                chassis.rotate(-90,.5);
                chassis.move(.5,"forward",0+8);
                chassis.move(.5,"backward",4);
                //Do Intake Servo
                intake.lowerLip();
                sleep(1000);
                chassis.move(.5,"backward",4);
                intake.raiseLip();
                //chassis.move(.5, "backward", 30);
                //chassis.move(.5, "right", 6);
                //chassis.move(.5,"forward",4);
                chassis.rotate(90,.5);
            } else if (aTag.spikeLocation.equals("CENTER")) {
                //Move to the center
                chassis.move(.5, "forward", 23+10);
                chassis.move(.5, "right", 2);
                chassis.move(.5,"backward",6);
                intake.lowerLip();
                sleep(1000);
                chassis.move(.5,"backward",6);
                intake.raiseLip();
                //chassis.move(.5, "backward", 30);
                //chassis.move(.5,"backward",2);
                //chassis.move(.5, "left", 2);
            } else {
                //Move to the left
                chassis.move(.5, "forward", 23+4);
                chassis.move(.5, "left", 8);
                chassis.move(.5,"backward",4);
                //Do Intake Servo
                intake.lowerLip();
                sleep(1000);
                chassis.move(.5,"backward",6);
                intake.raiseLip();
                //chassis.move(.5, "backward", 24);
                chassis.move(.5, "right", 10);
                chassis.move(.5,"forward",6);
            }
        }
    }
    public aprilTagDetectionMovement.backBoardAprilTags altAprilTag(String loc, String proximity, String teamColor){
        if(teamColor.equals("red")) {
            if (proximity.equals("close")) {
                if (loc.equals("LEFT")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.RedAllianceCenter;
                }
                if (loc.equals("CENTER")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.RedAllianceRight;
                } else {
                    return aprilTagDetectionMovement.backBoardAprilTags.RedAllianceLeft;
                }
            }
            if (proximity.equals("far")) {
                if (loc.equals("RIGHT")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.RedAllianceRight;
                }
                if (loc.equals("CENTER")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.RedAllianceCenter;
                } else {
                    return aprilTagDetectionMovement.backBoardAprilTags.RedAllianceLeft;
                }
            }
        }
        if(teamColor.equals("blue")) {
            if (proximity.equals("close")) {
                if (loc.equals("RIGHT")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceRight;
                }
                if (loc.equals("CENTER")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceCenter;
                } else {
                    return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceLeft;
                }
            }
            if (proximity.equals("far")) {
                if (loc.equals("LEFT")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceCenter;
                }
                if (loc.equals("CENTER")) {
                    return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceRight;
                } else {
                    return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceLeft;
                }
            }
        }
        return aprilTagDetectionMovement.backBoardAprilTags.BlueAllianceRight;
    }


    public void runOpMode(){
        chassis.initializeMovement();
        intake.initIntake();
        slide.initSlides();
        aTag.initCam2();
        aTag.camOn();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        while(!isStarted()){
            aTag.updateSpikeLocation();
            switch (aTag.spikeLocation) {
                case "NONE":
                    telemetry.addData("Location", "LEFT");
                    break;
                case "CENTER":
                    telemetry.addData("Location", "RIGHT");
                    break;
                case "RIGHT":
                    telemetry.addData("Location", "CENTER");
                    break;
                default:
                    telemetry.addData("Location", aTag.spikeLocation);
                    break;
            }
            //telemetry.addData("Location 2", aTag.spikeLocation);
            telemetry.update();
        }

        waitForStart();
        final String location = aTag.spikeLocation;
        //chassis.rotate(-90,0.5);
        placeOnSpikeMarkUpdated("close");
        //aTag.camOff();
        //chassis.move(.5, "forward", 25);
        chassis.rotate(-90, .5);
        //chassis.move(.5, "forward", 24);
        //chassis.move(.5, "left", 6);
        chassis.move(.5,"left",8);
        //aTag.initCam2(); //Maybe reinitializing will fix the thing?
        //aTag.camOn();

        //aprilTagDetectionMovement.backBoardAprilTags[] array = {altAprilTag(location)};
        //aTag.moveToAprilTag(array[0]);

        //aTag.initCam2();
        continueTime.reset();
        //while (aTag.getDetections().size() < 3 && opModeIsActive()) {
        while (aTag.getDetections().size() < 3 && continueTime.seconds() <= timeToContinue && opModeIsActive()) {
            telemetry.addData("%f",aTag.getDetections().size());
            telemetry.update();
            sleep(10);
        }
        //telemetry.addData("%f",aTag.getDetections().size());
        //telemetry.addLine(String.format("XY %6.1f %6.1f  (inch)",aTag.outputInfo[0],aTag.outputInfo[1]));
        //telemetry.update();
        //sleep(5000);
        //aTag.camOff();
        aTag.moveToAprilTag(altAprilTag(location, "close", "red"));

        aTag.camOff();
        chassis.move(.5,"right",15);
        chassis.rotate(180, .5);
        sleep(400);
        /*Trajectory traj1 = drive.trajectoryBuilder(new Pose2d())
                .lineToLinearHeading(new Pose2d(new Vector2d(0, 15), Math.toRadians(180)))
                .build();
        drive.followTrajectory(traj1);

        telemetry.addLine(String.format("XY %6.1f %6.1f  (inch)",aTag.outputInfo[0],aTag.outputInfo[1]));
        telemetry.update();
        //sleep(5000);
        chassis.move(.5, "backward", aTag.outputInfo[1]);
        chassis.move(.5, "left", aTag.outputInfo[0] - 20);
        /*Trajectory traj3 = drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(aTag.outputInfo[1]-20, aTag.outputInfo[0]),Math.toRadians(0))
                .build();
        drive.followTrajectory(traj3);
        chassis.move(.5,"backward",2.5);
        slide.slide(30,0.5);
        sleep(1000);
        slide.slide(0,0.5);
        //aTag.camOff();
        //telemetry.addData("hooray","hooray");
        telemetry.update();
        //chassis.parkFarRed();
        telemetry.addData("finishing","");
        telemetry.update();
        terminateOpModeNow();
    }
}

 */
