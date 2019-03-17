package pagerfrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.newspoint.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import adapters.NewsRecAdapter;
import models.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class Top extends Fragment {


    public Top() {
        // Required empty public constructor
    }

    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=36ea9548713341c2a86f3aca28a60ae7";

    private RecyclerView mRecyclerView;
    NewsRecAdapter mAdapter;
    ArrayList<Article> articleArrayList;

    private JsonObjectRequest request;
    private RequestQueue queue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_frag_tab, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);

        articleArrayList = new ArrayList<>();
        queue = Volley.newRequestQueue(getContext());
        jsonRequest();

        return view;
    }

    private void jsonRequest() {

        request = new JsonObjectRequest(Request.Method.GET, NEWS_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        String imageUrl = jsonObject.getString("urlToImage");
                        String webUrl = jsonObject.getString("url");
                        String date = jsonObject.getString("publishedAt");
                        String formattedDate = formattedDate(date);

                        Article article = new Article();
                        article.setmTitle(title);
                        article.setmImageUrl(imageUrl);
                        article.setmDate(formattedDate);
                        article.setmWebUrl(webUrl);

                        articleArrayList.add(article);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mAdapter = new NewsRecAdapter(getContext(), articleArrayList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);

    }

    public static String formattedDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            Log.e("Top Activity", "Error with parsing date", e);
        }
        return timeFormat.format(date);
    }

}
