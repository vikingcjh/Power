package com.soul.learn.power.media;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import com.soul.learn.power.log.LogUtil;

/**
 * Created by cjh on 2017/8/4.
 */

public class CameraManager {

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            LogUtil.i("Camera.getNumberOfCameras()= "+Camera.getNumberOfCameras());
            c = Camera.open(0); // attempt to get a Camera instance
            c.setDisplayOrientation(90);
            Camera.Parameters parameters= c.getParameters();
            parameters.setRotation(90);
            parameters.setFlashMode(Camera.Parameters.FOCUS_MODE_AUTO);

            c.setParameters(parameters);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
