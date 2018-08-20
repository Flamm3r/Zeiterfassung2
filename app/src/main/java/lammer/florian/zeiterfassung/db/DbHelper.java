package lammer.florian.zeiterfassung.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    private static final String _DB_FILE_NAME = "TimeTracking.db";
    private static final int _DB_Version = 1;

    public DbHelper(Context context) {
        super(context, _DB_FILE_NAME, null, _DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TimeDataTable.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
