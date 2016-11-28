package contentprovider.day.com.cn.day618_sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by ann on 2016/6/23.
 */
public class SQLiteContentProvider extends ContentProvider {

    private static String authority = "contentprovider.day.com.cn.day618_sqlite";
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, "student", 0);
    }

    private MySQLiteDatabaseHelper helper;
    private Cursor cursor;

    public SQLiteContentProvider() {

    }

    @Override
    public boolean onCreate() {
        helper = new MySQLiteDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = helper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case 0:
                cursor = db.query("student", projection, selection, selectionArgs, null, null, sortOrder);

        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
