package com.example.xudongzhang.cameragear;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;

public class CursorAdapter extends ResourceCursorAdapter {
    public CursorAdapter (Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
