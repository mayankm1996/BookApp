package com.example.android.booklistingapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mayank on 16-03-2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list, parent, false);
        }
        Book currentBook = getItem(position);

        TextView titletext = (TextView) listItemView.findViewById(R.id.title);
        TextView authortext = (TextView) listItemView.findViewById(R.id.author);
        titletext.setText(currentBook.getTitle());
        authortext.setText(currentBook.getAuthor());
        return listItemView;
    }
}
