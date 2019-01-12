package com.susantokun.project1_cataloguemovie;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovieActivity extends AppCompatActivity {

    public static String EXTRA_POSTER = "EXTRA_POSTER";
    public static String EXTRA_TITLE = "EXTRA_TITLE";
    public static String EXTRA_RELEASE_DATE = "EXTRA_RELEASE_DATE";
    public static String EXTRA_VOTE_COUNT = "EXTRA_VOTE_COUNT";
    public static String EXTRA_VOTE_AVERAGE = "EXTRA_VOTE_AVERAGE";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";

    private TextView tvTitle, tvReleaseDate, tvVoteCount, tvVoteAverage, tvOverview;
    private ImageView ivPoster;
    private Context mContext;

    private String change_releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ivPoster = (ImageView) findViewById(R.id.iv_detail_poster);
        tvTitle = (TextView) findViewById(R.id.tv_detail_title);
        tvReleaseDate = (TextView) findViewById(R.id.tv_detail_release_date);
        tvVoteCount = (TextView) findViewById(R.id.tv_detail_vote_count);
        tvVoteAverage = (TextView) findViewById(R.id.tv_detail_vote_average);
        tvOverview = (TextView) findViewById(R.id.tv_detail_overview);

        String poster = getIntent().getStringExtra(EXTRA_POSTER);
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String releaseDate = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        String voteCount = getIntent().getStringExtra(EXTRA_VOTE_COUNT);
        String voteAverage = getIntent().getStringExtra(EXTRA_VOTE_AVERAGE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(releaseDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_of_release = new_date_format.format(date);
//            tvReleaseDate.setText("Rilis : " + date_of_release);
            final SpannableStringBuilder normal_rilis = new SpannableStringBuilder("Rilis : " + date_of_release);
            final StyleSpan bold_rilis = new StyleSpan(android.graphics.Typeface.BOLD);
            normal_rilis.setSpan(bold_rilis,0,7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            tvReleaseDate.setText(normal_rilis);
        } catch (Exception e) {
            e.printStackTrace();
        }



        tvTitle.setText(title);
//        tvVoteCount.setText("Total : " + voteCount + " suara");
//        tvVoteAverage.setText("Rating : " + voteAverage + "%");

        final SpannableStringBuilder normal_vote_count = new SpannableStringBuilder("Total : " + voteCount + " suara");
        final StyleSpan bold_vote_count = new StyleSpan(android.graphics.Typeface.BOLD);
        normal_vote_count.setSpan(bold_vote_count,0,7, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tvVoteCount.setText(normal_vote_count);

        final SpannableStringBuilder normal_rating = new SpannableStringBuilder("Rating : " + voteAverage + "%");
        final StyleSpan bold_rating = new StyleSpan(android.graphics.Typeface.BOLD);
        normal_rating.setSpan(bold_rating,0,8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        tvVoteAverage.setText(normal_rating);

        tvOverview.setText(overview);

        if (TextUtils.isEmpty(overview)) {
            tvOverview.setText("Mohon maaf saat ini kami belum memiliki kilasan singkat " + title + ".");
        }

        Glide.with(DetailMovieActivity.this).load("http://image.tmdb.org/t/p/w185" + poster).into(ivPoster);

    }
}
