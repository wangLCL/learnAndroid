package com.wk.learn.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

/**
 * 在屏幕上显示DialogFragment，托管activity的FragmentManger会调用
 */
public class DateDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(
                requireContext(),
                null,
                year,
                month,
                day
        );
    }

    public static DateDialogFragment newInstance() {

        Bundle args = new Bundle();
        args.putString("key","value");
        DateDialogFragment fragment = new DateDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
























