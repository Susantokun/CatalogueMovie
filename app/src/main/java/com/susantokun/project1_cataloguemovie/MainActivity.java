package com.susantokun.project1_cataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {
    ListView listView;
    MovieAdapter adapter;
    EditText edtCari;
    Button btnCari;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();

        listView = (ListView)findViewById(R.id.lv_movie);
        listView.setAdapter(adapter);

        edtCari = (EditText)findViewById(R.id.edt_cari);

        btnCari = (Button)findViewById(R.id.btn_cari);
        btnCari.setOnClickListener(myListener);

        String dataMovie = edtCari.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, dataMovie);

        getLoaderManager().restartLoader(0, bundle, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItems item = (MovieItems)parent.getItemAtPosition(position);

                Log.d("Mov", "onItemClick");

                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);

                intent.putExtra(DetailMovieActivity.EXTRA_TITLE, item.getTitle());
                intent.putExtra(DetailMovieActivity.EXTRA_OVERVIEW, item.getOverview());
                intent.putExtra(DetailMovieActivity.EXTRA_RELEASE_DATE, item.getReleaseDate());
                intent.putExtra(DetailMovieActivity.EXTRA_POSTER, item.getPoster());
                intent.putExtra(DetailMovieActivity.EXTRA_VOTE_COUNT, item.getVoteCount());
                intent.putExtra(DetailMovieActivity.EXTRA_VOTE_AVERAGE, item.getVoteAverage());

                startActivity(intent);

            }
        });

    }

    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args){
        String movie = "";
        if (args != null){
            movie = args.getString(EXTRAS_MOVIE);
        }
        return new MovieAsyncTaskLoader(this, movie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data){
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader){
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            String dataMovie = edtCari.getText().toString();
            if (TextUtils.isEmpty(dataMovie)) return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, dataMovie);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };

}
