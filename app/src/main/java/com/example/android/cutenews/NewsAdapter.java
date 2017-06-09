package com.example.android.cutenews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * CuteNews Created by Muir on 08/06/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> News;
    private static OnItemClickListener Listener;
    private MainActivity Context;

    public NewsAdapter(MainActivity context, ArrayList<News> news, OnItemClickListener listener) {
        Context = context;
        News = news;
        Listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(News news);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

        News news = News.get(position);

        holder.newsTitleTextView.setText(news.getTitle());
        holder.newsAuthorTextView.setText(news.getAuthor());
        holder.newsDateTextView.setText(news.getDate());
        holder.newsSectionTextView.setText(news.getSection());

        //Picasso Library to convert the url from JSONObject imageLinks to an image(@thumbnail)
        Picasso.with(Context).load(news.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.newsImageView);

        holder.bind(News.get(position), (OnItemClickListener) Listener);
    }

    @Override
    public int getItemCount() {
        return News.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImageView;
        TextView newsTitleTextView;
        TextView newsAuthorTextView;
        TextView newsDateTextView;
        TextView newsSectionTextView;


    public ViewHolder(View itemView) {
        super(itemView);

        newsImageView = (ImageView) itemView.findViewById(R.id.news_image);
        newsTitleTextView = (TextView) itemView.findViewById(R.id.news_title);
        newsAuthorTextView = (TextView) itemView.findViewById(R.id.news_author);
        newsDateTextView = (TextView) itemView.findViewById(R.id.news_date);
        newsSectionTextView = (TextView) itemView.findViewById(R.id.news_section);
    }

    public void bind(final News news, final OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(news);
            }
        });
    }

}

    public void clear() {
        News.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<News> news) {
        News.addAll(news);
        notifyDataSetChanged();
    }
}
