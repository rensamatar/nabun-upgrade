package com.nabun_upgrade.model;

/**
 * Created by admin on 10/19/2015.
 */
public class FeedEvent {

    private String id;
    private String title;
    private String banner;

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
