package com.likeminded.apps.ivysaursworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Workout.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_USERS = "USERS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME_maxBench = "maxBench";
    public static final String COLUMN_NAME_maxSquat = "maxSquat";
    public static final String COLUMN_NAME_maxDeadlift = "maxDeadlift";
    public static final String COLUMN_NAME_maxOverheadPress = "maxOverheadPress";
    public static final String COLUMN_NAME_maxBarbellRow = "maxBarbellRow";

    private SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME_maxBench + " INTEGER" +  ", "
                + COLUMN_NAME_maxSquat + " INTEGER" +  ", "
                + COLUMN_NAME_maxDeadlift + " INTEGER" +  ", "
                + COLUMN_NAME_maxOverheadPress + " INTEGER" +  ", "
                + COLUMN_NAME_maxBarbellRow + " INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser(int bench, int squat, int deadlift, int overheadPress, int barbellRow)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_maxBench, bench);
        contentValues.put(COLUMN_NAME_maxSquat, squat);
        contentValues.put(COLUMN_NAME_maxDeadlift, deadlift);
        contentValues.put(COLUMN_NAME_maxOverheadPress, overheadPress);
        contentValues.put(COLUMN_NAME_maxBarbellRow, barbellRow);
        long result = db.insert(TABLE_USERS, null, contentValues);

        db.close();

        if(result == -1)
            return false;

        return true;
    }

    public void updateUser(UserModel user) {
        db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_maxBench, user.getMaxBench());
        contentValues.put(COLUMN_NAME_maxSquat, user.getMaxSquat());
        contentValues.put(COLUMN_NAME_maxDeadlift, user.getMaxDeadlift());
        contentValues.put(COLUMN_NAME_maxOverheadPress, user.getMaxOverheadPress());
        contentValues.put(COLUMN_NAME_maxBarbellRow, user.getMaxBarbellRow());
        db.update(TABLE_USERS, contentValues, COLUMN_ID + " = ?", new String[]{user.getID()});
        db.close();
    }

    public void deleteRecord(UserModel user) {
        db = this.getReadableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[]{user.getID()});
        db.close();
    }
}