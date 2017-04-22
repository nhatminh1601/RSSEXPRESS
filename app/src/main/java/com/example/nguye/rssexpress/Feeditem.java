package com.example.nguye.rssexpress;

/**
 * Created by nguye on 19/04/2017.
 */

public class Feeditem {
    String title;
    String description;
    String pubDate;
    String link;
    String guid;

//
//    public Feeditem(String title, String description, String pubDate, String link, String guid) {
//        this.title = title;
//        this.description = description;
//        this.pubDate = pubDate;
//        this.link = link;
//        this.guid = guid;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
