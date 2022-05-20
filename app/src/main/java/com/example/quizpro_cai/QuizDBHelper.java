package com.example.quizpro_cai;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizpro_cai.QuizContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizPro.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE =
                "CREATE TABLE " + QuestionsTable.NUMERACY_TABLE_NAME +" ( " +
                        QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                        QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                        QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                        QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                        QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                        QuestionsTable.COLUMN_ANSWER_NR + " INTEGER " + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE); //Run the query
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ QuestionsTable.NUMERACY_TABLE_NAME); //this is use if updating db..drop the current db and create a new one
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question(" 1+1= ?" , "1","2","3","4",2);
        addQuestion(q1);
        Question q2 = new Question(" 2+2= ?" , "4","2","3","4",1);
        addQuestion(q2);
        Question q3 = new Question(" 3+3= ?" , "1","2","6","4",3);
        addQuestion(q3);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.NUMERACY_TABLE_NAME, null, cv);
    }


    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.NUMERACY_TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}