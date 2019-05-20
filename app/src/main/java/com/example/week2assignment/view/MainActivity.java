package com.example.week2assignment.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.week2assignment.R;

public class MainActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //binding
        viewPager = findViewById(R.id.viewpager_frags);
        tabLayout = findViewById(R.id.tabl_song_genre);

        adapter = new TabAdapter(getSupportFragmentManager());

        //Setting up the tags for the tablayout
        adapter.addFragment(new RockFragment(),"Rock");
        adapter.addFragment(new ClassicFragment(),"Classic");
        adapter.addFragment(new PopFragment(),"Pop");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //set up all the icons
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_rock_blue);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_classic_black);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_pop_black);

        setSelectedListener();
    }



    private void setSelectedListener() {

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //change icon on selected
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_rock_blue);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_classic_blue);
                        break;

                    case 2:
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_pop_blue);
                        break;
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //change icon on usselected
                switch (tab.getPosition()){
                    case 0:
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_rock_black);
                        break;
                    case 1:
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_classic_black);
                        break;

                    case 2:
                        tabLayout.getTabAt(2).setIcon(R.drawable.ic_pop_black);
                        break;
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
