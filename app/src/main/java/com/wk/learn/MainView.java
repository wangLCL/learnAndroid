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
import com.wk.learn.dialog.DateDialogFragment;

import java.util.ArrayList;

public class MainView extends AppCompatActivity {
    private ArrayList<Question> arrayList;
    private int questionIndex;
    private TextView questionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        arrayList = new ArrayList<>();
        arrayList.add(new Question(R.string.question_text_1,false));
        arrayList.add(new Question(R.string.question_text_2,false));
        arrayList.add(new Question(R.string.question_text_3,false));
        arrayList.add(new Question(R.string.question_text_4,false));
        arrayList.add(new Question(R.string.question_text_5,false));
        questionTextView = findViewById(R.id.question_text_view);
        updateQuestion();
        Button trueBtn = findViewById(R.id.true_btn);
        Button falseBtn = findViewById(R.id.false_btn);
        Button nextBtn = findViewById(R.id.next_button);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                Toast.makeText(MainView.this,"true btn",Toast.LENGTH_SHORT).show();
            }
        });

        falseBtn.setOnClickListener(view->{
            checkAnswer(false);
//            Toast.makeText(MainView.this,"false btn",Toast.LENGTH_SHORT).show();
        });
        nextBtn.setOnClickListener(view->{
            updateQuestion();
//            Toast.makeText(MainView.this,"next btn",Toast.LENGTH_SHORT).show();
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