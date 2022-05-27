package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAddQuestion extends AppCompatActivity {

    private EditText txtQues, txtOption1, txtOption2, txtOption3, txtOption4, txtAns;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_question);

        btnAdd = findViewById(R.id.btnAddQuestion);
        txtQues = findViewById(R.id.txtQuestion);
        txtOption1 = findViewById(R.id.txtOption1);
        txtOption2 = findViewById(R.id.txtOption2);
        txtOption3 = findViewById(R.id.txtOption3);
        txtOption4 = findViewById(R.id.txtOption4);
        txtAns = findViewById(R.id.txtAnswer);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtQues.getText().toString().isEmpty()||txtOption1.getText().toString().isEmpty()||txtOption2.getText().toString().isEmpty()
                        ||txtOption3.getText().toString().isEmpty()||txtOption4.getText().toString().isEmpty()||txtAns.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                }else{
                    QuizDbHelper quizDbHelper = new QuizDbHelper(AdminAddQuestion.this);

                    quizDbHelper.addQuestion(txtQues.getText().toString(), //added in QuizDbHelper addQuestion method
                            txtOption1.getText().toString(),
                            txtOption2.getText().toString(),
                            txtOption3.getText().toString(),
                            txtOption4.getText().toString(),
                            //txtAns.getText().toString());
                            Integer.parseInt(txtAns.getText().toString()));

                    txtQues.setText("");
                    txtOption1.setText("");
                    txtOption2.setText("");
                    txtOption3.setText("");
                    txtOption4.setText("");
                    txtAns.setText("");

                    startActivity(new Intent(getApplicationContext(), AdminSubjects.class));


                }
            }




        });


    }
}