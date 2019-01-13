package com.example.danley.sharemanager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddCustomerActivity extends AppCompatActivity {
    private static final String ARG_NEW_CUSTOMER = "NCNID123";

    Customer mCustomer;

    EditText nameEditText, companyEditText, sharesAmountEditText, sharePriceEditText;
    Button mDoneButton, mDateButton;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        nameEditText = (EditText) findViewById(R.id.edit_cust_name);
        companyEditText = (EditText) findViewById(R.id.edit_cust_companyname);
        sharesAmountEditText = (EditText)findViewById(R.id.edit_cust_shares);
        sharePriceEditText = (EditText)findViewById(R.id.edit_cust_shareprice);
        mDateButton = (Button)findViewById(R.id.dateButton);
        mDoneButton = (Button)findViewById(R.id.closeButton);

        mCustomer = (Customer) getIntent().getSerializableExtra(ARG_NEW_CUSTOMER);

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddCustomerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                mDateButton.setText(String.format("Purchure Date:%d/%d/%d", mMonth, mDay, mYear));
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // We're gonna need to
                String s, s2;

                s = nameEditText.getText().toString();
                if (nameEditText.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Input is not completed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (companyEditText.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Input is not completed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sharesAmountEditText.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Input is not completed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sharePriceEditText.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Input is not completed", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (((String)mDateButton.getText()).toLowerCase() == ("Enter Date").toLowerCase()) {
                    Toast.makeText(getApplicationContext(), "Input is not completed", Toast.LENGTH_SHORT).show();
                    return;
                }
                mCustomer.setName(nameEditText.getText().toString());
                mCustomer.setCompany(companyEditText.getText().toString());
                mCustomer.setShareCount(Integer.parseInt(sharesAmountEditText.getText().toString()));
                mCustomer.setSharePrice(Float.parseFloat(sharePriceEditText.getText().toString()));
                mCustomer.setDate(new Date(datePickerDialog.getDatePicker().getMaxDate()));

                CustomerDataBaseManager.get(AddCustomerActivity.this).addCustomer(mCustomer);
                finish(); // finish when done
            }
        });

    }



    @Override
    protected void onPause() {
        // must do it must do

        super.onPause();
        // also must check before ending
    }

    public static Intent newIntent(Context context, Customer customer) {
        Intent intent = new Intent(context, AddCustomerActivity.class);
        intent.putExtra(ARG_NEW_CUSTOMER, customer);
        return intent;
    }
}
