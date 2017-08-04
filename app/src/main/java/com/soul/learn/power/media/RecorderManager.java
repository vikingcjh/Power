package com.soul.learn.power.media;


import android.content.Context;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by cjh on 2017/8/2.
 */

public class RecorderManager {
    private static final String TAG = "niodata";
    private static final RecorderManager ourInstance = new RecorderManager();

    public static RecorderManager getInstance() {
        return ourInstance;
    }

    private RecorderManager() {
    }

    private Camera mCamera;
    private CameraPreview mPreview;
    private MediaRecorder mMediaRecorder;
    private boolean isRecording = false;

    public boolean getIsRecordding() {
        return isRecording;
    }
    public SurfaceView getView() {
        return mPreview;
    }
    public boolean prepareVideoRecorder(Context context){

        mCamera = CameraManager.getCameraInstance();
        mPreview = new CameraPreview(context, mCamera);

        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        // Step 2: Set sources
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set output format and encoding (for versions prior to API Level 8)
        /*mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);*/

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));

        // Step 4: Set output file
        mMediaRecorder.setOutputFile(DataManager.getOutputMediaFile(DataManager.MEDIA_TYPE_VIDEO).toString());

        // Step 5: Set the preview output

        mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());

        // Step 6: Prepare configured MediaRecorder
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    public void setProperty() {

    }

    public void start() {
        if (mMediaRecorder != null) {
            mMediaRecorder.start();
            isRecording = true;
        }
    }
    public void stop() {
        mMediaRecorder.stop();
        releaseMediaRecorder();
        mCamera.lock();
        isRecording = false;
    }

    public void release() {
        releaseMediaRecorder();
        releaseCamera();
    }
    public void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            mCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
}
