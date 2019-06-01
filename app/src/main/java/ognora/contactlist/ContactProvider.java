package ognora.contactlist;

import android.content.ContentProvider;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class ContactProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        return false;
    }

    @NonNull
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }
}
