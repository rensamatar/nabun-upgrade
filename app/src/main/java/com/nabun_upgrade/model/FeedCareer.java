package com.nabun_upgrade.model;

/**
 * Created by admin on 10/15/2015.
 */
public class FeedCareer {

    private String attribute;
    private String banner;
    private String id;
    private String title;

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttribute() {
        return attribute;
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
