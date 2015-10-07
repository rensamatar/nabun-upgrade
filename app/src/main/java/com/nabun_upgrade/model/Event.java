package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by admin on 10/7/2015.
 */
public class Event implements Parcelable {

    private int id;
    private String title;
    private String summary;
    private String body;
    private String banner;
    private Date created_at;
    private Date updated_at;

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(body);
        dest.writeString(banner);
        dest.writeLong(created_at == null ? -1 : created_at.getTime());
        dest.writeLong(updated_at == null ? -1 : updated_at.getTime());
    }

    public Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        summary = in.readString();
        body = in.readString();
        banner = in.readString();
        long dateMillis = in.readLong();
        created_at = (dateMillis == -1 ? null : new Date(dateMillis));
        updated_at = (dateMillis == -1 ? null : new Date(dateMillis));
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
