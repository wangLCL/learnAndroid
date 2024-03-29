package com.wk.learn;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wk.android.R;
import com.wk.android.constant.Constant;
import com.wk.learn.bean.Question;
import com.wk.learn.dialog.DateDialogFragment;

import java.util.ArrayList;

public class MainView extends AppCompatActivity {
//    private ArrayList<Question> arrayList;
//    private int questionIndex;
    private TextView questionTextView;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        questionTextView = findViewById(R.id.question_text_view);
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        if (savedInstanceState!=null) {
            questionViewModel.setQuestionIndex(savedInstanceState.getInt("index", 0));
        }
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

            questionViewModel.moveToNext();
            updateQuestion();
//            Toast.makeText(MainView.this,"next btn",Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.cheat).setOnClickListener(view->{
            boolean answer = questionViewModel.getAnswer();
            Intent intent = CheakActivity.newIntent(MainView.this, answer);
            startActivityForResult(intent,99);
        });
        {
            Uri parse = Uri.parse("10086");
            Intent intent = new Intent(Intent.ACTION_SEND, parse);
            intent.putExtra("sms_body", "heelo");
        }
        {
            Uri smsUri = Uri.parse("smsto:1008611");
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsUri);
            intent.putExtra("sms_body", "这是一条测试短信");
            startActivity(intent);
        }
        {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> xxxxxxxxxxx = sms.divideMessage("xxxxxxxxxxx");
            for (String s : xxxxxxxxxxx) {
                sms.sendTextMessage("" + 10086, null, s, null, null);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",questionViewModel.getQuestionIndex());
    }


    private void updateQuestion() {
        questionTextView.setText(questionViewModel.questionResourceId());
    }

    private void checkAnswer(boolean userAnswer){
        boolean answer = questionViewModel.getAnswer();
        if (userAnswer == answer) {
            Toast.makeText(this,R.string.answer_right,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,R.string.answer_right,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != 99){
            return;
        }

        if (resultCode == 100){
            boolean result = data.getBooleanExtra("result", false);
            Toast.makeText(MainView.this,result+"",Toast.LENGTH_SHORT).show();
        }
    }
}