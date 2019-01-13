package com.example.danley.sharemanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class PagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private BTreeCustomer customerList;
    private List<String> customerIDList;
    private static final String EXTRA_CUSTOMER_ID = "customerid@42135"; // Some random ID I put.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        final CustomerID customerID = (CustomerID) getIntent().getSerializableExtra(EXTRA_CUSTOMER_ID);
        mViewPager = (ViewPager) findViewById(R.id.share_view_pager);
        customerList = CustomerDataBaseManager.get(this).getCustomerList();
        customerIDList = customerList.getIDList();
        FragmentManager fragManger = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentPagerAdapter(fragManger) {
            @Override
            public Fragment getItem(int i) {
                Customer customer = customerList.find(customerIDList.get(i));
                return CustomerFragment.newInstance(customer.getID());
            }

            @Override
            public int getCount() {
                return customerList.size();
            }
        });

        for (int i = 0; i < customerIDList.size(); i++ ) {
            if(customerID.toString().equals(customerIDList.get(i))) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context context, CustomerID customer) {
        Intent intent = new Intent(context, PagerActivity.class);
        intent.putExtra(EXTRA_CUSTOMER_ID, customer);
        return intent;
    }
}
