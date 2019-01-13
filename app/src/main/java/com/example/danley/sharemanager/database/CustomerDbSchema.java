package com.example.danley.sharemanager.database;

public class CustomerDbSchema {
    public static final class CustomerTable {
        public static final String NAME = "customers";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String COMPANY = "company";
            public static final String SHARECOUNT= "sharecount";
            public static final String SHAREPRICE= "shareprice";
            public static final String DATE = "date";
        }
    }
}

/*
*     private CustomerID ID;
    private String mName;
    private String mCompany;
    private int mShareCount;
    private float mSharePrice;
    private Date mDate;
*
*
* */
