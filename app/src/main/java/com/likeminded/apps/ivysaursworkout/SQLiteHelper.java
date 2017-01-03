package com.likeminded.apps.ivysaursworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    public static final String COLUMN_NAME_currentWeek = "currentWeek";
    public static final String COLUMN_NAME_currentDay = "currentDay";

    private SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME_maxBench + " REAL" +  ", " +
                COLUMN_NAME_maxSquat + " REAL" +  ", " +
                COLUMN_NAME_maxDeadlift + " REAL" +  ", " +
                COLUMN_NAME_maxOverheadPress + " REAL" +  ", "  +
                COLUMN_NAME_maxBarbellRow + " REAL" + ", " +
                COLUMN_NAME_currentWeek + " TEXT" + ", " +
                COLUMN_NAME_currentDay + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser(long bench, long squat, long deadlift, long overheadPress, long barbellRow, String week, String day)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_maxBench, bench);
        contentValues.put(COLUMN_NAME_maxSquat, squat);
        contentValues.put(COLUMN_NAME_maxDeadlift, deadlift);
        contentValues.put(COLUMN_NAME_maxOverheadPress, overheadPress);
        contentValues.put(COLUMN_NAME_maxBarbellRow, barbellRow);
        contentValues.put(COLUMN_NAME_currentWeek, week);
        contentValues.put(COLUMN_NAME_currentDay, day);
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
        contentValues.put(COLUMN_NAME_currentWeek, user.getWeek());
        contentValues.put(COLUMN_NAME_currentDay, user.getDay());
        db.update(TABLE_USERS, contentValues, COLUMN_ID + " = ?", new String[]{user.getID()});
        db.close();
    }

    public void deleteRecord(UserModel user) {
        db = this.getReadableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[]{user.getID()});
        db.close();
    }

    public ArrayList<UserModel> getAllUsers() {
        db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, null, null, null, null, null);
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        UserModel userModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                userModel = new UserModel();
                userModel.setID(cursor.getString(0));
                userModel.setMaxBench(cursor.getLong(1));
                userModel.setMaxSquat(cursor.getLong(2));
                userModel.setMaxDeadlift(cursor.getLong(3));
                userModel.setMaxOverheadPress(cursor.getLong(4));
                userModel.setMaxBarbellRow(cursor.getLong(5));
                userModel.setWeek(cursor.getString(6));
                userModel.setDay(cursor.getString(7));
                users.add(userModel);
            }
        }
        cursor.close();
        db.close();
        return users;
    }

    //another way to get all users for learning purposes using rawQuery vs query
    public ArrayList<UserModel> getAllUsersAlternate() {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        ArrayList<UserModel> users = new ArrayList<UserModel>();
        UserModel userModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                userModel = new UserModel();
                userModel.setID(cursor.getString(0));
                userModel.setMaxBench(cursor.getLong(1));
                userModel.setMaxSquat(cursor.getLong(2));
                userModel.setMaxDeadlift(cursor.getLong(3));
                userModel.setMaxOverheadPress(cursor.getLong(4));
                userModel.setMaxBarbellRow(cursor.getLong(5));
                userModel.setWeek(cursor.getString(6));
                userModel.setDay(cursor.getString(7));
                users.add(userModel);
            }
        }
        cursor.close();
        db.close();
        return users;
    }
}