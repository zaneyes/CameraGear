package com.example.xudongzhang.cameragear;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xudongzhang.camgear.DBAdapter;

import java.util.ArrayList;

public class ViewLensesActivity extends AppCompatActivity {

    private TextView lensLabel;
    private EditText idDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lenses);

        idDelete = (EditText) findViewById(R.id.editTextDelete);


        com.example.xudongzhang.camgear.DBAdapter db = new com.example.xudongzhang.camgear.DBAdapter(this);

        db.open();
        Cursor c = db.getAllLenses();
        if (c.moveToFirst())
            displayLenses(c);
        else
            Toast.makeText(this, "No lens found", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void deleteById(View view) {

        if(idDelete.getText().toString().matches("")) {
            Toast.makeText(this, "Please enter an id number for deletion", Toast.LENGTH_LONG).show();
        }else {

            com.example.xudongzhang.camgear.DBAdapter db = new com.example.xudongzhang.camgear.DBAdapter(this);

            db.open();
            if (db.deleteLens(Long.parseLong(idDelete.getText().toString())))
                Toast.makeText(this, "Delete Successful.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
            db.close();


            idDelete.getText().clear();
        }

    }

    public void displayLenses(Cursor c) {

//        com.example.xudongzhang.camgear.DBAdapter db = new DBAdapter(this);
//
//        db.open();
//        Cursor cursor = db.getAllLenses();
//        String[] fromFieldNames = new String[] {DBAdapter.KEY_ROWID, DBAdapter.KEY_BRANDNAME};
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listViewLens, StringArray);
//
//        lensLabel = (ListView) findViewById(R.id.listViewLens);
//        lensLabel.setAdapter(adapter);
//

          lensLabel = (TextView) findViewById(R.id.textViewLens);
          lensLabel.setMovementMethod(new ScrollingMovementMethod());
          String newLine = System.getProperty("line.separator");

          ArrayList<String> allLensesArrayList = new ArrayList<String>();


          for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
              allLensesArrayList.add("------------------------------------------id: " + c.getString(0) + "------------------------------------------\n");
              allLensesArrayList.add("Brand Name: " + c.getString(1) + "\n");
              allLensesArrayList.add("Lens Type:  " + c.getString(2) + "\n");
              allLensesArrayList.add("Focal Length: " + c.getString(3) + "\n");
              allLensesArrayList.add("Max Aperture: " + c.getDouble(4) + "\n");
              allLensesArrayList.add("Closest Focus Distance: " + c.getDouble(5) + "\n");
              allLensesArrayList.add("Mount:  " + c.getString(6) + "\n");
              allLensesArrayList.add("Motor Type: " + c.getString(7) + "\n");
              allLensesArrayList.add("Filter Size: " + c.getDouble(8));
          }

          for (int i = 0; i < allLensesArrayList.size(); i++) {
              lensLabel.append(newLine + allLensesArrayList.get(i).toString());
          }

    }
}
