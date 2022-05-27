package com.example.quizpro_cai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminNumeracy extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addQuesButton;
    private Dialog addQuestionDialog;

    QuizDbHelper dbHelper;
    ArrayList<String> questionId, question, opt1,opt2,opt3,opt4,ans;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_numeracy);

        addQuesButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);

        addQuestionDialog = new Dialog(AdminNumeracy.this); //this dialog is for adding question
        addQuestionDialog.setContentView(R.layout.add_question_dialog);
        addQuestionDialog.setCancelable(true);
        addQuestionDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText dialogQuestion = (EditText) addQuestionDialog.findViewById(R.id.txtAddQuestion);
        EditText dialogOpt1 = (EditText) addQuestionDialog.findViewById(R.id.txtOption1);
        EditText dialogOpt2 = (EditText) addQuestionDialog.findViewById(R.id.txtOption2);
        EditText dialogOpt3 = (EditText) addQuestionDialog.findViewById(R.id.txtOption3);
        EditText dialogOpt4 = (EditText) addQuestionDialog.findViewById(R.id.txtOption4);
        EditText dialogAns = (EditText) addQuestionDialog.findViewById(R.id.txtAnswer);
        Button btnAddQuesDialog = (Button) addQuestionDialog.findViewById(R.id.btnAddQuestion);

        addQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(getApplicationContext(), AdminAddQuestion.class));
                addQuestionDialog.show();

                btnAddQuesDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialogQuestion.getText().toString().isEmpty() || dialogOpt1.getText().toString().isEmpty() || dialogOpt2.getText().toString().isEmpty()
                                || dialogOpt3.getText().toString().isEmpty() || dialogOpt4.getText().toString().isEmpty() || dialogAns.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            QuizDbHelper quizDbHelper = new QuizDbHelper(AdminNumeracy.this);

                            quizDbHelper.addQuestion(dialogQuestion.getText().toString(), //added in QuizDbHelper addQuestion method
                                    dialogOpt1.getText().toString(),
                                    dialogOpt2.getText().toString(),
                                    dialogOpt3.getText().toString(),
                                    dialogOpt4.getText().toString(),
                                    Integer.parseInt(dialogAns.getText().toString()));

                            dialogQuestion.setText("");
                            dialogOpt1.setText("");
                            dialogOpt2.setText("");
                            dialogOpt3.setText("");
                            dialogOpt4.setText("");
                            dialogAns.setText("");

                            startActivity(new Intent(getApplicationContext(), AdminSubjects.class));

                        }
                    }
                });
        }
        });

        dbHelper = new QuizDbHelper(AdminNumeracy.this);
        questionId = new ArrayList<>();
        question = new ArrayList<>();
        opt1 = new ArrayList<>();
        opt2 = new ArrayList<>();
        opt3 = new ArrayList<>();
        opt4 = new ArrayList<>();
        ans = new ArrayList<>();

       storeDataInArrays();

        customAdapter = new CustomAdapter(AdminNumeracy.this,this, questionId, question, opt1, opt2, opt3, opt4, ans);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminNumeracy.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
            Cursor cursor = dbHelper.readAllData();
            if(cursor.getCount() == 0){
                Toast.makeText(getApplicationContext(), "There is no Question in database", Toast.LENGTH_SHORT).show();
            }else{
                while (cursor.moveToNext()){
                    questionId.add(cursor.getString(0));
                    question.add(cursor.getString(1));
                    opt1.add(cursor.getString(2));
                    opt2.add(cursor.getString(3));
                    opt3.add(cursor.getString(4));
                    opt4.add(cursor.getString(5));
                    ans.add(cursor.getString(6));
                }
            }


    }

}