package com.android.textaidl.server;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.textaidl.AIDLServer;
import com.android.textaidl.LogTextView;
import com.android.textaidl.R;

/**
 * create by caiyx in 2021/4/2
 * <p>
 * 服务端
 */
public class ServerActivity extends AppCompatActivity {

    private LogTextView logTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        logTextView = findViewById(R.id.ltv_log);
        logTextView.writeLog(getPackageName());
    }


    public void startServer(View view) {
        startService(new Intent(this, AIDLServer.class));
        logTextView.writeLog("启动服务");
    }

    public void stopServer(View view) {
        stopService(new Intent(this, AIDLServer.class));
        logTextView.writeLog("停止服务");
    }
}
