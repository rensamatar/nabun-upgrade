package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.nabun_upgrade.utility.AppFunctions;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by admin on 9/29/2015.
 */
public class Career implements Parcelable {
    private String id;
    private String title;
    private String banner;
    private String author;
    private String attribute;
    private String gender;
    private String age;
    private String qualifications;
    private ArrayList<Wage> wage;
    private ArrayList<Staff> staff;
    private String published_date;
    private String created_at;
    private String updated_at;

    public Career() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public ArrayList<Wage> getWage() {
        return wage;
    }

    public void setWage(ArrayList<Wage> wage) {
        this.wage = wage;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Career(JSONObject object) {
        Iterator<String> dataIter = object.keys();
        while (dataIter.hasNext()) {
            try {
                String key = dataIter.next();
                if (key.equalsIgnoreCase("id")) {
                    if (AppFunctions.checkJSONObjectForKey(key, object)) {
                        this.setId(object.getString(key));
                    }
                } else if (key.equalsIgnoreCase("title")) {
                    if (AppFunctions.checkJSONObjectForKey(key, object)) {
                        this.setTitle(object.getString(key));
                    }
                } else if (key.equalsIgnoreCase("attribute")) {
                    if (AppFunctions.checkJSONObjectForKey(key, object)) {
                        this.setTitle(object.getString(key));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(banner);
        dest.writeString(author);
        dest.writeString(attribute);
        dest.writeString(gender);
        dest.writeString(age);
        dest.writeString(qualifications);
        dest.writeTypedList(wage);
        dest.writeTypedList(staff);
        dest.writeString(published_date);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }

    public Career(Parcel in) {
        id = in.readString();
        title = in.readString();
        banner = in.readString();
        author = in.readString();
        attribute = in.readString();
        gender = in.readString();
        age = in.readString();
        qualifications = in.readString();
        in.createTypedArrayList(Wage.CREATOR);
        in.createTypedArrayList(Staff.CREATOR);
//        in.readTypedList(wage, Wage.CREATOR);
//        in.readTypedList(staff, Staff.CREATOR);
        published_date = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Career> CREATOR = new Creator<Career>() {
        @Override
        public Career createFromParcel(Parcel in) {
            return new Career(in);
        }

        @Override
        public Career[] newArray(int size) {
            return new Career[size];
        }
    };
}
