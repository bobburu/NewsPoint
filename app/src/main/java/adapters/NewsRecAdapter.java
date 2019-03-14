package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.newspoint.ArticleDetails;
import com.example.android.newspoint.R;

import java.util.ArrayList;

import models.Article;

public class NewsRecAdapter extends RecyclerView.Adapter<NewsRecAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Article> articleArrayList;

    public NewsRecAdapter(Context mContext, ArrayList<Article> articleArrayList) {
        this.mContext = mContext;
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final int position = i;
        myViewHolder.articleImage.setImageBitmap(articleArrayList.get(i).getmImage());
        myViewHolder.articleTitle.setText(articleArrayList.get(i).getmTitle());
        myViewHolder.articleDate.setText(articleArrayList.get(i).getmDate());

        myViewHolder.articleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ArticleDetails.class);
                intent.putExtra("urlString", articleArrayList.get(position).getmWebUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView articleImage;
        TextView articleTitle;
        TextView articleDate;
        CardView articleCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = (ImageView) itemView.findViewById(R.id.article_image);
            articleTitle = (TextView) itemView.findViewById(R.id.article_title);
            articleDate = (TextView) itemView.findViewById(R.id.article_date);
            articleCard = (CardView) itemView.findViewById(R.id.article_card);
        }
    }
}
