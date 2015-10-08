package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 10/8/2015.
 */
public class Staff implements Parcelable {

    private String id;
    private String nickname;
    private String phone;

    public Staff() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nickname);
        dest.writeString(phone);
    }

    public Staff(Parcel in) {
        id = in.readString();
        nickname = in.readString();
        phone = in.readString();
    }

    public static final Creator<Staff> CREATOR = new Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel in) {
            return new Staff(in);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };
}
