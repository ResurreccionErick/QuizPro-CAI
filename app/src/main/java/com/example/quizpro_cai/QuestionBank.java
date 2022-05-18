package com.example.quizpro_cai;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    final static List<QuestionList> numeracyQuestion(){

        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("1+1 = ?","1","2","3","4","2", "");
        final QuestionList question2 = new QuestionList("2+2 = ?","1","2","3","4","2", "");
        final QuestionList question3 = new QuestionList("3+3 = ?","1","2","3","4","2", "");
        final QuestionList question4 = new QuestionList("4+4 = ?","1","2","3","4","2", "");
        final QuestionList question5 = new QuestionList("5+5 = ?","1","2","3","4","2", "");

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);

        return questionLists;

    }

    final static List<QuestionList> filipinoQuestion(){

        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("1+1 = ?","1","2","3","4","2", "");
        final QuestionList question2 = new QuestionList("2+2 = ?","1","2","3","4","2", "");
        final QuestionList question3 = new QuestionList("3+3 = ?","1","2","3","4","2", "");
        final QuestionList question4 = new QuestionList("4+4 = ?","1","2","3","4","2", "");
        final QuestionList question5 = new QuestionList("5+5 = ?","1","2","3","4","2", "");

        questionLists.add(question1);
        questionLists.add(question2);
        questionLists.add(question3);
        questionLists.add(question4);
        questionLists.add(question5);

        return questionLists;

    }

    public static List<QuestionList> getQuestion(String selectedSubject){
        switch (selectedSubject){
            case "Numeracy Quiz":
                return numeracyQuestion();
            case "Filipino Quiz":
                return filipinoQuestion();
            default:
                return null;
        }
    }
}
