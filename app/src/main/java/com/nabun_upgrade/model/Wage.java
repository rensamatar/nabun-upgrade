package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 10/8/2015.
 */
public class Wage implements Parcelable {
    private String w1;
    private String w2;
    private String w3;
    private String w4;
    private String w5;
    private String w6;
    private String w7;
    private String w8;
    private String w9;
    private String w10;
    private String w11;
    private String w12;
    private String w13;
    private String w14;
    private String w15;
    private String w16;

    public Wage() {}

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }

    public void setW4(String w4) {
        this.w4 = w4;
    }

    public void setW5(String w5) {
        this.w5 = w5;
    }

    public void setW6(String w6) {
        this.w6 = w6;
    }

    public void setW7(String w7) {
        this.w7 = w7;
    }

    public void setW8(String w8) {
        this.w8 = w8;
    }

    public void setW9(String w9) {
        this.w9 = w9;
    }

    public void setW10(String w10) {
        this.w10 = w10;
    }

    public void setW11(String w11) {
        this.w11 = w11;
    }

    public void setW12(String w12) {
        this.w12 = w12;
    }

    public void setW13(String w13) {
        this.w13 = w13;
    }

    public void setW14(String w14) {
        this.w14 = w14;
    }

    public void setW15(String w15) {
        this.w15 = w15;
    }

    public void setW16(String w16) {
        this.w16 = w16;
    }

    public String getW1() {
        return w1;
    }

    public String getW2() {
        return w2;
    }

    public String getW3() {
        return w3;
    }

    public String getW4() {
        return w4;
    }

    public String getW5() {
        return w5;
    }

    public String getW6() {
        return w6;
    }

    public String getW7() {
        return w7;
    }

    public String getW8() {
        return w8;
    }

    public String getW9() {
        return w9;
    }

    public String getW10() {
        return w10;
    }

    public String getW11() {
        return w11;
    }

    public String getW12() {
        return w12;
    }

    public String getW13() {
        return w13;
    }

    public String getW14() {
        return w14;
    }

    public String getW15() {
        return w15;
    }

    public String getW16() {
        return w16;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(w1);
        dest.writeString(w2);
        dest.writeString(w3);
        dest.writeString(w4);
        dest.writeString(w5);
        dest.writeString(w6);
        dest.writeString(w7);
        dest.writeString(w8);
        dest.writeString(w9);
        dest.writeString(w10);
        dest.writeString(w11);
        dest.writeString(w12);
        dest.writeString(w13);
        dest.writeString(w14);
        dest.writeString(w15);
        dest.writeString(w16);
    }

    public Wage(Parcel in) {
        w1 = in.readString();
        w2 = in.readString();
        w3 = in.readString();
        w4 = in.readString();
        w5 = in.readString();
        w6 = in.readString();
        w7 = in.readString();
        w8 = in.readString();
        w9 = in.readString();
        w10 = in.readString();
        w11 = in.readString();
        w12 = in.readString();
        w13 = in.readString();
        w14 = in.readString();
        w15 = in.readString();
        w16 = in.readString();
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
