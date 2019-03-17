package models;


public class Article {

    private String mTitle;
    private String mDate;
    private String mImageUrl;
    private String mWebUrl;

    public Article() {
    }

    public Article(String mTitle, String mDate, String mImageUrl, String mWebUrl) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mImageUrl = mImageUrl;
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

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public void setmWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }
}
