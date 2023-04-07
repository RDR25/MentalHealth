package com.example.mentaltherapy.menu.meditation;

import android.net.Uri;

public class VideoItems {
    int videourl;
    int imgurl;
    String videoTitle;
    String videoDiscription;

    public int getImgurl() {
        return imgurl;
    }

    public void setImgurl(int imgurl) {
        this.imgurl = imgurl;
    }

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

    public VideoItems(int videourl,int imgurl, String videoTitle, String vifeoDiscription) {
        this.videourl = videourl;
        this.imgurl=imgurl;
        this.videoTitle = videoTitle;
        this.videoDiscription = vifeoDiscription;
    }
}
