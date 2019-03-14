package jsonparse;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.Article;

public class GetNews {

    public GetNews() {
    }

    private static final String LOG_TAG = "JSON Parsing";

    public static ArrayList<Article> getArticles(String jsonResponse) {

        ArrayList<Article> articles = new ArrayList<>();

        if (jsonResponse == null) {
            return null;
        }

        try {

            JSONObject newsObject = new JSONObject(jsonResponse);
            JSONArray articlesArray = newsObject.getJSONArray("articles");

            for (int i = 0; i < articlesArray.length(); i++) {
                JSONObject singleNewsObject = articlesArray.getJSONObject(i);
                String title = singleNewsObject.getString("title");
                Log.w(LOG_TAG, "Title -- " + title);
                String dateString = singleNewsObject.getString("publishedAt");
                String imageUrl = singleNewsObject.getString("urlToImage");
                Bitmap bitmap = HttpRequest.getBitmap(imageUrl);
                String localDate = GetNews.formattedDate(dateString);


                String webUrl = singleNewsObject.getString("url");

                Article newsArticle = new Article(title, localDate, bitmap, webUrl);
                articles.add(newsArticle);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error with the json response (obj creation)", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error with the bitmap method", e);
        }
        return articles;
    }


    public static String formattedDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error with parsing date", e);
        }
        return timeFormat.format(date);
    }


}
