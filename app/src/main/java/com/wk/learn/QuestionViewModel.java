package com.wk.learn;

import androidx.lifecycle.ViewModel;

import com.wk.android.R;
import com.wk.learn.bean.Question;

import java.util.ArrayList;

public class QuestionViewModel extends ViewModel {

    private int questionIndex;
    private ArrayList<Question> arrayList;
    public QuestionViewModel(){
        arrayList = new ArrayList<>();
        arrayList.add(new Question(R.string.question_text_1,false));
        arrayList.add(new Question(R.string.question_text_2,false));
        arrayList.add(new Question(R.string.question_text_3,false));
        arrayList.add(new Question(R.string.question_text_4,false));
        arrayList.add(new Question(R.string.question_text_5,false));
        arrayList.add(new Question(R.string.question_text_6,false));
        arrayList.add(new Question(R.string.question_text_7,false));
    }

    public boolean getAnswer(){
        return arrayList.get(questionIndex).isAnswer();
    }

    public int questionResourceId(){
        return arrayList.get(questionIndex).getTextId();
    }

    public void moveToNext(){
        questionIndex =(questionIndex + 1)%arrayList.size() ;
    }
    /**
     * 在销毁之前，可以做一些善后工作，
     * eg:解绑耨个数据源
     */
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
