package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
    Handler (управлять) класс будет управляющим базой данных
 */
public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
