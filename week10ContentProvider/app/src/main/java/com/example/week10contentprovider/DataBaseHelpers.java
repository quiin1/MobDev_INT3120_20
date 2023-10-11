package com.example.week10contentprovider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelpers extends SQLiteOpenHelper {
    public static final String DB_NAME = "students.db";
    public static final int DB_VERSION = 1;
    public static final String STUDENTS_TABLE = "STUDENTS_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_AGE = "STUDENT_AGE";
    public static final String COLUMN_ACTIVE_STUDENT = "ACTIVE_STUDENT";

    public DataBaseHelpers(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + STUDENTS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_STUDENT_AGE + " INT, " + COLUMN_ACTIVE_STUDENT + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
