package com.example.danley.sharemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CustomerFragment extends Fragment {
    private static final String ARG_CUSTOMER_ID = "customer_id";

    private Customer mCustomer;
    private TextView nameTextView;
    private TextView companyTextView;
    private TextView shareAmountTextView;
    private TextView sharePriceTextView;
    private TextView totalTextView;
    private TextView dateTextView;
    private TextView idTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomerID cId = (CustomerID) getArguments().getSerializable(ARG_CUSTOMER_ID);
        mCustomer = CustomerDataBaseManager.get(getActivity()).getCustomer(cId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container, false);

        nameTextView = (TextView) v.findViewById(R.id.text_cust_name);
        nameTextView.setText(mCustomer.getName());

        companyTextView = (TextView) v.findViewById(R.id.text_cust_companyname);
        companyTextView.setText(mCustomer.getCompany());

        shareAmountTextView = (TextView) v.findViewById(R.id.text_cust_shares);
        shareAmountTextView.setText(String.valueOf(mCustomer.getShareCount()));

        sharePriceTextView = (TextView) v.findViewById(R.id.text_cust_amount);
        sharePriceTextView.setText("$" + mCustomer.getSharePrice());

        totalTextView = (TextView) v.findViewById(R.id.text_cust_total);
        totalTextView.setText(String.format(" $%.2f", mCustomer.getTotalOfShares()));

        dateTextView = (TextView) v.findViewById(R.id.text_cust_date);
        dateTextView.setText(mCustomer.getDate().toString());

        idTextView = (TextView) v.findViewById(R.id.text_cust_id);
        idTextView.setText(mCustomer.getID().toString());

        return v;
    }

    public static CustomerFragment newInstance(CustomerID cId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CUSTOMER_ID, cId);
        CustomerFragment fragment = new CustomerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
