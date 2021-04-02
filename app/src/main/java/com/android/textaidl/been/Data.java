package com.android.textaidl.been;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * create by caiyx in 2021/4/2
 * <p>
 * 用于传递数据
 */
public class Data implements Parcelable {

    private String data;

    public Data() {
        data = "";
    }

    public Data(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    protected Data(Parcel in) {
        data = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(data);
    }

    public void readFromParcel(Parcel in){
        data = in.readString();
    }
}
