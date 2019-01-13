package com.uwaterlooplus.uwaterlooplus;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.uwaterlooplus.uwaterlooplus.Fragment.CalendarFragment;
import com.uwaterlooplus.uwaterlooplus.Fragment.CharacterFragment;
import com.uwaterlooplus.uwaterlooplus.Fragment.MapFragment;
import com.uwaterlooplus.uwaterlooplus.Fragment.NewAdventureFragment;


public class MainActivity extends AppCompatActivity implements CalendarFragment.OnFragmentInteractionListener,MapFragment.OnFragmentInteractionListener,NewAdventureFragment.OnFragmentInteractionListener,CharacterFragment.OnFragmentInteractionListener {

//    private TextView mTextMessage;

    private CalendarFragment calendarFragment;
    private CharacterFragment characterFragment;
    private MapFragment mapFragment;
    private NewAdventureFragment newAdventureFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_calendar:
//                    mTextMessage.setText(R.string.title_calendar);
                    loadFragment(calendarFragment);
                    return true;
                case R.id.navigation_map:
//                    mTextMessage.setText(R.string.title_map);
                    loadFragment(mapFragment);
                    return true;
                case R.id.navigation_newAdventure:
//                    mTextMessage.setText(R.string.title_newAdventure);
                    loadFragment(newAdventureFragment);
                    return true;
                case R.id.navigation_character:
//                    mTextMessage.setText(R.string.tittle_character);
                    loadFragment(characterFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.tv_1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        Make instances for every fragment
        calendarFragment = new CalendarFragment();
        characterFragment = new CharacterFragment();
        mapFragment = new MapFragment();
        newAdventureFragment = new NewAdventureFragment();
    }

    private void loadFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fl_container,fragment).addToBackStack(null).commit();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fl_container, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
