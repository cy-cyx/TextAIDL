package com.android.textaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * create by caiyx in 2021/4/2
 * <p>
 * 服务
 */
public class AIDLServer extends Service {

    private MyIBinder myIBinder = new MyIBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("xx", "服务启动");
        Toast.makeText(this,"服务启动",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("xx", "服务停止");
        Toast.makeText(this,"服务停止",Toast.LENGTH_SHORT).show();
    }

    class MyIBinder extends Interface.Stub {

        @Override
        public String getSting() throws RemoteException {
            return getPackageName() + System.currentTimeMillis();
        }
    }
}
