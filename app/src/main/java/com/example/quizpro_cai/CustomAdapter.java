package com.example.quizpro_cai;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList questionId, question, opt1,opt2,opt3,opt4, ans;

    public CustomAdapter(Activity activity, Context context, ArrayList questionId, ArrayList question, ArrayList opt1, ArrayList opt2, ArrayList opt3, ArrayList opt4, ArrayList ans) {
        this.activity = activity;
        this.context = context;
        this.questionId = questionId;
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.ans = ans;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_question_recyclerview_row, parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Intent intent = new Intent(context, AdminUpdateQuestion.class);
        holder.quesId.setText(String.valueOf(questionId.get(position)));
        holder.question.setText(String.valueOf(question.get(position)));
        holder.opt1.setText(String.valueOf(opt1.get(position)));
        holder.opt2.setText(String.valueOf(opt2.get(position)));
        holder.opt3.setText(String.valueOf(opt3.get(position)));
        holder.opt4.setText(String.valueOf(opt4.get(position)));
        holder.ans.setText(String.valueOf(ans.get(position)));
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("q_id", String.valueOf(questionId.get(position)));
                intent.putExtra("q_ques", String.valueOf(question.get(position)));
                intent.putExtra("q_opt1", String.valueOf(opt1.get(position)));
                intent.putExtra("q_opt2", String.valueOf(opt2.get(position)));
                intent.putExtra("q_opt3", String.valueOf(opt3.get(position)));
                intent.putExtra("q_opt4", String.valueOf(opt4.get(position)));
                intent.putExtra("q_ans", String.valueOf(ans.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleted_id = String.valueOf(questionId.get(position));

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Question");
                builder.setMessage("Are you sure you want to delete this question?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuizDbHelper dbHelper = new QuizDbHelper(context);
                        dbHelper.deleteOneQuestion(deleted_id);
                        context.startActivity(new Intent(context, AdminNumeracy.class));
                    }
                }).show();
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(context, AdminNumeracy.class));
                    }
                }).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return questionId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quesId, question, opt1, opt2, opt3, opt4, ans;
        ImageView btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quesId = itemView.findViewById(R.id.questionIdRv);
            question = itemView.findViewById(R.id.txtQuestionRv);
            opt1 = itemView.findViewById(R.id.txtOpt1);
            opt2 = itemView.findViewById(R.id.txtOpt2);
            opt3 = itemView.findViewById(R.id.txtOpt3);
            opt4 = itemView.findViewById(R.id.txtOpt4);
            ans = itemView.findViewById(R.id.txtAns);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
