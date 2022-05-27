package com.example.quizpro_cai;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

//import com.example.quizpro_cai.QuizContract.*;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class QuizDbHelper extends SQLiteOpenHelper {
    Context context;

        private static final String DATABASE_NAME = "QuizPro-CAI.db";
        private static final int DATABASE_VERSION = 1;

        public static final String NUMERACY_TABLE_NAME = "numeracy_questions";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER = "answer";

        private SQLiteDatabase db;

        QuizDbHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            this.db = db;

            String SQL_CREATE_NUMERACY_QUESTIONS_TABLE = "CREATE TABLE " + NUMERACY_TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_QUESTION + " TEXT, " +
                    COLUMN_OPTION1 + " TEXT, " +
                    COLUMN_OPTION2 + " TEXT, " +
                    COLUMN_OPTION3 + " TEXT, " +
                    COLUMN_OPTION4 + " TEXT, " +
                    COLUMN_ANSWER + " INTEGER);";
            db.execSQL(SQL_CREATE_NUMERACY_QUESTIONS_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NUMERACY_TABLE_NAME);
            onCreate(db);
        }



        void addQuestion(String question, String option1, String option2, String option3, String option4, int answer){
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_QUESTION, question);
            cv.put(COLUMN_OPTION1, option1);
            cv.put(COLUMN_OPTION2, option2);
            cv.put(COLUMN_OPTION3, option3);
            cv.put(COLUMN_OPTION4, option4);
            cv.put(COLUMN_ANSWER, answer);
            long result = db.insert(NUMERACY_TABLE_NAME,null,cv);

            if(result == -1){ //if failed
                Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
            }
        }

        Cursor readAllData(){
            String query = "SELECT * FROM " +NUMERACY_TABLE_NAME;
            db = getReadableDatabase();
            Cursor cursor = null;

            if(db != null){
                cursor = db.rawQuery(query,null);
            }
            return cursor;
        }

    public void updateQuestion(String row_id, String question, String option1, String option2, String option3, String option4, String answer){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, question);
        cv.put(COLUMN_OPTION1, option1);
        cv.put(COLUMN_OPTION2, option2);
        cv.put(COLUMN_OPTION3, option3);
        cv.put(COLUMN_OPTION4, option4);
        cv.put(COLUMN_ANSWER, answer);
        long result = db.update(NUMERACY_TABLE_NAME,cv,COLUMN_ID+"=?" , new String[]{row_id});

        if(result == -1){ //if failed
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Question was updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneQuestion(String row_id){
            db = this.getWritableDatabase();
            long result = db.delete(NUMERACY_TABLE_NAME, COLUMN_ID+"=?", new String[]{row_id});

        if(result == -1){ //if failed
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Question was Deleted", Toast.LENGTH_SHORT).show();
        }
    }



        @SuppressLint("Range")
        public List<Question> getAllNumeracyQuestions() {
            List<Question> numeracyQuestionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + NUMERACY_TABLE_NAME, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(COLUMN_ANSWER)));
                    numeracyQuestionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return numeracyQuestionList;
        }


    }

