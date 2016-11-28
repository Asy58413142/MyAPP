package contentprovider.day.com.cn.day618_sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ann on 2016/6/20.
 */
public class MySQLiteDatabaseHelper extends SQLiteOpenHelper {

    public static String TABLE_NAME = "student";
    public static String CL_NAME = "name";
    public static String CL_AGE = "age";
    public static String CL_SCORE = "score";
    public String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CL_NAME + " text,"
            + CL_AGE + " integer,"
            + CL_SCORE + " integer)";

    public MySQLiteDatabaseHelper(Context context) {
        super(context, "schools.db", null, 1);
    }

    public MySQLiteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
