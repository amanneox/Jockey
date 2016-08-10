package com.marverenic.music.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.marverenic.music.R;
import com.triggertrap.seekarc.SeekArc;

public class DurationPickerDialogFragment extends DialogFragment {

    private static final String KEY_TITlE = "DurationPickerDialogFragment.TITLE";
    private static final String KEY_MAX_VAL = "DurationPickerDialogFragment.MAX_VALUE";
    private static final String KEY_DEFAULT_VAL = "DurationPickerDialogFragment.DEFAULT_VALUE";
    private static final String KEY_SAVED_VAL = "DurationPickerDialogFragment.SAVED_VALUE";

    private SeekArc mSlider;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View contentView = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_duration_picker, null);

        mSlider = (SeekArc) contentView.findViewById(R.id.duration_picker_slider);

        String title = getArguments().getString(KEY_TITlE);
        int maxValue = getArguments().getInt(KEY_MAX_VAL, Integer.MAX_VALUE);

        mSlider.setMax(maxValue);

        if (savedInstanceState == null) {
            int defaultValue = getArguments().getInt(KEY_DEFAULT_VAL, 0);
            mSlider.setProgress(defaultValue);
        } else {
            mSlider.setProgress(savedInstanceState.getInt(KEY_SAVED_VAL));
        }

        return new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setView(contentView)
                .setPositiveButton(R.string.action_done, null)
                .setNegativeButton(R.string.action_cancel, null)
                .show();
    }

    public static class Builder {

        private FragmentManager mFragmentManager;

        private String mTitle;
        private int mMax;
        private int mDefault;

        public Builder(AppCompatActivity activity) {
            this(activity.getSupportFragmentManager());
        }

        public Builder(FragmentManager fragmentManager) {
            mFragmentManager = fragmentManager;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMaxValue(int max) {
            mMax = max;
            return this;
        }

        public Builder setDefaultValue(int value) {
            mDefault = value;
            return this;
        }

        public void show(String tag) {
            Bundle args = new Bundle();
            args.putString(KEY_TITlE, mTitle);
            args.putInt(KEY_MAX_VAL, mMax);
            args.putInt(KEY_DEFAULT_VAL, mDefault);

            DurationPickerDialogFragment dialogFragment = new DurationPickerDialogFragment();
            dialogFragment.setArguments(args);

            dialogFragment.show(mFragmentManager, tag);
        }
    }
}
