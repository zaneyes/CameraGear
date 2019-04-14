package com.example.xudongzhang.camgear;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {

    //Constants for creating and updating the database
    public static final String KEY_ROWID           = "_id";
    public static final String KEY_BRANDNAME       = "brand_name";
    public static final String KEY_TYPE            = "type";
    public static final String KEY_FLENGTH         = "focal_length";
    public static final String KEY_APERTURE        = "max_aperture";
    public static final String KEY_CFDISTANCE      = "closest_focusing_distance";
    public static final String KEY_MOUNT           = "mount";
    public static final String KEY_MOTORTYPE       = "motor_type";
    public static final String KEY_FILTERSIZE      = "filter_size";
    public static final String TAG                 = "DBAdapter";

    public static final String DATABASE_NAME       = "CameraGears";
    public static final String DATABASE_TABLE      = "lenses";
    public static final int    DATABASE_VERSION    = 1;

//    static final String DATABASE_CREATE     =
//            "create table " + DATABASE_TABLE + " (" + KEY_ROWID + " integer primary key autoincrement, "
//            + KEY_BRANDNAME + " text not null, " + KEY_TYPE + " text, " + KEY_FLENGTH + " text not null, "
//            + KEY_APERTURE + " real, " + KEY_CFDISTANCE + " real, " + KEY_MOUNT + " text, " + KEY_MOTORTYPE
//            + " text, " + KEY_FILTERSIZE + " real);";

    static final String DATABASE_CREATE     =
            "create table lenses (_id integer primary key autoincrement, "
                    + "brand_name text not null, type text, focal_length text not null, max_aperture real, closest_focusing_distance real, "
                    + "mount text, motor_type text, filter_size real);";

    final Context context;

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context    = ctx;
        dbHelper        = new DatabaseHelper(context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper
    {
        public DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS lenses");
            onCreate(db);
        }
    }

    //open the database
    public DBAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    //close the database
    public void close() {
        dbHelper.close();
    }

    public long insertLens(String brandName, String type, String focalLength, double maxAperture,
                           double cfDistance, String mount, String motorType, double filterSize) {
        ContentValues initialValue = new ContentValues();
        initialValue.put(KEY_BRANDNAME, brandName);
        initialValue.put(KEY_TYPE, type);
        initialValue.put(KEY_FLENGTH, focalLength);
        initialValue.put(KEY_APERTURE, maxAperture);
        initialValue.put(KEY_CFDISTANCE, cfDistance);
        initialValue.put(KEY_MOUNT, mount);
        initialValue.put(KEY_MOTORTYPE, motorType);
        initialValue.put(KEY_FILTERSIZE, filterSize);

        return db.insert(DATABASE_TABLE, null, initialValue);
    }

    public boolean deleteLens(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor getAllLenses() {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_BRANDNAME, KEY_TYPE, KEY_FLENGTH, KEY_APERTURE, KEY_CFDISTANCE,
                KEY_MOUNT, KEY_MOTORTYPE, KEY_FILTERSIZE}, null, null, null, null, null);
    }

    public Cursor getLens(long rowId) throws SQLException {
        Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_BRANDNAME, KEY_TYPE, KEY_FLENGTH, KEY_APERTURE, KEY_CFDISTANCE,
                        KEY_MOUNT, KEY_MOTORTYPE, KEY_FILTERSIZE}, KEY_ROWID + "=" + rowId,
                null, null, null, null, null);
        if(mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Update
    public boolean updateLens(long rowId, String brandName, String type, String focalLength, double maxAperture,
                              double cfDistance, String mount, String motorType, double filterSize) {
        ContentValues args = new ContentValues();
        args.put(KEY_BRANDNAME, brandName);
        args.put(KEY_TYPE, type);
        args.put(KEY_FLENGTH, focalLength);
        args.put(KEY_APERTURE, maxAperture);
        args.put(KEY_CFDISTANCE, cfDistance);
        args.put(KEY_MOUNT, mount);
        args.put(KEY_MOTORTYPE, motorType);
        args.put(KEY_FILTERSIZE, filterSize);

        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }


}
