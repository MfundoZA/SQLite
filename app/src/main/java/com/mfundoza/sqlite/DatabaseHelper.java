package com.mfundoza.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "customer.db";
    public static final String CUSTOMER_TABLE = "customer_table";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * This is called the first time a database is accessed. There should be code in here to create
     * a new database.
      */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    /**
     * This is called if the database version number changes. It prevents previous users apps from
     * breaking when you change the database design.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel) {
        // SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DATABASE_NAME, null);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        values.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        values.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActiveCustomer());

        long insert = db.insert(CUSTOMER_TABLE, null, values);
        db.close();

        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<CustomerModel> getAllCustomers() {
        List<CustomerModel> customers = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // If cursor is not empty then loop through each record and create a CustomerModel
            // object before adding that object to the list
            do {
                int customerId = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true : false;

                CustomerModel newCustomer = new CustomerModel(customerId, customerName, customerAge, customerActive);
                customers.add(newCustomer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return customers;
    }

    public boolean deleteCustomer(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " +
                customerModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }
}
