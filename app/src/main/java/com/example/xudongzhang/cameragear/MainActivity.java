package com.example.xudongzhang.cameragear;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void insertLens(final View view) {

        final Intent intent;

        intent = new Intent(this, InsertLensActivity.class);
        startActivity(intent);

    }

    public void viewLenses(final View view) {
        final Intent intent;
        intent = new Intent(this, ViewLensesActivity.class);
        startActivity(intent);
    }
}
