package bso.android.simplemovieapps.koneksi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fadil on 3/27/18.
 */

public class KoneksiDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Movie.db";
    public static final String TABLE_NAME = "DataMovie";

    public static final String colom1 = "ID";
    public static final String colom2 = "title";
    public static final String colom3 = "genre";
    public static final String colom4 = "year";

    public KoneksiDatabase(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME +" " +
                "(ID INTEGER PRIMARY KEY ," +
                " title TEXT," +
                " genre TEXT ," +
                " year TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
    public Cursor TampilData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean MasukanData(String id,String title,String genre, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues konten = new ContentValues();
        konten.put(colom1,id);
        konten.put(colom2,title);
        konten.put(colom3,genre);
        konten.put(colom4,year);


        long hasil= db.insert(TABLE_NAME,null,konten);
        db.close();

        if (hasil== -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean EditData(String id,String title,String genre, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues konten = new ContentValues();
        konten.put(colom2,title);
        konten.put(colom3,genre);
        konten.put(colom4,year);

        int hasil= db.update(TABLE_NAME,konten,"ID =? ",new String[]{id});
        if (hasil>0){
            return true;
        }
        else {
            return false;
        }
    }
    public Integer DeleteData ( String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABLE_NAME,"ID=? ", new String[]{id});
        return i;
    }
}
