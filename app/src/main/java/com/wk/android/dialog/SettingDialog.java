package com.wk.android.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.afollestad.materialdialogs.MaterialDialog;

public class SettingDialog extends DialogFragment {


    public static SettingDialog newInstance() {
        SettingDialog dialog = new SettingDialog();
        Bundle bundle = new Bundle();
//        bundle.putLongArray("songs", songList);
        dialog.setArguments(bundle);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Bundle arguments = getArguments();
//        arguments.get()
        return new MaterialDialog.Builder(getActivity()).title("Add to playlist")
                .items("xx")
                .itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                if (which == 0) {
                    SettingDialog.newInstance()
                            .show(getActivity()
                                    .getSupportFragmentManager(), "CREATE_PLAYLIST");
                    return;
                }
                dialog.dismiss();

            }
        }).build();
    }
}
