package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 10/8/2015.
 */
public class Photos implements Parcelable {

    private String img_01;
    private String img_02;
    private String img_03;
    private String img_04;
    private String img_05;
    private String img_06;
    private String img_07;
    private String img_08;

    public Photos() {}

    public void setImg_01(String img_01) {
        this.img_01 = img_01;
    }

    public void setImg_02(String img_02) {
        this.img_02 = img_02;
    }

    public void setImg_03(String img_03) {
        this.img_03 = img_03;
    }

    public void setImg_04(String img_04) {
        this.img_04 = img_04;
    }

    public void setImg_05(String img_05) {
        this.img_05 = img_05;
    }

    public void setImg_06(String img_06) {
        this.img_06 = img_06;
    }

    public void setImg_07(String img_07) {
        this.img_07 = img_07;
    }

    public void setImg_08(String img_08) {
        this.img_08 = img_08;
    }

    public String getImg_01() {
        return img_01;
    }

    public String getImg_02() {
        return img_02;
    }

    public String getImg_03() {
        return img_03;
    }

    public String getImg_04() {
        return img_04;
    }

    public String getImg_05() {
        return img_05;
    }

    public String getImg_06() {
        return img_06;
    }

    public String getImg_07() {
        return img_07;
    }

    public String getImg_08() {
        return img_08;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(img_01);
        dest.writeString(img_02);
        dest.writeString(img_03);
        dest.writeString(img_04);
        dest.writeString(img_05);
        dest.writeString(img_06);
        dest.writeString(img_07);
        dest.writeString(img_08);
    }

    public Photos(Parcel in) {
        img_01 = in.readString();
        img_02 = in.readString();
        img_03 = in.readString();
        img_04 = in.readString();
        img_05 = in.readString();
        img_06 = in.readString();
        img_07 = in.readString();
        img_08 = in.readString();
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };
}
