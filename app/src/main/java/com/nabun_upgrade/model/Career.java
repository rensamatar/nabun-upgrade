package com.nabun_upgrade.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.nabun_upgrade.utility.AppFunctions;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

/**
 * Created by admin on 9/29/2015.
 */
public class Career implements Parcelable {
    private int id;
    private String name;
    private String title;
    private String summary;
    private String body;
    private String author;
    private String banner;
    private String created_at;
    private String updated_at;

    public Career() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
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
                        this.setId(object.getInt(key));
                    }
                } else if (key.equalsIgnoreCase("name")) {
                    if (AppFunctions.checkJSONObjectForKey(key, object)) {
                        this.setName(object.getString(key));
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
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(body);
        dest.writeString(author);
        dest.writeString(banner);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }

    protected Career(Parcel in) {
        id = in.readInt();
        name = in.readString();
        title = in.readString();
        summary = in.readString();
        body = in.readString();
        author = in.readString();
        banner = in.readString();
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
