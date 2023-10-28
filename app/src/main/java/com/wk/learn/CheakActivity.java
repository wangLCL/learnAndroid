package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wk.android.R;

public class CheakActivity extends AppCompatActivity {

    public static Intent newIntent(MainView mainView, boolean answer) {
        Intent intent = new Intent(mainView, CheakActivity.class);
        intent.putExtra("answer",answer);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheak);
        Intent intent = getIntent();
        boolean answer = intent.getBooleanExtra("answer", false);
        TextView answerTextView = findViewById(R.id.answer_text_view);
        findViewById(R.id.show_answer_button).setOnClickListener(view->{
            answerTextView.setText(answer+"");
        });
    }

    private void setResultAnswer(boolean flag){
        Intent i = getIntent();
        i.putExtra("result",flag);
        setResult(100,i);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}