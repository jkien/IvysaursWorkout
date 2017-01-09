package com.likeminded.apps.ivysaursworkout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Workout.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String TABLE_USERS = "USERS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_maxBench = "maxBench";
    public static final String COLUMN_maxSquat = "maxSquat";
    public static final String COLUMN_maxDeadlift = "maxDeadlift";
    public static final String COLUMN_maxOverheadPress = "maxOverheadPress";
    public static final String COLUMN_maxBarbellRow = "maxBarbellRow";
    public static final String COLUMN_currentWeek = "currentWeek";
    public static final String COLUMN_currentDay = "currentDay";

    private SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME + " REAL" +  ", " +
                COLUMN_maxBench + " REAL" +  ", " +
                COLUMN_maxSquat + " REAL" +  ", " +
                COLUMN_maxDeadlift + " REAL" +  ", " +
                COLUMN_maxOverheadPress + " REAL" +  ", "  +
                COLUMN_maxBarbellRow + " REAL" + ", " +
                COLUMN_currentWeek + " TEXT" + ", " +
                COLUMN_currentDay + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser(String username, long bench, long squat, long deadlift, long overheadPress, long barbellRow, String week, String day)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_maxBench, bench);
        contentValues.put(COLUMN_maxSquat, squat);
        contentValues.put(COLUMN_maxDeadlift, deadlift);
        contentValues.put(COLUMN_maxOverheadPress, overheadPress);
        contentValues.put(COLUMN_maxBarbellRow, barbellRow);
        contentValues.put(COLUMN_currentWeek, week);
        contentValues.put(COLUMN_currentDay, day);
        long result = db.insert(TABLE_USERS, null, contentValues);

        db.close();

        if(result == -1)
            return false;

        return true;
    }

    public void updateUser(UserModel user) {
        db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, user.getUsername());
        contentValues.put(COLUMN_maxBench, user.getMaxBench());
        contentValues.put(COLUMN_maxSquat, user.getMaxSquat());
        contentValues.put(COLUMN_maxDeadlift, user.getMaxDeadlift());
        contentValues.put(COLUMN_maxOverheadPress, user.getMaxOverheadPress());
        contentValues.put(COLUMN_maxBarbellRow, user.getMaxBarbellRow());
        contentValues.put(COLUMN_currentWeek, user.getWeek());
        contentValues.put(COLUMN_currentDay, user.getDay());
        db.update(TABLE_USERS, contentValues, COLUMN_ID + " = ?", new String[]{user.getID()});
        db.close();
    }

    public void deleteRecord(UserModel user) {
        db = this.getReadableDatabase();
        db.delete(TABLE_USERS, COLUMN_ID + " = ?", new String[]{user.getID()});
        db.close();
    }

    public UserModel getUserByUsername(String username) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username = ?", new String[] { username });
        UserModel userModel = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            userModel = new UserModel();
            userModel.setID(cursor.getString(0));
            userModel.setUsername(cursor.getString(1));
            userModel.setMaxBench(cursor.getLong(2));
            userModel.setMaxSquat(cursor.getLong(3));
            userModel.setMaxDeadlift(cursor.getLong(4));
            userModel.setMaxOverheadPress(cursor.getLong(5));
            userModel.setMaxBarbellRow(cursor.getLong(6));
            userModel.setWeek(cursor.getString(7));
            userModel.setDay(cursor.getString(8));
        }

        cursor.close();
        db.close();
        return userModel;
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
                userModel.setUsername(cursor.getString(1));
                userModel.setMaxBench(cursor.getLong(2));
                userModel.setMaxSquat(cursor.getLong(3));
                userModel.setMaxDeadlift(cursor.getLong(4));
                userModel.setMaxOverheadPress(cursor.getLong(5));
                userModel.setMaxBarbellRow(cursor.getLong(6));
                userModel.setWeek(cursor.getString(7));
                userModel.setDay(cursor.getString(8));
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
                userModel.setUsername(cursor.getString(1));
                userModel.setMaxBench(cursor.getLong(2));
                userModel.setMaxSquat(cursor.getLong(3));
                userModel.setMaxDeadlift(cursor.getLong(4));
                userModel.setMaxOverheadPress(cursor.getLong(5));
                userModel.setMaxBarbellRow(cursor.getLong(6));
                userModel.setWeek(cursor.getString(7));
                userModel.setDay(cursor.getString(8));
                users.add(userModel);
            }
        }
        cursor.close();
        db.close();
        return users;
    }
}