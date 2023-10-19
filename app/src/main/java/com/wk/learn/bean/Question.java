package com.wk.learn.bean;

import androidx.annotation.StringRes;

public class Question {
    //文本的id
    private int textId;
    private boolean answer;

    public Question(@StringRes int textId,boolean answer){
        this.textId = textId;
        this.answer = answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setTextId(@StringRes int textId) {
        this.textId = textId;
    }

    public int getTextId() {
        return textId;
    }

    public boolean isAnswer() {
        return answer;
    }
}
