package com.string.opencvdemo;

import android.app.Application;

import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class CVApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OpenCVLoader.initAsync("3.4.7",this,new LoaderCallbackInterface(){

            @Override
            public void onManagerConnected(int status) {

            }

            @Override
            public void onPackageInstall(int operation, InstallCallbackInterface callback) {

            }
        });
    }
}
