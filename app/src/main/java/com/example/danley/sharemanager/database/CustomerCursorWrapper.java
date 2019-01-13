package com.example.danley.sharemanager.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.danley.sharemanager.Customer;
import com.example.danley.sharemanager.CustomerID;
import com.example.danley.sharemanager.database.CustomerDbSchema.CustomerTable;

import java.util.Date;

public class CustomerCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Customer getCustomer() {
        String id = getString(getColumnIndex(CustomerTable.Cols.ID));
        String name = getString(getColumnIndex(CustomerTable.Cols.NAME));
        String company = getString(getColumnIndex(CustomerTable.Cols.COMPANY));
        long date = getLong(getColumnIndex(CustomerTable.Cols.DATE));
        int shareCount = getInt(getColumnIndex(CustomerTable.Cols.SHARECOUNT));
        float share = getFloat(getColumnIndex(CustomerTable.Cols.SHAREPRICE));

        Customer customer = new Customer();
        customer.setID((new CustomerID(id)));
        customer.setName(name);
        customer.setCompany(company);
        customer.setDate(new Date(date));
        customer.setShareCount(shareCount);
        customer.setSharePrice(share);

        return customer;
    }
}
