package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 10/8/2015.
 */
public class Wage implements Parcelable {
    private String title;
    public Wage() {}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    public Wage(String title) {
        this.title = "Holy moly";
    }

    public Wage(Parcel in) {
        title = in.readString();
    }

    public static final Creator<Wage> CREATOR = new Creator<Wage>() {
        @Override
        public Wage createFromParcel(Parcel in) {
            return new Wage(in);
        }

        @Override
        public Wage[] newArray(int size) {
            return new Wage[size];
        }
    };
}
