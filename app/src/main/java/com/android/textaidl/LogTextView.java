package com.android.textaidl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * create by caiyx in 2021/4/2
 */
@SuppressLint("AppCompatCustomView")
public class LogTextView extends TextView {

    public LogTextView(Context context) {
        super(context);
    }

    public LogTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LogTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void writeLog(String log) {
        String oldLog = getText().toString();
        String newLog = log + "\n" + oldLog;
        setText(newLog);
    }
}
