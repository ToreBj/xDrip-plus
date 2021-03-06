package com.eveningoutpost.dexdrip.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.eveningoutpost.dexdrip.Home;
import com.eveningoutpost.dexdrip.NavDrawerBuilder;
import com.eveningoutpost.dexdrip.NavigationDrawerFragment;
import com.eveningoutpost.dexdrip.R;

import java.util.List;

/**
 * Created by stephenblack on 6/8/15.
 */
public abstract class ActivityWithMenu extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private int menu_position;
    private String menu_name;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
        menu_name = getMenuName();
        NavDrawerBuilder navDrawerBuilder = new NavDrawerBuilder(getApplicationContext());
        List<String> menu_option_list = navDrawerBuilder.nav_drawer_options;
        menu_position = menu_option_list.indexOf(menu_name);

      mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
      mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), menu_name, this);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        NavDrawerBuilder navDrawerBuilder = new NavDrawerBuilder(getApplicationContext());
        List<String> menu_option_list = navDrawerBuilder.nav_drawer_options;
        List<Intent> intent_list = navDrawerBuilder.nav_drawer_intents;
        if (position != menu_position) {
            startActivity(intent_list.get(position));
            //do not close activity if it is the Launcher or "Home".
            if (!getMenuName().equalsIgnoreCase(Home.menu_name)) {
                finish();
            }
        }
    }

    public abstract String getMenuName();
}
