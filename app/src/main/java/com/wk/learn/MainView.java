package com.wk.learn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wk.android.R;

public class MainView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        Button trueBtn = findViewById(R.id.true_btn);
        Button falseBtn = findViewById(R.id.false_btn);

        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainView.this, R.string.true_info, Toast.LENGTH_LONG);
//                Starting from Android , for apps targeting API level or higher,
//                this method is a no-op when called on text toasts.Build.VERSION_CODES#
//                RBuild.VERSION_CODES#R
//                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 2100);
                toast.show();
            }
        });

        falseBtn.setOnClickListener(view->{
            Toast toast = Toast.makeText(MainView.this,
                    R.string.false_info, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        });
    }
}