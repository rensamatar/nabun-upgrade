package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 10/7/2015.
 */
public class Event implements Parcelable {

    private int id;
    private String title;
    private String banner;
    private String author;
    private String body;
    private ArrayList<Photos> photos;
    private String published_date;
    private String created_at;
    private String updated_at;

    public Event() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ArrayList<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photos> photos) {
        this.photos = photos;
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

    public Event(JSONObject object) {
        try {
            this.setId(object.optInt("id"));
            this.setTitle(object.optString("title"));
            this.setBanner(object.optString("banner"));
            this.setAuthor(object.optString("author"));
            this.setBody(object.optString("body"));
            this.setPublished_date(object.optString("published_date"));
            this.setCreated_at(object.optString("created_at"));
            this.setUpdated_at(object.optString("updated_at"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(banner);
        dest.writeString(author);
        dest.writeString(body);
        dest.writeTypedList(photos);
        dest.writeString(published_date);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }

    public Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        banner = in.readString();
        author = in.readString();
        body = in.readString();
        in.readTypedList(photos, Photos.CREATOR);
        published_date = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
