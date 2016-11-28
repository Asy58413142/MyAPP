package contentprovider.day.com.cn.day618_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by ann on 2016/6/20.
 */
public class SQLDBHandler {

    private MySQLiteDatabaseHelper helper;

    public SQLDBHandler(Context context) {
        helper = new MySQLiteDatabaseHelper(context);

    }


    public void insertAPI(String name, int age, int score) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabaseHelper.CL_NAME, name);
        values.put(MySQLiteDatabaseHelper.CL_AGE, age);
        values.put(MySQLiteDatabaseHelper.CL_SCORE, score);
        //表名
        //是否可以插入空记录  null  不可以，一般都为空
        //要插入的数据
        db.insert(MySQLiteDatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

    public void insert(String name, int age, int score) {
        String sql = "insert into " + MySQLiteDatabaseHelper.TABLE_NAME + "("
                + MySQLiteDatabaseHelper.CL_NAME + "," + MySQLiteDatabaseHelper.CL_AGE + "," + MySQLiteDatabaseHelper.CL_SCORE + ") values ( ?,?,?) ;";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[]{name, age, score});
        db.close();
    }


    public void upDateAPI(String name, String score) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MySQLiteDatabaseHelper.CL_NAME, name);
        db.update(MySQLiteDatabaseHelper.TABLE_NAME, values, "score = ?", new String[]{score});
        db.close();
    }

    public void upDate(String name, String score) {
        String sql="update student set name = ? where score = ? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql,new Object[]{name,score});
        db.close();
    }
    public void deleteAPI(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(MySQLiteDatabaseHelper.TABLE_NAME, "name = ?", new String[]{name});
        db.close();
    }

    public void delete(String name) {
        String sql="delete from student where name = ?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql,new Object[]{name});
        db.close();
    }
    public ArrayList<Student> queryAPI() {

        SQLiteDatabase db = helper.getReadableDatabase();
        //column 查询的字段名 null为查询所有的字段
        //selection 查询条件
        //selectionArgs 查询条件的值
        //groupBy 分组
        //having  分组条件
        //orderBy 是否排序，及方式
        ArrayList<Student> list = new ArrayList<Student>();
        Cursor cursor = db.query(MySQLiteDatabaseHelper.TABLE_NAME, null, null, null, null, null, "_id asc");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_NAME);
                int ageIndex = cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_AGE);
                int scoreIndex = cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_SCORE);
                String name = cursor.getString(nameIndex);
                int age = cursor.getInt(ageIndex);
                int score = cursor.getInt(scoreIndex);

                Student student = new Student(name, age, score);
                list.add(student);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
    public Cursor queryAPI1() {

        SQLiteDatabase db = helper.getReadableDatabase();
        //column 查询的字段名 null为查询所有的字段
        //selection 查询条件
        //selectionArgs 查询条件的值
        //groupBy 分组
        //having  分组条件
        //orderBy 是否排序，及方式
        ArrayList<Student> list = new ArrayList<Student>();
        Cursor cursor = db.query(MySQLiteDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }


    public ArrayList<Student> query() {
        String sql = "select * from " + MySQLiteDatabaseHelper.TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Student> list = new ArrayList<Student>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int nameIndex = cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_NAME);
                String columnName = cursor.getString(nameIndex);
                int ageIndex = cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_AGE);
                int columnAge = cursor.getInt(ageIndex);
                int scoreIndex = cursor.getColumnIndex(MySQLiteDatabaseHelper.CL_SCORE);
                String columnScore = cursor.getString(scoreIndex);
//                student.setName(columnName);
//                student.setAge(columnAge);
//                student.setScore(Integer.parseInt(columnScore));
                Student student = new Student(columnName, columnAge, Integer.valueOf(columnScore));
                list.add(student);
            }
        }
        cursor.close();
        db.close();
        return list;
    }
}
