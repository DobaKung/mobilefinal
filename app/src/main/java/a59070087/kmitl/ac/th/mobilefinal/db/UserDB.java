package a59070087.kmitl.ac.th.mobilefinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB {
    private static SQLiteDatabase db;
    private static UserDBHelper userDBHelper;
    private static UserDB userDB;

    private UserDB(Context context) {
        userDBHelper = new UserDBHelper(context);
        db = userDBHelper.getWritableDatabase();
    }

    public static UserDB getInstance(Context context) {
        if (userDB == null) {
            userDB = new UserDB(context);
        }
        return userDB;
    }

    public long createRecord(String userId, String name, int age, String pwd) {
        ContentValues values = new ContentValues();
        values.put(userDBHelper.COL_USER_ID, userId);
        values.put(userDBHelper.COL_NAME, name);
        values.put(userDBHelper.COL_AGE, age);
        values.put(userDBHelper.COL_PWD, pwd);
        return db.insert(userDBHelper.TABLE_NAME, null, values);
    }

    public Cursor getRecords() {
        String[] cols = new String[] { userDBHelper.COL_USER_ID, userDBHelper.COL_NAME, userDBHelper.COL_AGE };
        Cursor cursor = db.query(true, userDBHelper.TABLE_NAME, cols, null, null, null, null, null, null);
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
