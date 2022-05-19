package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private TextView timer, tvQuestion, tvQuestionNum;
    private RadioButton rbOption1,rbOption2, rbOption3, rbOption4, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        timer = findViewById(R.id.timer);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNum = findViewById(R.id.tvQuesNumber);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnConfirm = findViewById(R.id.btnConfirm);




    }


}