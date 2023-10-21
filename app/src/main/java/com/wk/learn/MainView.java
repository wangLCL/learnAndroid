package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wk.android.R;
import com.wk.learn.bean.Question;

import java.util.ArrayList;

public class MainView extends AppCompatActivity {
    private TextView questionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);


        //首次调用会返回一个新的QuestionModel，再次访问的时候，返回上次创建的。
        //当activity销毁掉的时候，model也就销毁了。
        QuestionViewModel questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

//        QuestionViewModel


        questionTextView = findViewById(R.id.question_text_view);
        updateQuestion();

        Button trueBtn = findViewById(R.id.true_btn);
        Button falseBtn = findViewById(R.id.false_btn);
        Button nextBtn = findViewById(R.id.next_button);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseBtn.setOnClickListener(view->{
            checkAnswer(false);
        });
        nextBtn.setOnClickListener(view->{
            updateQuestion();
        });
    }

    private void updateQuestion() {
        questionIndex = questionIndex%arrayList.size();
        questionTextView.setText(arrayList.get(questionIndex).getTextId());
        questionIndex ++;
    }

    private void checkAnswer(boolean userAnswer){
        questionIndex = questionIndex%arrayList.size();
        boolean answer = arrayList.get(questionIndex).isAnswer();
        if (userAnswer == answer) {
            Toast.makeText(this,R.string.answer_right,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.answer_right,Toast.LENGTH_LONG).show();
        }

    }
}