package com.example.danley.sharemanager;

import java.io.Serializable;
import java.util.Date;

public class Customer implements Serializable {
    private CustomerID ID;
    private String mName;
    private String mCompany;
    private int mShareCount;
    private float mSharePrice;
    private Date mDate;

    public Customer() {
        String lel = CustomerID.randomID();
        ID = new CustomerID(lel);
    }

    public static CustomerID randomID() {
        return new CustomerID(CustomerID.randomID());
    }

    public String getName() { return mName; }
    public void setName(String newName) { mName = newName; }

    public String getCompany() { return mCompany; }
    public void setCompany(String newCompanyName) { mCompany = newCompanyName; }

    public int getShareCount() { return mShareCount; }
    public void setShareCount( int newShareCount) { mShareCount = newShareCount; }

    public float getSharePrice() { return mSharePrice; }
    public void setSharePrice(float newSharePrice) { mSharePrice = newSharePrice; }

    public CustomerID getID() {return ID; };
    public void setID(CustomerID newID) { ID = newID; };

    public float getTotalOfShares() { return (float)(mSharePrice * mShareCount); }

    public Date getDate() { return mDate; }
    public void setDate(Date newDate) { mDate = newDate; }




}
