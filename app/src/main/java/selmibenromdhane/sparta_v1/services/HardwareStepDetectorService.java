package selmibenromdhane.sparta_v1.services;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import selmibenromdhane.sparta_v1.utils.AndroidVersionHelper;


public class HardwareStepDetectorService extends AbstractStepDetectorService {

    private static final String LOG_TAG = HardwareStepDetectorService.class.getName();

    /**
     * Creates an HardwareStepDetectorService.
     */
    public HardwareStepDetectorService(){
        this("");
        // required empty constructor
    }

    /**
     * Creates an HardwareStepDetectorService.
     *
     * @param name Name for the worker thread, use it for debugging purposes
     */
    public HardwareStepDetectorService(String name) {
        super(name);
        Log.i(LOG_TAG, "Created step counter service");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_STEP_DETECTOR)
            return;

        this.onStepDetected(1);
    }

    @SuppressLint("InlinedApi")
    @Override
    public int getSensorType() {
        if (AndroidVersionHelper.supportsStepDetector(getPackageManager())) {
            return Sensor.TYPE_STEP_DETECTOR;
        } else {
            return 0;
        }
    }
}
