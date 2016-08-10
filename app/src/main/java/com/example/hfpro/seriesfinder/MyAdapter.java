package com.example.hfpro.seriesfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<TvShow> items;

    public MyAdapter(Context context, ArrayList<TvShow> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TvShow tvShow = (TvShow) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.tvshow_item, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.titleView);
        TextView country = (TextView) convertView.findViewById(R.id.countryTextView);
        TextView genre = (TextView) convertView.findViewById(R.id.genreText);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.MyRating);
        ImageView icon = (ImageView) convertView.findViewById(R.id.showIcon);

        name.setText(tvShow.name);
        country.setText(tvShow.country);
        genre.setText("Genre : " + tvShow.genre);
        icon.setImageBitmap(ContentDownloader.getBitmapData(tvShow.image));
        if(tvShow.rating.equalsIgnoreCase("null"))
               ratingBar.setRating(Float.parseFloat("2.5"));
        else
                ratingBar.setRating(Float.parseFloat(tvShow.rating)/2);


        return convertView;

    }
}

