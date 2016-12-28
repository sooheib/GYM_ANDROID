package selmibenromdhane.sparta_v1.utils;

import android.content.pm.PackageManager;

import selmibenromdhane.sparta_v1.services.AbstractStepDetectorService;
import selmibenromdhane.sparta_v1.services.AccelerometerStepDetectorService;
import selmibenromdhane.sparta_v1.services.HardwareStepCounterService;
import selmibenromdhane.sparta_v1.services.HardwareStepDetectorService;



public class Factory {

    /**
     * Returns the class of the step detector service which should be used
     *
     * The used step detector service depends on different soft- and hardware preferences.
     * @param pm An instance of the android PackageManager
     * @return The class of step detector
     */
    public static Class<? extends AbstractStepDetectorService> getStepDetectorServiceClass(PackageManager pm){
        if(pm != null) {
            if (AndroidVersionHelper.supportsStepCounter(pm))
                return HardwareStepCounterService.class;
            if (AndroidVersionHelper.supportsStepDetector(pm))
                return HardwareStepDetectorService.class;
        }
        return AccelerometerStepDetectorService.class;
    }
}
