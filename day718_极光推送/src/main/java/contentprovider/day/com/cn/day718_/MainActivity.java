package contentprovider.day.com.cn.day718_;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import cn.com.qubaoyang.R;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button checkImage;
    private ImageView imageContainer;
    private File f;
    private Button openCamera;
    private Button imageCrop;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkImage = (Button) findViewById(R.id.checkImage);
        openCamera = (Button) findViewById(R.id.openCamera);
        imageCrop = (Button) findViewById(R.id.imageCrop);
        imageCrop.setOnClickListener(this);
        openCamera.setOnClickListener(this);
        checkImage.setOnClickListener(this);
        imageContainer = (ImageView) findViewById(R.id.imageContainer);

        SensorManager sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
    }


    @Override
    protected void onResume() {
        super.onResume();

        JPushInterface.onResume(this);

    }


    @Override
    protected void onPause() {
        super.onPause();

        JPushInterface.onPause(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openCamera:

                //打开照相机
                f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), System.currentTimeMillis() + ".png");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 0);
                break;
            case R.id.checkImage:


                break;

            case R.id.imageCrop:

                if (bitmap != null) {
                    Intent intent1 = new Intent();
                    intent1.setAction("com.android.camera.action.CROP");
                    intent1.setDataAndType(Uri.fromFile(f), "image/*");
                    intent1.putExtra("corp", true);
                    intent1.putExtra("aspectX", 1);
                    intent1.putExtra("aspectY", 1);
                    intent1.putExtra("outputX", 200);
                    intent1.putExtra("outputY", 200);
                    intent1.putExtra("return-data", true);
                    startActivityForResult(intent1, 2);
                } else {
                    Toast.makeText(MainActivity.this, "没有图片资源", Toast.LENGTH_SHORT).show();
                } 
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
//            Bitmap data1 = data.getParcelableExtra("data");
            BitmapFactory.Options options = new BitmapFactory.Options();

            BitmapFactory.decodeFile(f.getPath(), options);
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            int i = outWidth / 400;
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;//设置为false才可以再次加载
            //二次采样
            bitmap = BitmapFactory.decodeFile(f.getPath(), options);
            imageContainer.setImageBitmap(bitmap);
//            imageContainer.setImageBitmap(data1);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
             bitmap = data.getParcelableExtra("data");
            imageContainer.setImageBitmap(bitmap);
        }
    }
}
