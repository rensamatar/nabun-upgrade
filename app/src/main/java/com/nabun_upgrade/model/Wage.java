package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 10/8/2015.
 */
public class Wage implements Parcelable {
    private String id;
    private String title;

    public Wage() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Wage(String title) {
        this.title = "Holy moly";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
    }

    protected Wage(Parcel in) {
        id = in.readString();
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
