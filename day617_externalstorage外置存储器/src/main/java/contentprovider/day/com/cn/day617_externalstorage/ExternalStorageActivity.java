package contentprovider.day.com.cn.day617_externalstorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

import static android.os.Environment.*;

/**
 * 使用ExternalStorage外部存储的时候一定要添加读写的权限
 * 注意：4.4以后就不需要权限了
 * mnt/storage/Android/data  存放app私有的数据
 *
 */

public class ExternalStorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        String state = getExternalStorageState();
        if (MEDIA_MOUNTED.equals(state)) {
//            return ;//判断是否经行读写的
            //获取sd卡的路径
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            //得到公共文件夹
            File pictures_public = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            //获取私有文件夹的路径
            //只有自己的使用
            //当程序卸载，私有文件夹会被删掉
            File pictures_private =getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            //内部缓存文件夹
            File internal_cacheDir = getCacheDir();
            //外部缓存文件夹
            File external_cacheDir = getExternalCacheDir();
        }
    }
}
