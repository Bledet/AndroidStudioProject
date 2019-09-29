package com.example.topquiz.model;

import java.util.List;

public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.setmQuestion(question);
        this.setmChoiceList(choiceList);
        this.setmAnswerIndex(answerIndex);
    }

    /* Accesseur */

    //mQuestion
    public String getmQuestion() {
        return mQuestion;
    }
    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    //mChoiceList
    public List<String> getmChoiceList() {
        return mChoiceList;
    }
    public void setmChoiceList(List<String> mChoiceList) {
        this.mChoiceList = mChoiceList;
    }

    //mAnswerIndex
    public int getmAnswerIndex() {
        return mAnswerIndex;
    }
    public void setmAnswerIndex(int mAnswerIndex) {
        this.mAnswerIndex = mAnswerIndex;
    }


}
