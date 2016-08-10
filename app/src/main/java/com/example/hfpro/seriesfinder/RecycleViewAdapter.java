package com.example.hfpro.seriesfinder;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
{

    private ArrayList<TvShow> mDataset;
    private Context context ;

    public class ViewHolder extends RecyclerView.ViewHolder {



        public TextView serieName ;
        public TextView type  ;
        public TextView country  ;
        public RatingBar ratingBar ;
        public ImageView icon ;


        public ViewHolder(View view) {
            super(view);
            serieName = (TextView) view.findViewById(R.id.titleView) ;
            type = (TextView) view.findViewById(R.id.genreText) ;
            country = (TextView) view.findViewById(R.id.countryTextView) ;
            ratingBar = (RatingBar) view.findViewById(R.id.MyRating) ;
            icon = (ImageView) view.findViewById(R.id.showIcon) ;
        }
    }

    // The Constructor

    public RecycleViewAdapter (Context context , ArrayList<TvShow> list)
    {
        this.mDataset = list ;
        this.context = context ;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.tvshow_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final TvShow tvCurrent = mDataset.get(position) ;

        holder.serieName.setText(tvCurrent.name);
        holder.icon.setImageBitmap(ContentDownloader.getBitmapData(tvCurrent.image));
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , Main3Activity.class) ;
                intent.putExtra("URL" , tvCurrent.url) ;
                context.startActivity(intent);
            }
        });
        holder.country.setText(tvCurrent.country);
        holder.type.setText(tvCurrent.genre);
        if(tvCurrent.rating.equalsIgnoreCase("null"))
            holder.ratingBar.setRating(2.5f);
        else
            holder.ratingBar.setRating(Float.parseFloat(tvCurrent.rating));
    }


    @Override
    public int getItemCount() {
        return mDataset.size() ;
    }
}
