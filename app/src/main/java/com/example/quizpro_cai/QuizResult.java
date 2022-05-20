package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {

    private TextView tvCorrect;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        tvCorrect = findViewById(R.id.tvCorrect);

        btnDone = findViewById(R.id.btnDone);

        final int getCorrectAns = getIntent().getIntExtra("resultScore",0);
        final int getTotalNoOfQues = getIntent().getIntExtra("questionOverall",0);

        tvCorrect.setText(getCorrectAns + "/" + getTotalNoOfQues);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}