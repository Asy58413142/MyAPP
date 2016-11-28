package contentprovider.day.com.cn.day621_apk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private InputStream inputStream;
    private InputStream in;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in = getResources().openRawResource(R.raw.school);

        try {
            inputStream = getAssets().open("school.db");

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cache = getExternalCacheDir();
            File file = new File(cache, "school.db");
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] b = new byte[2048];
            int len;
            try {
                while ((len = inputStream.read(b)) != -1) {
                    try {
                        outputStream.write(b, 0, len);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file, null);
            Cursor student = db.query("student", null, null, null, null, null, null);
            if (student.getCount() > 0) {
                while (student.moveToNext()) {
                    String name = student.getString(student.getColumnIndex("name"));
                    int age = student.getInt(student.getColumnIndex("age"));
                    int score = student.getInt(student.getColumnIndex("score"));
                    Log.d("DB", "姓名：" + name + ";年龄：" + age + "；分数："+score);
                }
            }
        }
    }
}
