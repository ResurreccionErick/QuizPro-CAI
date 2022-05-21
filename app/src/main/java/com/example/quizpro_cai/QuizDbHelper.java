package com.example.quizpro_cai;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizpro_cai.QuizContract.*;


import java.util.ArrayList;
import java.util.List;


    public class QuizDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "QuizPro.db";
        private static final int DATABASE_VERSION = 1;

        private SQLiteDatabase db;

        public QuizDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            this.db = db;

            final String SQL_CREATE_NUMERACY_QUESTIONS_TABLE = "CREATE TABLE " +
                    numeracyTable.NUMERACY_TABLE_NAME + " ( " +
                    numeracyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    numeracyTable.COLUMN_QUESTION + " TEXT, " +
                    numeracyTable.COLUMN_OPTION1 + " TEXT, " +
                    numeracyTable.COLUMN_OPTION2 + " TEXT, " +
                    numeracyTable.COLUMN_OPTION3 + " TEXT, " +
                    numeracyTable.COLUMN_OPTION4 + " TEXT, " +
                    numeracyTable.COLUMN_ANSWER_NR + " INTEGER" +
                    ")";

            final String SQL_CREATE_LANGUAGE_QUESTIONS_TABLE = "CREATE TABLE " +
                    languageLiteracyTable.LANGUAGE_TABLE_NAME + " ( " +
                    languageLiteracyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    languageLiteracyTable.COLUMN_QUESTION + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION1 + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION2 + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION3 + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION4 + " TEXT, " +
                    languageLiteracyTable.COLUMN_ANSWER_NR + " INTEGER" +
                    ")";

            final String SQL_CREATE_FILIPINO_QUESTIONS_TABLE = "CREATE TABLE " +
                    filipinoLiteracyTable.FILIPINO_TABLE_NAME + " ( " +
                    languageLiteracyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    languageLiteracyTable.COLUMN_QUESTION + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION1 + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION2 + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION3 + " TEXT, " +
                    languageLiteracyTable.COLUMN_OPTION4 + " TEXT, " +
                    languageLiteracyTable.COLUMN_ANSWER_NR + " INTEGER" +
                    ")";

            final String SQL_CREATE_READING_QUESTIONS_TABLE = "CREATE TABLE " +
                    readingLiteracyTable.READING_TABLE_NAME + " ( " +
                    readingLiteracyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    readingLiteracyTable.COLUMN_QUESTION + " TEXT, " +
                    readingLiteracyTable.COLUMN_OPTION1 + " TEXT, " +
                    readingLiteracyTable.COLUMN_OPTION2 + " TEXT, " +
                    readingLiteracyTable.COLUMN_OPTION3 + " TEXT, " +
                    readingLiteracyTable.COLUMN_OPTION4 + " TEXT, " +
                    readingLiteracyTable.COLUMN_ANSWER_NR + " INTEGER" +
                    ")";

            db.execSQL(SQL_CREATE_NUMERACY_QUESTIONS_TABLE);
            db.execSQL(SQL_CREATE_LANGUAGE_QUESTIONS_TABLE);
            db.execSQL(SQL_CREATE_FILIPINO_QUESTIONS_TABLE);
            db.execSQL(SQL_CREATE_READING_QUESTIONS_TABLE);
            fill_questions_LanguageTable();
            fill_questions_NumeracyTable();
            fill_questions_FilipinoTable();
            fill_questions_ReadingTable();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + numeracyTable.NUMERACY_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + languageLiteracyTable.LANGUAGE_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + filipinoLiteracyTable.FILIPINO_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + readingLiteracyTable.READING_TABLE_NAME);
            onCreate(db);
        }

        private void fill_questions_NumeracyTable() {
            Question q1 = new Question("1+1 = ?", "1", "2", "3", "4", 2);
            addNumeracyQuestion(q1);
            Question q2 = new Question("2+2 = ?", "1", "2", "3", "3", 4);
            addNumeracyQuestion(q2);
            Question q3 = new Question("3+3 = ?", "2", "4", "6", "8", 3);
            addNumeracyQuestion(q3);
            Question q4 = new Question("4+4 = ?", "8", "9", "0", "D", 1);
            addNumeracyQuestion(q4);
            Question q5 = new Question("5+5 = ?", "10", "20", "30", "40", 1);
            addNumeracyQuestion(q5);
        }

        private void fill_questions_LanguageTable() {
            Question q1 = new Question("A is correct", "A", "B", "C", "D", 1);
            addLanguageQuestion(q1);
            Question q2 = new Question("B is correct", "A", "B", "C", "D", 2);
            addLanguageQuestion(q2);
            Question q3 = new Question("C is correct", "A", "B", "C", "D", 3);
            addLanguageQuestion(q3);
            Question q4 = new Question("A is correct again", "A", "B", "C", "D", 1);
            addLanguageQuestion(q4);
            Question q5 = new Question("B is correct again", "A", "B", "C", "D", 2);
            addLanguageQuestion(q5);
        }

        private void fill_questions_FilipinoTable() {
            Question q1 = new Question("_lepante", "E", "A", "B", "D", 1);
            addFilipinoQuestion(q1);
        }

        private void fill_questions_ReadingTable() {
            Question q1 = new Question("_PPLE", "A", "B", "C", "D", 1);
            addReadingQuestion(q1);
        }

        private void addNumeracyQuestion(Question question) {
            ContentValues cv = new ContentValues();
            cv.put(numeracyTable.COLUMN_QUESTION, question.getQuestion());
            cv.put(numeracyTable.COLUMN_OPTION1, question.getOption1());
            cv.put(numeracyTable.COLUMN_OPTION2, question.getOption2());
            cv.put(numeracyTable.COLUMN_OPTION3, question.getOption3());
            cv.put(numeracyTable.COLUMN_OPTION4, question.getOption4());
            cv.put(numeracyTable.COLUMN_ANSWER_NR, question.getAnswerNr());
            db.insert(numeracyTable.NUMERACY_TABLE_NAME, null, cv);
        }

        private void addLanguageQuestion(Question question) {
            ContentValues cv = new ContentValues();
            cv.put(languageLiteracyTable.COLUMN_QUESTION, question.getQuestion());
            cv.put(languageLiteracyTable.COLUMN_OPTION1, question.getOption1());
            cv.put(languageLiteracyTable.COLUMN_OPTION2, question.getOption2());
            cv.put(languageLiteracyTable.COLUMN_OPTION3, question.getOption3());
            cv.put(languageLiteracyTable.COLUMN_OPTION4, question.getOption4());
            cv.put(languageLiteracyTable.COLUMN_ANSWER_NR, question.getAnswerNr());
            db.insert(languageLiteracyTable.LANGUAGE_TABLE_NAME, null, cv);
        }

        private void addFilipinoQuestion(Question question) {
            ContentValues cv = new ContentValues();
            cv.put(filipinoLiteracyTable.COLUMN_QUESTION, question.getQuestion());
            cv.put(filipinoLiteracyTable.COLUMN_OPTION1, question.getOption1());
            cv.put(filipinoLiteracyTable.COLUMN_OPTION2, question.getOption2());
            cv.put(filipinoLiteracyTable.COLUMN_OPTION3, question.getOption3());
            cv.put(filipinoLiteracyTable.COLUMN_OPTION4, question.getOption4());
            cv.put(filipinoLiteracyTable.COLUMN_ANSWER_NR, question.getAnswerNr());
            db.insert(filipinoLiteracyTable.FILIPINO_TABLE_NAME, null, cv);
        }

        private void addReadingQuestion(Question question) {
            ContentValues cv = new ContentValues();
            cv.put(readingLiteracyTable.COLUMN_QUESTION, question.getQuestion());
            cv.put(readingLiteracyTable.COLUMN_OPTION1, question.getOption1());
            cv.put(readingLiteracyTable.COLUMN_OPTION2, question.getOption2());
            cv.put(readingLiteracyTable.COLUMN_OPTION3, question.getOption3());
            cv.put(readingLiteracyTable.COLUMN_OPTION4, question.getOption4());
            cv.put(readingLiteracyTable.COLUMN_ANSWER_NR, question.getAnswerNr());
            db.insert(readingLiteracyTable.READING_TABLE_NAME, null, cv);
        }

        @SuppressLint("Range")
        public List<Question> getAllNumeracyQuestions() {
            List<Question> numeracyQuestionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + numeracyTable.NUMERACY_TABLE_NAME, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(numeracyTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(numeracyTable.COLUMN_ANSWER_NR)));
                    numeracyQuestionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return numeracyQuestionList;
        }

        @SuppressLint("Range")
        public List<Question> getAllLanguageQuestions() {
            List<Question> languageQuestionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + languageLiteracyTable.LANGUAGE_TABLE_NAME, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(numeracyTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(numeracyTable.COLUMN_ANSWER_NR)));
                    languageQuestionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return languageQuestionList;
        }

        @SuppressLint("Range")
        public List<Question> getAllFilipinoQuestions() {
            List<Question> filipinoQuestionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + filipinoLiteracyTable.FILIPINO_TABLE_NAME, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(numeracyTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(numeracyTable.COLUMN_ANSWER_NR)));
                    filipinoQuestionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return filipinoQuestionList;
        }

        @SuppressLint("Range")
        public List<Question> getAllReadingQuestions() {
            List<Question> filipinoQuestionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + readingLiteracyTable.READING_TABLE_NAME, null);

            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(numeracyTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(numeracyTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(numeracyTable.COLUMN_ANSWER_NR)));
                    filipinoQuestionList.add(question);
                } while (c.moveToNext());
            }

            c.close();
            return filipinoQuestionList;
        }
    }

