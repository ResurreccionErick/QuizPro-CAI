package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout btnRead, btnVideos, btnSounds, btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnLearnByReading);
        btnSounds = findViewById(R.id.btnLearnBySounds);
        btnVideos = findViewById(R.id.btnLearnByVideos);
        btnQuiz = findViewById(R.id.btnTakeAQuiz);


        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainQuestionActivity.class));
            }
        });
    }


}