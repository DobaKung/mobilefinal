package a59070087.kmitl.ac.th.mobilefinal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mobilefinal.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String COL_ID = "_id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    public static final String COL_PWD = "password";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_USER_ID + " TEXT NOT NULL, " +
            COL_NAME + " TEXT NOT NULL, " +
            COL_AGE + " INTEGER NOT NULL, " +
            COL_PWD + " TEXT NOT NULL);";

    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
