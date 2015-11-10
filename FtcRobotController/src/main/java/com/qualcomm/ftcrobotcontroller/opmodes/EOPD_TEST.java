package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.ftccommon.DbgLog;
//import com.qualcomm.robotcore.util.Range;


/**
 * Created by Matt on 11/3/2015.
 */

public class EOPD_TEST extends OpMode {

    OpticalDistanceSensor opticalDistanceSensor;
    OpticalDistanceSensor v_sensor_ods;

    @Override
    public void init() {
        opticalDistanceSensor = hardwareMap.opticalDistanceSensor.get("ODS");
        v_sensor_ods = hardwareMap.opticalDistanceSensor.get("ODS");
        telemetry.addData("ODS Value", "Hello!");

        try
        {
            v_sensor_ods = hardwareMap.opticalDistanceSensor.get ("sensor_ods");
        }
        catch (Exception p_exeception)
        {
            try
            {
                v_sensor_ods = hardwareMap.opticalDistanceSensor.get("sensor_eopd");
            }
            catch (Exception p_exeception_eopd)
            {
                try
                {
                    v_sensor_ods = hardwareMap.opticalDistanceSensor.get("sensor_EOPD");
                }
                catch (Exception p_exeception_EOPD)
                {
                    //m_warning_message ("sensor_ods/eopd/EOPD");
                    DbgLog.msg
                            ("Can't map sensor_ods nor sensor_eopd, nor" +
                                            " sensor_EOPD ("
                                            + p_exeception_EOPD.getLocalizedMessage()
                                            + ").\n"
                            );

                    v_sensor_ods = null;
                }
            }
        }
    }

    @Override
    public void loop() {
        double odsReading = opticalDistanceSensor.getLightDetected();
        //double odsReading2 = opticalDistanceSensor.getLightDetectedRaw();

        telemetry.addData("ODS Value", odsReading);

        if ( odsReading < 1) {
            telemetry.addData("DirectionChange", "too close");
        } else if (odsReading > 8 ) {
            telemetry.addData("ODS Value", "too far");
        }
        //distanceSensor = hardwareMap.opticalDistanceSensor.get("dist1");
        //int lineSensor = distanceSensor.getLightDetectedRaw();

    }
}