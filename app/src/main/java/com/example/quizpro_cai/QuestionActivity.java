package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuestionActivity extends AppCompatActivity {

    private TextView tvScore, tvQuestion, tvQuestionNum, tvCountdown;
    private RadioGroup rbGroup;
    private RadioButton rbOption1,rbOption2, rbOption3, rbOption4;
    private Button btnConfirm;

    private static final long COUNTDOWN_IN_MILLIS = 30000; //30 seconds

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private long backPressedTime;

    private Drawable textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter; //what number they are
    private int questionCountTotal; //total number of question
    private Question currentQuestion; //what was the current question

    private int score; //this is the score
    private boolean answered; //it will identify answered or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        tvCountdown = findViewById(R.id.tvCountdown);
        tvScore = findViewById(R.id.tvScore);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNum = findViewById(R.id.tvQuesNumber);
        rbGroup = findViewById(R.id.radio_group);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);
        btnConfirm = findViewById(R.id.btnConfirm);

        textColorDefaultRb = rbOption1.getBackground(); //get the background of the radio buttons to became default

        QuizDbHelper dbHelper= new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size(); //how many questions are there in questionList
        Collections.shuffle(questionList);

        showNextQuestion();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){ //if not answered
                        if(rbOption1.isChecked() || rbOption2.isChecked() || rbOption3.isChecked() || rbOption4.isChecked()){
                            checkAnswer();
                        }else{ //if no radiobutton selected
                            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
                        }
                }else{ //if answered, show next question
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rbOption1.setBackground(textColorDefaultRb); //it will go back to its default background if next question appeared
        rbOption2.setBackground(textColorDefaultRb);
        rbOption3.setBackground(textColorDefaultRb);
        rbOption4.setBackground(textColorDefaultRb);

        rbGroup.clearCheck(); //unselect all the radiobutton if new question appeared

        if (questionCounter < questionCountTotal) { //if theres questions left it will show next question

            currentQuestion = questionList.get(questionCounter); //get the question from questionList and get all the info by incrementing the questionCounter

            tvQuestion.setText(currentQuestion.getQuestion()); //get the question from currentQuestion variable
            rbOption1.setText(currentQuestion.getOption1());
            rbOption2.setText(currentQuestion.getOption2());
            rbOption3.setText(currentQuestion.getOption3());
            rbOption4.setText(currentQuestion.getOption4());

            questionCounter++; //every next question it will increment the questionCounter
            tvQuestionNum.setText("Question: " + questionCounter + "/" + questionCountTotal); //set the what was the number of the question in QuestionActivity UI

            answered = false; //declaring false to identify if its correct or not before moving to next question
            btnConfirm.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountdown();
        }else{
            finishQuiz(); //finish if theres no questions left
        }


    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountdownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis/1000)/60;
        int seconds = (int) (timeLeftInMillis/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        tvCountdown.setText(timeFormatted);

        if(timeLeftInMillis < 10000){ //if below 10 seconds
            tvCountdown.setBackgroundColor(Color.RED);
            tvCountdown.setTextColor(Color.WHITE);
        }else{
            tvCountdown.setBackgroundResource(R.drawable.round_background_orange);
            tvCountdown.setTextColor(Color.BLACK);
        }
    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel(); //cancel the countdown before checking the answer

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId()); //get the id of the selected radiobutton
        int answerNr = rbGroup.indexOfChild(rbSelected)+1; //turning into index

        if(answerNr == currentQuestion.getAnswerNr()){ //if its correct
            score++;
            tvScore.setText("Score: " + score);
        }

        showSolution();

    }

    private void showSolution() {
        rbOption1.setBackgroundResource(R.drawable.round_background_gray);
        rbOption2.setBackgroundResource(R.drawable.round_background_gray);
        rbOption3.setBackgroundResource(R.drawable.round_background_gray);
        rbOption4.setBackgroundResource(R.drawable.round_background_gray);

        switch (currentQuestion.getAnswerNr()){
            case 1: //identify what was the selected radiobutton that turn it into index
                rbOption1.setBackgroundResource(R.drawable.round_background_green);
                tvQuestion.setText("Correct!");
                tvQuestion.setTextSize(40);
                break;
            case 2: //identify what was the selected radiobutton that turn it into index
                rbOption2.setBackgroundResource(R.drawable.round_background_green);
                tvQuestion.setText("Correct!");
                tvQuestion.setTextSize(40);
                break;
            case 3: //identify what was the selected radiobutton that turn it into index
                rbOption3.setBackgroundResource(R.drawable.round_background_green);
                tvQuestion.setText("Correct!");
                tvQuestion.setTextSize(40);
                break;
            case 4: //identify what was the selected radiobutton that turn it into index
                rbOption4.setBackgroundResource(R.drawable.round_background_green);
                tvQuestion.setText("Correct!");
                tvQuestion.setTextSize(40);
                break;
        }

        if(questionCounter < questionCountTotal){ //if theres questions left
            btnConfirm.setText("Next");

        }else{
            btnConfirm.setText("Finish");
        }
    }

    private void finishQuiz() {

        Intent resIntent = new Intent(getApplicationContext(), QuizResult.class);
        resIntent.putExtra("questionOverall", questionCountTotal);
        resIntent.putExtra("resultScore" , score);
        startActivity(resIntent);
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){ //2 seconds
            Intent resIntent = new Intent(getApplicationContext(), QuizResult.class);
            resIntent.putExtra("questionOverall", questionCountTotal);
            resIntent.putExtra("resultScore" , score);
            startActivity(resIntent);
        }else{
            Toast.makeText(getApplicationContext(), "The quiz will be over, Press back again to finish,", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}