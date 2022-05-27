package com.example.quizpro_cai;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
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
    private Dialog updateQuestionDialog;

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
        updateQuestionDialog = new Dialog(context); //this dialog is for adding question
        updateQuestionDialog.setContentView(R.layout.update_question_dialog);
        updateQuestionDialog.setCancelable(true);
        updateQuestionDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText dialogQuestion = (EditText) updateQuestionDialog.findViewById(R.id.txtUpdateQuestion);
        EditText dialogOpt1 = (EditText) updateQuestionDialog.findViewById(R.id.txtUpdateOption1);
        EditText dialogOpt2 = (EditText) updateQuestionDialog.findViewById(R.id.txtUpdateOption2);
        EditText dialogOpt3 = (EditText) updateQuestionDialog.findViewById(R.id.txtUpdateOption3);
        EditText dialogOpt4 = (EditText) updateQuestionDialog.findViewById(R.id.txtUpdateOption4);
        EditText dialogAns = (EditText) updateQuestionDialog.findViewById(R.id.txtUpdateAnswer);
        Button btnUpdateQuesDialog = (Button) updateQuestionDialog.findViewById(R.id.btnUpdateQuestion);

        //Intent intent = new Intent(context, AdminUpdateQuestion.class);
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
                updateQuestionDialog.show();

                dialogQuestion.setText(String.valueOf(question.get(position))); //set text the info to pick where to update
                dialogOpt1.setText(String.valueOf(opt1.get(position)));
                dialogOpt2.setText(String.valueOf(opt3.get(position)));
                dialogOpt3.setText(String.valueOf(opt3.get(position)));
                dialogOpt4.setText(String.valueOf(opt4.get(position)));
                dialogAns.setText(String.valueOf(ans.get(position)));

                btnUpdateQuesDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = String.valueOf(questionId.get(position));
                        String question = dialogQuestion.getText().toString().trim();
                        String opt1 = dialogOpt1.getText().toString().trim();
                        String opt2 = dialogOpt2.getText().toString().trim();
                        String opt3 = dialogOpt3.getText().toString().trim();
                        String opt4 = dialogOpt4.getText().toString().trim();
                        int ans = Integer.parseInt(dialogAns.getText().toString().trim());

                        //then pass the updated data in updateQuestion constructor in QuizDBHelper
                        QuizDbHelper dbHelper = new QuizDbHelper(context);
                        dbHelper.updateQuestion(id, question, opt1,opt2,opt3,opt4,ans);

                        context.startActivity(new Intent(context, AdminNumeracy.class));
                    }
                });


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
