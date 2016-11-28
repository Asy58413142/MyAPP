package contentprovider.day.com.cn.day623_asynctaskloader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ann on 2016/6/23.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, "data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_sql = "CREATE TABLE student ("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"name TEXT,"+"score INTEGER)";
        db.execSQL(create_table_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
