package a59070087.kmitl.ac.th.mobilefinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDB {
    public static final String TAG = "USERDB";
    private static SQLiteDatabase db;
    private static UserDBHelper userDBHelper;
    private static UserDB userDB;

    private UserDB(Context context) {
        userDBHelper = new UserDBHelper(context);
        db = userDBHelper.getWritableDatabase();
    }

    public static UserDB getInstance(Context context) {
        Log.d(TAG, "getInstance");
        if (userDB == null) {
            Log.d(TAG, "getInstance: userDB is null, creating...");
            userDB = new UserDB(context);
        }
        return userDB;
    }

    public long createRecord(String userId, String name, int age, String pwd) {
        Log.d(TAG, "createRecord");
        ContentValues values = new ContentValues();
        values.put(userDBHelper.COL_USER_ID, userId);
        values.put(userDBHelper.COL_NAME, name);
        values.put(userDBHelper.COL_AGE, age);
        values.put(userDBHelper.COL_PWD, pwd);
        return db.insert(userDBHelper.TABLE_NAME, null, values);
    }

    public Cursor getRecords() {
        Log.d(TAG, "getRecords");
        String[] cols = new String[] { userDBHelper.COL_USER_ID, userDBHelper.COL_NAME, userDBHelper.COL_AGE };
        Cursor cursor = db.query(true, userDBHelper.TABLE_NAME, cols, null, null, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    public Cursor getRecord(String userId, String pwd) {
        Log.d(TAG, "getRecord: " + userId + " " + pwd);
        final String[] COLS = new String[] { userDBHelper.COL_USER_ID, userDBHelper.COL_NAME };
        final String WHERE = userDBHelper.COL_USER_ID + "=? AND " + userDBHelper.COL_PWD + "=?";
        final String[] ARGS = new String[] { userId, pwd };
        Cursor cursor = db.query(true, userDBHelper.TABLE_NAME, COLS, WHERE, ARGS, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }

    /* public long updateRecord(int rowId, String date, String sleepStart, String sleepEnd) {
        ContentValues values = new ContentValues();
        values.put(sleepDBHelper.COL_DATE, date);
        values.put(sleepDBHelper.COL_SLEEP_START, sleepStart);
        values.put(sleepDBHelper.COL_SLEEP_END, sleepEnd);
        String whereClause = sleepDBHelper.COL_ID + "=" + rowId;
        return db.update(sleepDBHelper.TABLE_NAME, values, whereClause, null);
    } */
}
