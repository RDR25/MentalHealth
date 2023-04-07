package com.example.mentaltherapy.menu.meditation;

import android.net.Uri;

public class VideoItems {
    int videourl;
    String videoTitle;
    String videoDiscription;

    public int getVideourl() {
        return videourl;
    }

    public void setVideourl(int videourl) {
        this.videourl = videourl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoDiscription() {
        return videoDiscription;
    }

    public void setVideoDiscription(String vifeoDiscription) {
        this.videoDiscription = vifeoDiscription;
    }

    public VideoItems(int videourl, String videoTitle, String vifeoDiscription) {
        this.videourl = videourl;
        this.videoTitle = videoTitle;
        this.videoDiscription = vifeoDiscription;
    }
}
