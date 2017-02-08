package g7.zcf.DBDemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Create by zcf on 2017/1/23 0023 15:08
 */
public class DBHelper {
    private SqlDB mSqlDB;
    private SQLiteDatabase mSQLiteDatabase;

    public void initDB(Context context, String dbName, int version) {
        if (mSqlDB == null) {
            mSqlDB = new SqlDB(context, dbName, null, version);
        }
        mSQLiteDatabase = mSqlDB.getWritableDatabase();
    }

    public void insert() {
    }
}
