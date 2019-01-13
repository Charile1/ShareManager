package com.example.danley.sharemanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class CustomerActivity extends AbstractFragmentActivity {
    private static final String EXTRA_ID = "customerID@441XFF"; // Just a random ID

    public static Intent newIntent(Context context, CustomerID customerID) {
        Intent intent = new Intent (context, CustomerActivity.class);
        intent.putExtra(EXTRA_ID, customerID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        CustomerID cId = (CustomerID) getIntent().getSerializableExtra(EXTRA_ID);


        return CustomerFragment.newInstance(cId);
    }
}
