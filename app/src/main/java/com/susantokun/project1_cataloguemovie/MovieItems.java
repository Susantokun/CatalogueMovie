package com.susantokun.project1_cataloguemovie;

import org.json.JSONObject;

import java.text.DecimalFormat;

public class MovieItems {
    private int id;
    private String poster;
    private String title;
    private String releaseDate;
    private String vote_count;
    private String vote_average;
    private String overview;



    public MovieItems(JSONObject object){
        try {
            int id = object.getInt("id");
            String poster = object.getString("poster_path");
            String title = object.getString("title");
            String releaseDate = object.getString("release_date");
            String vote_count = object.getString("vote_count");
            Double vote_average = object.getDouble("vote_average");
            String overview = object.getString("overview");

            double hasil_rating = vote_average * 10;
            String rating = new DecimalFormat("##.##").format(hasil_rating);

            this.id = id;
            this.poster = poster;
            this.title = title;
            this.releaseDate = releaseDate;
            this.vote_count = vote_count;
            this.vote_average = rating;
            this.overview = overview;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getVoteCount() {
        return vote_count;
    }

    public String getVoteAverage() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }
}
