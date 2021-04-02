package com.android.textaidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.android.textaidl.client.ClientActivity;
import com.android.textaidl.server.ServerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.type.equals("client")) {
            startActivity(new Intent(this, ClientActivity.class));
            finish();
        } else if (BuildConfig.type.equals("server")) {
            startActivity(new Intent(this, ServerActivity.class));
            finish();
        } else {
            finish();
        }
    }
}
