package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminUpdateQuestion extends AppCompatActivity {

    private EditText txtQuestion, txtOpt1, txtOpt2, txtOpt3, txtOpt4, txtAns;
    private Button btnUpdate;


    String id, question, opt1, opt2, opt3, opt4,ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_question);

        txtQuestion = findViewById(R.id.txtUpdateQuestion);
        txtOpt1 = findViewById(R.id.txtUpdateOption1);
        txtOpt2 = findViewById(R.id.txtUpdateOption2);
        txtOpt3 = findViewById(R.id.txtUpdateOption3);
        txtOpt4 = findViewById(R.id.txtUpdateOption4);
        txtAns = findViewById(R.id.txtUpdateAnswer);
        btnUpdate = findViewById(R.id.btnUpdateQuestion);

        //first we need to get the intents
        getAndSetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get again the updated data from EditText then assign it to the variable
                question = txtQuestion.getText().toString().trim();
                opt1 = txtOpt1.getText().toString().trim();
                opt2 = txtOpt2.getText().toString().trim();
                opt3 = txtOpt3.getText().toString().trim();
                opt4 = txtOpt4.getText().toString().trim();
                ans = txtAns.getText().toString().trim();

                //then pass the updated data in updateQuestion constructor in QuizDBHelper
                QuizDbHelper dbHelper = new QuizDbHelper(AdminUpdateQuestion.this);
                dbHelper.updateQuestion(id, question, opt1,opt2,opt3,opt4,ans);

                finish();
            }
        });


    }

     void getAndSetIntentData() {
        if(getIntent().hasExtra("q_id") && getIntent().hasExtra("q_ques") && getIntent().hasExtra("q_opt1") && getIntent().hasExtra("q_opt2") &&
                getIntent().hasExtra("q_opt3") && getIntent().hasExtra("q_opt4") && getIntent().hasExtra("q_ans")){

            //getting data from intent from CustomeAdapter
            id = getIntent().getStringExtra("q_id");
            question = getIntent().getStringExtra("q_ques");
            opt1 = getIntent().getStringExtra("q_opt1");
            opt2 = getIntent().getStringExtra("q_opt2");
            opt3 = getIntent().getStringExtra("q_opt3");
            opt4 = getIntent().getStringExtra("q_opt4");
            ans = getIntent().getStringExtra("q_ans");

            //setting up the intent
            txtQuestion.setText(question);
            txtOpt1.setText(opt1);
            txtOpt2.setText(opt2);
            txtOpt3.setText(opt3);
            txtOpt4.setText(opt4);
            txtAns.setText(ans);

        }else{
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
        }
    }


}