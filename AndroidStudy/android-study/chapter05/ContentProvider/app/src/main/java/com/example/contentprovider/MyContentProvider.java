package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;

public class MyContentProvider extends ContentProvider {
    public final static String AUTHORITY = "com.example.contentprovider";
    public final static int INFO_DIR = 0;
    public final static int INFO_ITEM = 1;

    private SQLiteDatabase db;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "information", INFO_DIR);
        uriMatcher.addURI(AUTHORITY, "information/#", INFO_ITEM);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        DBHelper helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        return db != null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case INFO_DIR:
                count = db.delete("information", selection, selectionArgs);
                break;
            case INFO_ITEM:
                String id = uri.getPathSegments().get(1);
                count = db.delete("information", "_id=?", new String[]{id});
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        // 提示数据库的内容发生了变化
        if(count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        Uri newUri = null;
        long newId = 0;

        switch (uriMatcher.match(uri)) {
            case INFO_DIR:
            case INFO_ITEM:
                newId = db.insert("information", "", values);
                if (newId > 0) {
                    newUri = ContentUris.withAppendedId(uri, newId);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        // 提示数据库的内容发生了变化
        if(newId > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return newUri;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case INFO_DIR:
                cursor = db.query("information", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INFO_ITEM:
                String id = uri.getPathSegments().get(1);
                cursor = db.query("information", projection, "_id=?", new String[]{id}, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        return cursor;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case INFO_DIR:
                count = db.update("information", values, selection, selectionArgs);
                break;
            case INFO_ITEM:
                String id = uri.getPathSegments().get(1);
                count = db.update("information", values, "_id=?", new String[]{id});
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        // 提示数据库的内容发生了变化
        if(count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case INFO_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentprovider.information";
            case INFO_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contentprovider.information";
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
