package com.example.carserviceapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.carserviceapp.R;
import com.example.carserviceapp.data.ViewerPagerAdapter;
import com.example.carserviceapp.fragments.ClientLoginFragment;
import com.example.carserviceapp.fragments.DriverLoginFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBarID);
        tabLayout = findViewById(R.id.tabLayoutID);
        viewPager = findViewById(R.id.view_pager_id);


        getSupportFragmentManager().beginTransaction().replace(R.id.view_pager_id,
                new ClientLoginFragment()).commit();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager){
        ViewerPagerAdapter viewerPagerAdapter = new ViewerPagerAdapter(this.
                getSupportFragmentManager());

        viewerPagerAdapter.addFragment(new ClientLoginFragment(),"Client Login");
        viewerPagerAdapter.addFragment(new DriverLoginFragment(),"Driver Login");


        viewPager.setAdapter(viewerPagerAdapter);
    }


}