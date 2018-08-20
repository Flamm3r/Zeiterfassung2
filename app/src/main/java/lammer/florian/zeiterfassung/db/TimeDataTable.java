package lammer.florian.zeiterfassung.db;

import android.database.sqlite.SQLiteDatabase;

public class TimeDataTable {

    private static final String _CREATE = "CREATE TABLE [time_data]" +
            "([_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "[start_time] TEXT NOT NULL," +
            "[end_time] TEXT)";

    public static void createTable(SQLiteDatabase db){
        db.execSQL(_CREATE);
    }

}
