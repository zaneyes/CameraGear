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

        if(brandInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the brand name of the lens", Toast.LENGTH_SHORT).show();
            return;
        }else if(lensTypeInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the type of the lens", Toast.LENGTH_SHORT).show();
        }else if(focalLengthInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the focal length of the lens", Toast.LENGTH_SHORT).show();
        }else if(maxApertureInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the max aperture of the lens", Toast.LENGTH_SHORT).show();
        }else if(cfDistanceInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the closest focus distance of the lens", Toast.LENGTH_SHORT).show();
        }else if(mountTypeInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the mount type of the lens", Toast.LENGTH_SHORT).show();
        }else if(motorTypeInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the motor type of the lens", Toast.LENGTH_SHORT).show();
        }else if(filterSizeInput.getText().toString().matches("")) {
            Toast.makeText(this, "You didn't enter the filter size of the lens", Toast.LENGTH_SHORT).show();
        }else {

            com.example.xudongzhang.camgear.DBAdapter db = new com.example.xudongzhang.camgear.DBAdapter(this);

            db.open();
            long id = db.insertLens(brandInput.getText().toString(), lensTypeInput.getText().toString(), focalLengthInput.getText().toString(),
                    Double.valueOf(maxApertureInput.getText().toString()), Double.valueOf(cfDistanceInput.getText().toString()),
                    mountTypeInput.getText().toString(), motorTypeInput.getText().toString(),
                    Double.valueOf(filterSizeInput.getText().toString()));
            db.close();

            Toast.makeText(this, "Save Successful.", Toast.LENGTH_LONG).show();

            brandInput.getText().clear();
            lensTypeInput.getText().clear();
            focalLengthInput.getText().clear();
            maxApertureInput.getText().clear();
            cfDistanceInput.getText().clear();
            mountTypeInput.getText().clear();
            motorTypeInput.getText().clear();
            filterSizeInput.getText().clear();
        }




    }
}
