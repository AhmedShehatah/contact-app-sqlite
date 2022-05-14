package com.amany.contactbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amany.contactbook.model.ContactModel;

import java.util.ArrayList;

public class DBSqlite extends SQLiteOpenHelper {

    private static final String DBName = "database.db";

    public DBSqlite(@Nullable Context context) {
        super(context, DBName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT, phone TEXT,notes TEXT,email TEXT,token TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);

    }

    public boolean insertContact(ContactModel contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("email", contact.getEmail());
        values.put("phone", contact.getPhone());
        values.put("notes", contact.getNotes());
        values.put("token", contact.getToken());
        long result = db.insert("contacts", null, values);
        return result != -1;

    }

    public ArrayList<ContactModel> getAllContacts() {
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contacts", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String token = cursor.getString(5);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String notes = cursor.getString(3);
            String email = cursor.getString(4);
            arrayList.add(new ContactModel(token, name, email, notes, phone));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public boolean updateContact(ContactModel contact) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("email", contact.getEmail());
        values.put("phone", contact.getPhone());
        values.put("notes", contact.getNotes());
        long result = db.update("contacts", values, "token=?", new String[]{contact.getToken()});
        return result != -1;
    }

    public boolean delete(String token) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts", "token=?", new String[]{token}) == 1;
    }
}
