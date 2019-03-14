package models;

import android.graphics.Bitmap;


public class Article {

    private String mTitle;
    private String mDate;
    private Bitmap mImage;

    private String mWebUrl;

    public Article(String mTitle, String mDate, Bitmap mImage) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mImage = mImage;
    }

    public Article(String mTitle, String mDate, Bitmap mImage, String mWebUrl) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mImage = mImage;
        this.mWebUrl = mWebUrl;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public void setmWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public Bitmap getmImage() {
        return mImage;
    }

    public void setmImage(Bitmap mImage) {
        this.mImage = mImage;
    }
}
