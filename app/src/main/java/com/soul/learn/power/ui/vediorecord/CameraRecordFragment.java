package com.soul.learn.power.ui.vediorecord;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;

import com.soul.learn.power.R;
import com.soul.learn.power.log.LogUtil;
import com.soul.learn.power.media.CameraManager;
import com.soul.learn.power.media.CameraPreview;
import com.soul.learn.power.media.DataManager;
import com.soul.learn.power.media.RecorderManager;
import com.soul.learn.power.ui.base.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by cjh on 2017/8/4.
 */

public class CameraRecordFragment extends BaseFragment implements RecordContract.View{
    private static final String TAG = "niodata";
    private Context mContext;

    @BindView(R.id.camera_preview)
    FrameLayout mCamera_preview;
    @BindView(R.id.button_capture)
    Button mButton_capture;

//    private Camera mCamera;
//    private CameraPreview mPreview;

    @Override
    public void initPresenter() {
        if (mPresenter == null){
            RecordModel model = new RecordModel();
            mPresenter = new RecordPresenter(model,this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video_preview;
    }

    @Override
    public void initWidget() {
        mContext = getContext();
    }

    @Override
    public void startModel() {
        /*// Create an instance of Camera
        mCamera = CameraManager.getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(mContext, mCamera);
        mCamera_preview.addView(mPreview);*/
    }

    public void prepareMediaDevice() {
        if (RecorderManager.getInstance().prepareVideoRecorder(mContext)) {


        } else {
            // prepare didn't work, release the camera
            RecorderManager.getInstance().releaseMediaRecorder();
            mCamera_preview.removeAllViews();
        }
        mCamera_preview.addView(RecorderManager.getInstance().getView());
    }

    public void releaseMediaDevice() {
        RecorderManager.getInstance().release();
        mCamera_preview.removeAllViews();
    }

//    private boolean isRecording = false;
    @OnClick(R.id.button_capture)
    public void clickCapture(){

        if (RecorderManager.getInstance().getIsRecordding()) {
            // stop recording and release camera
            RecorderManager.getInstance().stop();

            // inform the user that recording has stopped
            mButton_capture.setText("Capture");
        } else {
            RecorderManager.getInstance().start();
            /*// initialize video camera
            if (RecorderManager.getInstance().prepareVideoRecorder(mContext)) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can start recording
                RecorderManager.getInstance().start();

                // inform the user that recording has started
                mButton_capture.setText("Stop");
            } else {
                // prepare didn't work, release the camera
                RecorderManager.getInstance().releaseMediaRecorder();
                // inform user
            }*/
        }
//        mCamera.takePicture(null, null, mPicture);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareMediaDevice();
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaDevice();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }


    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            camera.startPreview();

            File pictureFile = DataManager.getOutputMediaFile(DataManager.MEDIA_TYPE_IMAGE);
            LogUtil.i("pictureFile.getAbsolutePath()="+pictureFile.getAbsolutePath());
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions: ");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };
}
