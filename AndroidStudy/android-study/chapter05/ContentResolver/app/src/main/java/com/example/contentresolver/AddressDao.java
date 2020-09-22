package com.example.contentresolver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    private Context context;

    private final String INFO_URI = "content://com.example.contentprovider/information";

    public AddressDao(Context context) {
        this.context = context;
    }

    public int insert(Address address) {
        Uri uri = Uri.parse(INFO_URI);
        ContentValues values = new ContentValues();
        values.put("name", address.getName());
        values.put("phone", address.getPhone());

        Uri newUri = this.context.getContentResolver().insert(uri, values);
        if(newUri != null) {
            return Integer.parseInt(newUri.getPathSegments().get(1));
        }
        return 0;
    }

    public boolean update(Address address) {
        Uri uri = Uri.parse(INFO_URI + "/" + address.get_id());
        ContentValues values = new ContentValues();
        values.put("name", address.getName());
        values.put("phone", address.getPhone());

        long num = this.context.getContentResolver().update(uri, values, null, null);
        return num > 0;
    }

    public boolean delete(int _id) {
        Uri uri = Uri.parse(INFO_URI + "/" + _id);
        long num = this.context.getContentResolver().delete(uri, null, null);
        return num > 0;
    }


    public List<Address> query() {
        List<Address> addresses = new ArrayList<>();
        Uri uri = Uri.parse(INFO_URI);
        Cursor cursor = this.context.getContentResolver().query(uri, null, null, null, null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                addresses.add(new Address(_id, name, phone));
            }
            cursor.close();
        }
        return addresses;
    }
}
