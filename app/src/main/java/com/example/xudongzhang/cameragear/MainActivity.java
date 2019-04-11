package com.example.xudongzhang.cameragear;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        com.example.xudongzhang.camgear.DBAdapter db = new com.example.xudongzhang.camgear.DBAdapter(this);

        db.open();
        long id = db.insertLens("CANON", "Standard Zoom", "24-70", 2.8, 0.38, "EF", "USM", 82);
        db.close();

        db.open();
        Cursor c = db.getLens(1);
        if (c.moveToFirst())
            displayContact(c);
        else
            Toast.makeText(this, "No lens found", Toast.LENGTH_LONG).show();
        db.close();

    }

    public void displayContact(Cursor c) {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Brand Name: " + c.getString(1) + "\n" +
                        "Lens Type:  " + c.getString(2) + "\n" +
                        "Focal Length: " + c.getString(3) + "\n" +
                        "Max Aperture: " + c.getDouble(4) + "\n" +
                        "Closest Focus Distance: " + c.getDouble(5) + "\n" +
                        "Mount:  " + c.getString(6) + "\n" +
                        "Motor Type: " + c.getString(7) + "\n" +
                        "Filter Size: " + c.getDouble(8), Toast.LENGTH_LONG).show();
    }
}
