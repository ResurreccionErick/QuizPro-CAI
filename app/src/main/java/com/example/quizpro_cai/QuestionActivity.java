package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView timer, tvSubjectName, tvQuestion, tvQuestionNum;
    private AppCompatButton btnOption1,btnOption2, btnOption3, btnOption4;

    private AppCompatButton btnOptionNext;

    private Timer quizTimer;

    private int totalTimeInMins = 1;
    private int seconds = 0;

    private List<QuestionList> questionLists;
    private int currentQuestionPos = 0;

    private String selectedOptionByUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        btnBack = findViewById(R.id.btnQBack);
        timer = findViewById(R.id.timer);
        tvSubjectName = findViewById(R.id.tvSubjectName);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNum = findViewById(R.id.tvQuesNumber);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnOptionNext = findViewById(R.id.btnOptionNext);

        final String getSelectedSubjectName = getIntent().getStringExtra("selectedSubject");

        tvSubjectName.setText(getIntent().getStringExtra("selectedSubject"));

        questionLists = QuestionBank.getQuestion(getSelectedSubjectName); //pass to questionbank class

        startTimer(timer);

        tvQuestionNum.setText((currentQuestionPos+1) + "/" + questionLists.size()); //set the what number of the question
        tvQuestion.setText(questionLists.get(0).getQuestion()); //set the question
        btnOption1.setText(questionLists.get(0).getOption1()); //set text the answer options
        btnOption2.setText(questionLists.get(0).getOption2());
        btnOption3.setText(questionLists.get(0).getOption3());
        btnOption4.setText(questionLists.get(0).getOption4());

        buttonsConfiguration(); //this is the configuration of answer buttons

        btnBack.setOnClickListener(new View.OnClickListener() { //this is for back button in toolbar
            @Override
            public void onClick(View view) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

    }

    private void buttonsConfiguration(){
        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btnOption1.getText().toString();
                    btnOption1.setBackgroundResource(R.drawable.round_background_red);
                    btnOption1.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(currentQuestionPos).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btnOption2.getText().toString();
                    btnOption2.setBackgroundResource(R.drawable.round_background_red);
                    btnOption2.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(currentQuestionPos).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btnOption3.getText().toString();
                    btnOption3.setBackgroundResource(R.drawable.round_background_red);
                    btnOption3.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(currentQuestionPos).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        btnOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    selectedOptionByUser = btnOption4.getText().toString();
                    btnOption4.setBackgroundResource(R.drawable.round_background_red);
                    btnOption4.setTextColor(Color.WHITE);

                    revealAnswer();

                    questionLists.get(currentQuestionPos).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        btnOptionNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();

                    questionLists.get(currentQuestionPos).setUserSelectedAnswer(selectedOptionByUser);
                }else{
                    revealAnswer();
                    changeNextQuestion();
                }
            }
        });
    }

    private void changeNextQuestion() {
        currentQuestionPos++;

        playAnim(tvQuestion,0,0); //calling animation method
        playAnim(btnOption1,0,1);
        playAnim(btnOption2,0,2);
        playAnim(btnOption3,0,3);
        playAnim(btnOption4,0,4);

        if((currentQuestionPos+1)==questionLists.size()){

            btnOptionNext.setBackgroundColor(Color.GREEN);
            btnOptionNext.setText("Submit Quiz");
        }
        if(currentQuestionPos < questionLists.size()){ //when quiz is done
            selectedOptionByUser = "";

            btnOption1.setBackgroundResource(R.drawable.round_corner);
            btnOption1.setTextColor(Color.BLACK);

            btnOption2.setBackgroundResource(R.drawable.round_corner);
            btnOption2.setTextColor(Color.BLACK);

            btnOption3.setBackgroundResource(R.drawable.round_corner);
            btnOption3.setTextColor(Color.BLACK);

            btnOption4.setBackgroundResource(R.drawable.round_corner);
            btnOption4.setTextColor(Color.BLACK);


            tvQuestionNum.setText((currentQuestionPos+1) + "/" + questionLists.size()); //set the what number of the question
            tvQuestion.setText(questionLists.get(currentQuestionPos).getQuestion()); //set the question
            btnOption1.setText(questionLists.get(currentQuestionPos).getOption1()); //set text the answer options
            btnOption2.setText(questionLists.get(currentQuestionPos).getOption2());
            btnOption3.setText(questionLists.get(currentQuestionPos).getOption3());
            btnOption4.setText(questionLists.get(currentQuestionPos).getOption4());
        }else{
            Intent intent = new Intent(getApplicationContext(), QuizResult.class);
            intent.putExtra("correct", getCorrectAns());
            intent.putExtra("incorrect",getIncorrectAns());
            startActivity(intent);

            finish();
        }
    }

    private void startTimer(TextView timerText){
        quizTimer = new Timer();

        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds==0){
                    totalTimeInMins--;
                    seconds = 60;
                }else if(seconds == 0 && totalTimeInMins == 0){
                    quizTimer.purge();
                    quizTimer.cancel();

                    Toast.makeText(getApplicationContext(), "Times up!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), QuizResult.class);
                    intent.putExtra("correct", getCorrectAns());
                    intent.putExtra("incorrect", getIncorrectAns());
                    startActivity(intent);

                    finish();
                }else{
                    seconds--; //if theres time left it will decrement
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length()==1){
                            finalMinutes = "0"+finalMinutes;
                        }

                        if(finalSeconds.length() == 1){
                            finalSeconds = "0"+finalMinutes;
                        }

                        timer.setText(finalMinutes+":"+finalSeconds);

                    }
                });
            }
        },1000,1000);

    }

    private int getCorrectAns(){
        int correctAns = 0;

        for(int i = 0; i < questionLists.size(); i++){
            final String getUserSelectedAnswer = questionLists.get(i).getUserSelectedAnswer(); //get from the QuestionActivity class
            final String getAnswer = questionLists.get(i).getAnswer(); //get from the QuestionActivity class

            if(getUserSelectedAnswer.equals(getAnswer)){
                correctAns++;
            }
        }
        return correctAns;
    }

    private int getIncorrectAns(){
        int correctAns = 0;

        for(int i = 0; i < questionLists.size(); i++){
            final String getUserSelectedAnswer = questionLists.get(i).getUserSelectedAnswer(); //get from the QuestionActivity class
            final String getAnswer = questionLists.get(i).getAnswer(); //get from the QuestionActivity class

            if(!getUserSelectedAnswer.equals(getAnswer)){
                correctAns++;
            }
        }
        return correctAns;
    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = questionLists.get(currentQuestionPos).getAnswer();

        if(btnOption1.getText().toString().equals(getAnswer)){
            btnOption1.setBackgroundResource(R.drawable.round_background_green);
            btnOption1.setTextColor(Color.WHITE);
        }else if(btnOption2.getText().toString().equals(getAnswer)){
            btnOption2.setBackgroundResource(R.drawable.round_background_green);
            btnOption2.setTextColor(Color.WHITE);
        }else if(btnOption3.getText().toString().equals(getAnswer)){
            btnOption3.setBackgroundResource(R.drawable.round_background_green);
            btnOption3.setTextColor(Color.WHITE);
        }else if(btnOption4.getText().toString().equals(getAnswer)){
            btnOption4.setBackgroundResource(R.drawable.round_background_green);
            btnOption4.setTextColor(Color.WHITE);
        }
    }

    private void playAnim(View view,final int value,int viewNum) //method that plays flipping animation on the buttons
    {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(value==0){
                    switch (viewNum){
                        case 0:
                            ((TextView)view).setText(questionLists.get(currentQuestionPos).getQuestion());
                            break;
                        case 1:
                            ((TextView)view).setText(questionLists.get(currentQuestionPos).getOption1());
                            break;
                        case 2:
                            ((TextView)view).setText(questionLists.get(currentQuestionPos).getOption2());
                            break;
                        case 3:
                            ((TextView)view).setText(questionLists.get(currentQuestionPos).getOption3());
                            break;
                        case 4:
                            ((TextView)view).setText(questionLists.get(currentQuestionPos).getOption4());
                            break;
                    }

                    if(viewNum != 0)
                    ((Button)view).setBackgroundResource(R.drawable.round_bg_white); //back to its original color after next question
                    playAnim(view,1,viewNum);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

    }





}