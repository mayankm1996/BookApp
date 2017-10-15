package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes/?q=";

    private static final int BOOK_LOADER_ID = 1;
    private BookAdapter mAdapter;

    private TextView mEmptyTextView;
    String searchString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView booklistView = (ListView) findViewById(R.id.list);
        mEmptyTextView = (TextView) findViewById(R.id.empty_view);
        booklistView.setEmptyView(mEmptyTextView);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        booklistView.setAdapter(mAdapter);
        ConnectivityManager conmg = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conmg.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            View loadingbar = findViewById(R.id.loading_indicator);
            loadingbar.setVisibility(View.GONE);
            mEmptyTextView.setText("no connection");
        }
    }
    public void submit(View view) {
        EditText text = (EditText) findViewById(R.id.edit_text);
        searchString = text.getText().toString();

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(BOOK_LOADER_ID, null, this);
    }
    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, REQUEST_URL + searchString);
    }
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        View loadingbar = findViewById(R.id.loading_indicator);
        loadingbar.setVisibility(View.GONE);
        mEmptyTextView.setText("ENTER QUERY IN FIELD TO GET THE RESULTS OF BOOKS AND THEIR AUTHORS");
        mAdapter.clear();
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}