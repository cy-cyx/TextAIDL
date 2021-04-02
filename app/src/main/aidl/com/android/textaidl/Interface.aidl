package com.android.textaidl;
import com.android.textaidl.Data;
import android.graphics.Bitmap;

interface Interface {

    String getSting();

// 用于测试tag in out inout
//    void upDataData(in Data data);
//    void upDataData(out Data data);
    void upDataData(inout Data data);

    void getBitmap(in Bitmap bm);
}
