package com.susantokun.project1_cataloguemovie;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public MovieAdapter(Context mContext){
        this.mContext = mContext;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems items){
        mData.add(items);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    public int getViewType(int i){
        return 0;
    }

    public int getViewTypeCount(){
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public MovieItems getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.movie_items, null);

            holder.ivPoster = (ImageView) view.findViewById(R.id.iv_poster);
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            holder.tvReleaseDate = (TextView) view.findViewById(R.id.tv_release_date);
            holder.tvVoteAverage = (TextView) view.findViewById(R.id.tv_vote_average);
            holder.tvOverview = (TextView) view.findViewById(R.id.tv_overview);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Glide.with(mContext).load("http://image.tmdb.org/t/p/w185/"+mData.get(i).getPoster()).into(holder.ivPoster);
        holder.tvTitle.setText(mData.get(i).getTitle());
        String get_date = mData.get(i).getReleaseDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(get_date);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_of_release = new_date_format.format(date);
            holder.tvReleaseDate.setText("Rilis : "+date_of_release);
        } catch (ParseException e){
            e.printStackTrace();
        }
        holder.tvVoteAverage.setText("Rating : "+mData.get(i).getVoteAverage()+"%");
        holder.tvOverview.setText(mData.get(i).getOverview());

        return view;
    }

    private static class ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvReleaseDate, tvVoteAverage, tvOverview;
    }
}
