package com.finalwork.android.e_commerce.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.finalwork.android.e_commerce.R;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentComment;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentCommentNull;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentPay;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentPayNull;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentReceive;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentReceiveNull;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentReturn;
import com.finalwork.android.e_commerce.view.fragment.fragmentpending.FragmentReturnNull;

public class PendingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);
        int id = getIntent().getIntExtra("id", 0);
        switch (id){
            case 3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentPay())
                        .commit();
                break;
            case -3:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentPayNull())
                        .commit();
                break;
            case 4:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentReceive())
                        .commit();
                break;
            case -4:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentReceiveNull())
                        .commit();
                break;
            case 5:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentComment())
                        .commit();
                break;
            case -5:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentCommentNull())
                        .commit();
                break;
            case 6:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentReturn())
                        .commit();
                break;
            case -6:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pendingfragment_container, new FragmentReturnNull())
                        .commit();
                break;
        }
    }
}
