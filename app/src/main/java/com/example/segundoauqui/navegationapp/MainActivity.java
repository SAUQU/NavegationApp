package com.example.segundoauqui.navegationapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static com.example.segundoauqui.navegationapp.R.id.action_moreOptions;
import static com.example.segundoauqui.navegationapp.R.layout.activity_main;
import static com.example.segundoauqui.navegationapp.R.layout.fragment_camera;






public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Camera.OnFragmentInteractionListener, EMI_Calculator.OnFragmentInteractionListener,Home.OnFragmentInteractionListener, MP3Player.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        setTitle("Loading...");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(null == savedInstanceState){

            Fragment fragment = new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.action_container, fragment).commit();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();



        switch (item.getItemId()) {
            case R.id.action_moreOptions:

                //String url = "https://developer.chrome.com/extensions/options";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.putExtras(i);
                startActivity(i);

//
//                String url = "https://depaul.joinhandshake.com/users/223454";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);



//                Intent myIntent = new Intent(getApplicationContext(), MyActivity.class);
//                myIntent.putExtra("theUrl", url);
//                startActivity(myIntent);

               // newGame;
                return true;


            case R.id.action_myApps:

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                ComponentName comp = new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
                launchIntent.setComponent(comp);
                launchIntent.setData(Uri.parse("market://details?id=com.snapchat.android"));

                startActivity(launchIntent);

                return true;


            case R.id.action_myHelp:
                //showHelp();

                String url1 = "https://developer.android.com/support.html";
                Intent b = new Intent(Intent.ACTION_VIEW);
                b.setData(Uri.parse(url1));
                startActivity(b);


                return true;


            // Respond to the action bar's back button
            case R.id.back:
                //Trigger BackKey
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String key = "";


        Fragment fragment = null;
        Boolean FragmentSelected = true;



        if (id == R.id.nav_camera) {
            // Handle the camera action
            key = "cam";
            fragment = new Home();
            //getSupportFragmentManager().beginTransaction().replace(R.id.action_container, fragment).commit();

            FragmentSelected = true;


        } else if (id == R.id.nav_gallery) {
            key = "gal";
            fragment = new Camera();
            FragmentSelected = true;

            //getSupportFragmentManager().beginTransaction().replace(R.id.action_container, fragment).commit();

        } else if (id == R.id.nav_slideshow) {
            key = "slide";
            fragment = new EMI_Calculator();
            FragmentSelected = true;

            //getSupportFragmentManager().beginTransaction().replace(R.id.action_container, fragment).commit();

        } else if (id == R.id.nav_manage) {
            key = "mp3";
            fragment = new MP3Player();
            FragmentSelected = true;


            //getSupportFragmentManager().beginTransaction().replace(R.id.action_container, fragment).commit();

        }


        if(FragmentSelected){

            getSupportFragmentManager().beginTransaction().replace(R.id.action_container, fragment).addToBackStack(key).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
