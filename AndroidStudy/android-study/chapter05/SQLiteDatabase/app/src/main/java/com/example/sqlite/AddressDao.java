package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public AddressDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Address address) {
        int newId = 0;
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", address.getName());
        values.put("phone", address.getPhone());

        long num = db.insert("information", null, values);
        if(num > 0) {
            Cursor cursor = db.rawQuery("select last_insert_rowid() from address", null);
            if(cursor != null && cursor.moveToFirst()) {
                 newId = cursor.getInt(0);
            }
        }
        db.close();
        return newId;
    }

    public boolean update(Address address) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", address.getName());
        values.put("phone", address.getPhone());

        long num = db.update("information", values, "_id=?", new String[] {String.valueOf(address.get_id())});
        db.close();
        return num > 0;
    }

    public boolean delete(int _id) {
        db = dbHelper.getWritableDatabase();
        long num = db.delete("information", "_id=?", new String[]{String.valueOf(_id)});
        db.close();
        return num > 0;
    }


    public List<Address> query() {
        List<Address> addresses = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("information", null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            addresses.add(new Address(_id, name, phone));
        }
        cursor.close();
        db.close();
        return addresses;
    }
}
