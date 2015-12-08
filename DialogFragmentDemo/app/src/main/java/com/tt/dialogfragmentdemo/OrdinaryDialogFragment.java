package com.tt.dialogfragmentdemo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tuozhaobing on 15-12-8.
 * Add Some Comment
 */
public class OrdinaryDialogFragment extends DialogFragment {
    private static final String TAG = "OrdinaryDialogFragment";

    public OrdinaryDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment, null);
    }
}
