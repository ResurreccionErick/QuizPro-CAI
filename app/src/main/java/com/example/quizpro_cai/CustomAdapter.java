package com.example.quizpro_cai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList questionId, question, opt1,opt2,opt3,opt4, ans;


    public CustomAdapter(Context context, ArrayList questionId, ArrayList question, ArrayList opt1, ArrayList opt2, ArrayList opt3, ArrayList opt4, ArrayList ans) {
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.quesId.setText(String.valueOf(questionId.get(position)));
        holder.question.setText(String.valueOf(question.get(position)));
        holder.opt1.setText(String.valueOf(opt1.get(position)));
        holder.opt2.setText(String.valueOf(opt2.get(position)));
        holder.opt3.setText(String.valueOf(opt3.get(position)));
        holder.opt4.setText(String.valueOf(opt4.get(position)));
        holder.ans.setText(String.valueOf(ans.get(position)));
    }

    @Override
    public int getItemCount() {
        return questionId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quesId, question, opt1, opt2, opt3, opt4, ans;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quesId = itemView.findViewById(R.id.questionIdRv);
            question = itemView.findViewById(R.id.txtQuestionRv);
            opt1 = itemView.findViewById(R.id.txtOpt1);
            opt2 = itemView.findViewById(R.id.txtOpt2);
            opt3 = itemView.findViewById(R.id.txtOpt3);
            opt4 = itemView.findViewById(R.id.txtOpt4);
            ans = itemView.findViewById(R.id.txtAns);
        }
    }
}
