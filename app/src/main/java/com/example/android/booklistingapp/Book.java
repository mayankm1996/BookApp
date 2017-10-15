package com.example.android.booklistingapp;

/**
 * Created by Mayank on 16-03-2017.
 */

public class Book {

    private String mAuthor;
    private String mTitle;

    public Book(String author, String title) {
        mAuthor = author;
        mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

}