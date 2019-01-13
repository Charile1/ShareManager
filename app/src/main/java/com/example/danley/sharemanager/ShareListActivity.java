package com.example.danley.sharemanager;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShareListActivity extends AbstractFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ShareListFragment();
    }
}
