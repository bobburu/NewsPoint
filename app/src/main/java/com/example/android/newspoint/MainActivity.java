package com.example.android.newspoint;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import adapters.NewsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Find tool bar and set up as Action bar */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** Find tablayout and viewpager
         *  Create new instance of NewsPagerAdapter
         *  Set up viewpager with adapter
         *  Set up tablayout with view pager
         */
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        NewsPagerAdapter newsPagerAdapter = new NewsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(newsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
