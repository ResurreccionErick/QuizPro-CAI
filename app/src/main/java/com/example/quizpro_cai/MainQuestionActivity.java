package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainQuestionActivity extends AppCompatActivity {

    LinearLayout btnLanguageLiteracy, btnNumbering, btnReading, btnFilipino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);

        btnNumbering = findViewById(R.id.qMainNumeracy);

        btnLanguageLiteracy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("selectedSubject", "Language Literacy Quiz");
                startActivity(intent);
            }
        });

        btnReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("selectedSubject", "Reading Quiz");
                startActivity(intent);
            }
        });

        btnNumbering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("selectedSubject", "Numeracy Quiz");
                startActivity(intent);
            }
        });

        btnFilipino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                intent.putExtra("selectedSubject", "Filipino Quiz");
                startActivity(intent);
            }
        });





    }
}