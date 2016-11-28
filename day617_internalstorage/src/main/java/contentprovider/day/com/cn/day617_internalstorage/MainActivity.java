package contentprovider.day.com.cn.day617_internalstorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * InternalStorage 内部存储器，私有的文件保存数据，外部程序和用户恕不能看到的，除非root后
 * 程序卸载以后，data/data/包名下，所有的文件删掉
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MODE_APPEND 在原有的数据后面追加数据
        //MODE_PRIVATE 把原有的数据覆盖掉
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput("user_info.text", MODE_PRIVATE);
            fileOutputStream.write(new Byte("hahahah"));
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream in = openFileInput("user_info.text");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String data = bufferedReader.readLine();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
