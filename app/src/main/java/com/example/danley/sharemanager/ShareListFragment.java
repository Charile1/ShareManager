package com.example.danley.sharemanager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShareListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ShareAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateAdapter();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateAdapter();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_share_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_customer:
                Customer customer = new Customer();
                List<String> cd = CustomerDataBaseManager.get(getActivity()).getCustomerList().getIDList();

                for (int i = 0; i < cd.size(); i++) {
                    if (cd.get(i).equals(customer.getID()))
                    {
                        customer.setID(customer.randomID());
                        i = 0;
                    }
                }

                //CustomerDataBaseManager.get(getActivity()).addCustomer(customer);
                Intent intent = AddCustomerActivity.newIntent(getActivity(), customer);
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateAdapter() {
        CustomerDataBaseManager dataBaseManager = CustomerDataBaseManager.get(getActivity());
        BTreeCustomer customerList = dataBaseManager.getCustomerList();


        if (mAdapter == null) {
            mAdapter = new ShareAdapter(customerList);
            mRecyclerView.setAdapter(mAdapter);
        } else
        {
            mAdapter.setCustomers(customerList);
            mAdapter.notifyDataSetChanged();
        }

    }


    /*
    * ShareHolder - Used for the Holder, for the adapter
    * Contains the TextViews from our share_list
    *
    * */
    private class ShareHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCustomerNameView;
        private TextView mCustomerCompanyView;
        private Customer mCustomer;


        public ShareHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_customers, parent, false));
            itemView.setOnClickListener(this);
            mCustomerNameView = (TextView) itemView.findViewById(R.id.customer_name);
            mCustomerCompanyView = (TextView) itemView.findViewById(R.id.customer_company);

        }

        public void bind(Customer customer) {
            mCustomer = customer;
            mCustomerNameView.setText(mCustomer.getName());
            mCustomerCompanyView.setText(mCustomer.getCompany());
        }

        @Override
        public void onClick(View v) {
            Intent intent = PagerActivity.newIntent(getActivity(), mCustomer.getID());
            startActivity(intent);
        }
    }

    private class ShareAdapter extends RecyclerView.Adapter<ShareHolder> {
        private BTreeCustomer mCustomerList;
        private List<String> mCustomersIDs = new ArrayList<>();

        public ShareAdapter(BTreeCustomer customerList) {
            mCustomerList = customerList;
            mCustomersIDs = mCustomerList.getIDList();

        }


        @Override
        public ShareHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ShareHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder( ShareHolder shareHolder, int i) {
            // Still a bit questionable...
            Customer customer = mCustomerList.find( mCustomerList.getIDList().get(i));
            shareHolder.bind(customer);
        }

        @Override
        public int getItemCount() {
            return mCustomerList.size();
        }

        public void setCustomers(BTreeCustomer customerList) {
            mCustomerList = customerList;
        }
    }
}
