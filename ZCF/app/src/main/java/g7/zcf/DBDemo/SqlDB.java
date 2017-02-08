package g7.zcf.DBDemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create by zcf on 2017/1/23 0023 14:58
 */
public class SqlDB extends SQLiteOpenHelper{

    private String mDbName;

    public SqlDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mDbName = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + mDbName;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
