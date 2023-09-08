package com.wk.android.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class RestDialog extends DialogFragment {
    public static RestDialog newInstance() {
        RestDialog restDialog = new RestDialog();
        Bundle bundle = new Bundle();
        restDialog.setArguments(bundle);
        return restDialog;
    }

    private Runnable runnable;
    public void setRestListener(Runnable runnable){
        this.runnable = runnable;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                        .setTitle("我是一个普通Dialog")
                        .setMessage("你要点击哪一个按钮呢?")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        runnable.run();
                                    }
                                })
                        .setNegativeButton("关闭",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dismiss();
                                    }
                                })
                .create();
    }
}
