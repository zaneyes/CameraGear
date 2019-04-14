package com.example.xudongzhang.cameragear;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertLensActivity extends AppCompatActivity {

    private EditText brandInput;
    private EditText lensTypeInput;
    private EditText focalLengthInput;
    private EditText maxApertureInput;
    private EditText cfDistanceInput;
    private EditText mountTypeInput;
    private EditText motorTypeInput;
    private EditText filterSizeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_lens);



//        db.open();
//        long id = db.insertLens("CANON", "Standard Zoom", "24-70", 2.8, 0.38, "EF", "USM", 82);
//        db.close();



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

    public void saveLens (View v) {
        brandInput          = (EditText) findViewById(R.id.editTextBrandName);
        lensTypeInput       = (EditText) findViewById(R.id.editTextLensType);
        focalLengthInput    = (EditText) findViewById(R.id.editTextFocalLength);
        maxApertureInput    = (EditText) findViewById(R.id.editTextMaxAperture);
        cfDistanceInput     = (EditText) findViewById(R.id.editTextCFDistance);
        mountTypeInput      = (EditText) findViewById(R.id.editTextMountType);
        motorTypeInput      = (EditText) findViewById(R.id.editTextMotorType);
        filterSizeInput     = (EditText) findViewById(R.id.editTextFilterSize);

        com.example.xudongzhang.camgear.DBAdapter db = new com.example.xudongzhang.camgear.DBAdapter(this);

        db.open();
        long id = db.insertLens(brandInput.getText().toString(), lensTypeInput.getText().toString(), focalLengthInput.getText().toString(),
                                Double.valueOf(maxApertureInput.getText().toString()), Double.valueOf(cfDistanceInput.getText().toString()),
                                mountTypeInput.getText().toString(), motorTypeInput.getText().toString(),
                                Double.valueOf(filterSizeInput.getText().toString()));
        db.close();



    }
}
