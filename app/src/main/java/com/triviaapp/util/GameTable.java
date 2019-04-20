package com.triviaapp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.triviaapp.model.Game;

import java.util.ArrayList;
import java.util.Date;

public class GameTable {
    private static AppDBHelper mAppDBHelper = null;



    private static GameTable instance = null;

    public static GameTable getInstance(Context context) {
        if (instance == null) {
            instance = new GameTable();
            mAppDBHelper = new AppDBHelper(context);
        }
        return instance;
    }



    private static final String TEXT_TYPE = " TEXT";
    public static final String TYPE_TIMESTAMP_FORMAT = " DATETIME DEFAULT CURRENT_TIMESTAMP";
    private static final String COMMA_SEP = ",";
    private static final String TABLE_NAME = "GameInfo";
    private static final String COULMN_ID = "Id";
    private static final String COULMN_NAME = "name";
    private static final String COULMN_ANSWER1 = "answer1";
    private static final String COULMN_ANSWER2 = "answer2";
    private static final String COULMN_DATE= "date";



    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+TABLE_NAME+"("+COULMN_ID+" integer primary key autoincrement"+COMMA_SEP
                    +COULMN_NAME+TEXT_TYPE+COMMA_SEP
                    +COULMN_ANSWER1+TEXT_TYPE+COMMA_SEP
                    +COULMN_ANSWER2+TEXT_TYPE+COMMA_SEP
                    +COULMN_DATE+TYPE_TIMESTAMP_FORMAT
                    + ");";



    public void addData(Game game){

        SQLiteDatabase db = mAppDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COULMN_NAME,game.getName());
        values.put(COULMN_ANSWER1,game.getAnswer1());
        values.put(COULMN_ANSWER2,game.getAnswer2());
        values.put(COULMN_DATE,game.getDate().getTime());

        db.insert(TABLE_NAME, null, values);

    }


    public ArrayList<Game> getGameHistoryList(){
        ArrayList<Game> arrayList = new ArrayList <>();

        SQLiteDatabase db = mAppDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " ORDER BY "+COULMN_DATE+" DESC", null);

        if (cursor.moveToFirst()) {
            do {
               Game game = new Game();
               game.setName(cursor.getString(1));
               game.setAnswer1(cursor.getString(2));
               game.setAnswer2(cursor.getString(3));
               Date date = new Date();
               date.setTime(cursor.getLong(4));
               game.setDate(date);

               arrayList.add(game);

            } while (cursor.moveToNext());
        }
        cursor.close();


        return arrayList;
    }







}
