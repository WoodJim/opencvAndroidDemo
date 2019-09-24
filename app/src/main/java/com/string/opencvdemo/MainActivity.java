package com.string.opencvdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        if(!OpenCVLoader.initDebug())
        {
            Log.d("opencv","初始化失败");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        if (OpenCVLoader.initDebug()) {
            Toast.makeText(this,"init success 成功",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"init success 失败",Toast.LENGTH_LONG).show();
        }
        testOpencv();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    private void testOpencv() {
        ImageView imageView = (ImageView)findViewById(R.id.iv_test);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.net_module_arch);
        Mat src = new Mat();
        Mat dst = new Mat();
        Utils.bitmapToMat(bitmap, src);
        //new Size(width, height)
        Imgproc.resize(src, dst, new Size(400,600),0,0,Imgproc.INTER_AREA);
        Bitmap bitmap1 = Bitmap.createBitmap(dst.cols(),dst.rows(),Bitmap.Config.RGB_565);
        Utils.matToBitmap(dst, bitmap1);
        imageView.setImageBitmap(bitmap1);

    }
}
