package com.android.textaidl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * create by caiyx in 2021/4/12
 *
 * 测试binder传送大文件
 */
public class BigFileBinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        ImageView imageView = findViewById(R.id.iv_pic);

        Bundle bundle = getIntent().getBundleExtra("xx");
        IBinder iBinder = bundle.getBinder("xx");
        PicGet picGet = PicGet.Stub.asInterface(iBinder);
        try {
            Bitmap bitmap = picGet.getBitmap();
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 900, 1200, true)/*太大无法显示*/);
            bitmap.recycle();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // 不可用这种方式
       /* Bitmap bitmap = getIntent().getParcelableExtra("bm");
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 900, 1200, true)*//*太大无法显示*//*);
        bitmap.recycle();*/
    }
}
