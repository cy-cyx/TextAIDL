package com.android.textaidl.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.textaidl.BigFileBinderActivity;
import com.android.textaidl.Interface;
import com.android.textaidl.PicGet;
import com.android.textaidl.been.Data;
import com.android.textaidl.LogTextView;
import com.android.textaidl.R;

/**
 * create by caiyx in 2021/4/2
 * <p>
 * 客户端
 */
public class ClientActivity extends AppCompatActivity {

    private LogTextView logTextView;
    private Interface intarface;
    private MyConnection myConnection;

    class MyConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            intarface = Interface.Stub.asInterface(iBinder);
            logTextView.writeLog("服务连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            intarface = null;
            logTextView.writeLog("服务断开");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        logTextView = findViewById(R.id.ltv_log);
        logTextView.writeLog(getPackageName());

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.img);
    }

    public void onConnection(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.android.textaidl2", "com.android.textaidl.AIDLServer");
        myConnection = new MyConnection();
        bindService(intent, myConnection, BIND_AUTO_CREATE);
    }

    public void onDisConnection(View view) {
        if (myConnection != null) {
            unbindService(myConnection);
            myConnection = null;
            intarface = null;
        }
    }

    public void onPrintService(View view) {
        try {
            if (intarface != null) {
                String sting = intarface.getSting();
                logTextView.writeLog("服务对应的包名" + sting);
            }
        } catch (RemoteException e) {
            logTextView.writeLog("服务已经断开");
        }
    }

    private Data data = new Data("1");

    public void onTextData(View view) {
        try {
            if (intarface != null) {
                intarface.upDataData(data);
                logTextView.writeLog("data" + data.getData());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Bitmap bm;

    public void onBitmapTransaction(View view) {
        try {
            if (intarface != null) {

                Log.d("xx", "准备跨进程传的" + bm.getWidth() + ":" + bm.getHeight() + "time:" + System.currentTimeMillis());
                intarface.getBitmap(bm);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onBitmapActivity(View view) {
        Intent intent = new Intent(this, BigFileBinderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBinder("xx", new PicGet.Stub() {
            @Override
            public Bitmap getBitmap() {
                return bm;
            }
        });
        intent.putExtra("xx", bundle);

        // 不可用这种方式
        /*intent.putExtra("bm", bm);*/
        startActivity(intent);
    }
}
