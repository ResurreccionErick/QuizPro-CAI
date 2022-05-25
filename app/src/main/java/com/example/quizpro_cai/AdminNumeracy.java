package com.example.quizpro_cai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminNumeracy extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addQuesButton;

    QuizDbHelper dbHelper;
    ArrayList<String> questionId, question, opt1,opt2,opt3,opt4,ans;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_numeracy);

        addQuesButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);

        addQuesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminAddQuestion.class));
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

        customAdapter = new CustomAdapter(AdminNumeracy.this, questionId, question, opt1, opt2, opt3, opt4, ans);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminNumeracy.this));

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