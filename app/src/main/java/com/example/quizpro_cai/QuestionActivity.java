package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView timer, tvSubjectName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnBack = findViewById(R.id.btnQBack);
        timer = findViewById(R.id.timer);
        tvSubjectName = findViewById(R.id.tvSubjectName);

        tvSubjectName.setText(getIntent().getStringExtra("selectedSubject"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
}