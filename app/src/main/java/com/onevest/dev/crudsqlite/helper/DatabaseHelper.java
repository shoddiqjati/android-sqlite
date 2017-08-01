package com.onevest.dev.crudsqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.onevest.dev.crudsqlite.model.Contact;
import com.onevest.dev.crudsqlite.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    private Context context;
    protected static final String TAG = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_INIT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " +
                Constants.TB_NAME + "(" + Constants.col_id + " INTEGER PRIMARY KEY, " +
                Constants.col_name + " TEXT, " + Constants.col_phone + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
        Log.d(TAG, Constants.TB_NAME + "successfully created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXIST " + Constants.TB_NAME);
        onCreate(sqLiteDatabase);
        Log.d(TAG, Constants.TB_NAME + "is updated");
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.col_id, contact.getId());
        values.put(Constants.col_name, contact.getName());
        values.put(Constants.col_phone, contact.getPhone());

        try {
            db.insert(Constants.TB_NAME, null, values);
            Log.d(TAG, contact.getName() + "successfully added");
        } catch (RuntimeException e) {
            Log.d(TAG, "Failed to add" + contact.getName());
        }
    }

    public List<Contact> getAllContact() {
        SQLiteDatabase db = getReadableDatabase();
        Contact contact;
        String allQuery = "SELECT * FROM " + Constants.TB_NAME;
        List<Contact> contactList = new ArrayList<>();
        Cursor cursor = db.rawQuery(allQuery, null);
        while (cursor.moveToNext()) {
            contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
            contactList.add(contact);
        }
        cursor.close();
        if (contactList.size() != 0) {
            Log.d(TAG, "Successfully get All Contact");
            return contactList;
        } else {
            Log.d(TAG, "No record in the database");
            return null;
        }

    }

    public void deleteContact(int id) {
        SQLiteDatabase db = getReadableDatabase();
        boolean deleteStatus = db.delete(Constants.TB_NAME, Constants.col_id, new String[]{String.valueOf(id)}) > 0;
        if (deleteStatus) {
            Log.d(TAG, "Record with id = " + id + " is successfully deleted");
        } else {
            Log.d(TAG, "Failed to delete" + id);
        }


    }
}
