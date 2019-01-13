package com.example.danley.sharemanager;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.danley.sharemanager.database.CustomerBaseHelper;
import com.example.danley.sharemanager.database.CustomerCursorWrapper;

import static com.example.danley.sharemanager.database.CustomerDbSchema.*;

// Well made example from the book,  to give a good explaination of a Single and utlizing for SQL!
public class CustomerDataBaseManager {
    private static CustomerDataBaseManager sCustomerDBM;

    private SQLiteDatabase mDatabase;
    private Context mContext;

    private CustomerDataBaseManager(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CustomerBaseHelper(context).getWritableDatabase();
    }

    public static CustomerDataBaseManager get(Context context) {
        if (sCustomerDBM == null) {
            sCustomerDBM = new CustomerDataBaseManager(context);
        }
        return sCustomerDBM;
    }

    public BTreeCustomer getCustomerList() {
        BTreeCustomer customerList = new BTreeCustomer();

        CustomerCursorWrapper cursor = queryCustomers(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                customerList.insertCustomer(cursor.getCustomer());
                cursor.moveToNext();
            }
        }finally {
                cursor.close();;
        }
        return customerList;
    }



     public void addCustomer(Customer customer) {
         ContentValues values = getContentValues(customer);
         mDatabase.insert(CustomerTable.NAME, null, values); // This inserts our values to the Table~

     }

    private static ContentValues getContentValues(Customer customer) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.Cols.ID, customer.getID().toString());
        values.put(CustomerTable.Cols.NAME, customer.getName());
        values.put(CustomerTable.Cols.COMPANY, customer.getCompany());
        values.put(CustomerTable.Cols.DATE, customer.getDate().getTime());
        values.put(CustomerTable.Cols.SHARECOUNT, customer.getShareCount());
        values.put(CustomerTable.Cols.SHAREPRICE, customer.getSharePrice());

        return values;
    }

    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CustomerTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );

        return new CustomerCursorWrapper(cursor);
    }

    public Customer getCustomer(CustomerID id) {
        CustomerCursorWrapper cursor = queryCustomers(
                CustomerTable.Cols.ID + " = ?",
                new String[]{id.toString()} );

         try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getCustomer();
        } finally {
            cursor.close();
        }
    }
}
