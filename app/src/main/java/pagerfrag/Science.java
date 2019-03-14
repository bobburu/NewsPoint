package pagerfrag;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.newspoint.R;

import java.util.ArrayList;

import adapters.NewsRecAdapter;
import jsonparse.GetNews;
import jsonparse.HttpRequest;
import models.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class Science extends Fragment {


    public Science() {
        // Required empty public constructor
    }

    private static final String NEWS_URL = "https://newsapi.org/v2/top-headlines?country=in&category=science&apiKey=36ea9548713341c2a86f3aca28a60ae7";

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    NewsRecAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pager_frag_tab, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        NewsAsyncTask asyncTask = new NewsAsyncTask();
        asyncTask.execute(NEWS_URL);

        return view;
    }

    public class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<Article>> {

        @Override
        protected ArrayList<Article> doInBackground(String... strings) {
            String jsonData = HttpRequest.getJsonResponse(strings[0]);
            ArrayList<Article> articleArrayList = GetNews.getArticles(jsonData);
            return articleArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> articles) {
            super.onPostExecute(articles);

            mProgressBar.setVisibility(View.GONE);

            mAdapter = new NewsRecAdapter(getContext(), articles);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(layoutManager);
        }
    }
}
