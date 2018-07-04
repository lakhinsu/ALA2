package com.ala.android.ala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Batch;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "GroupPrefs" ;
    public static final String Name1 = "name1Key";
    public static final String Name2 = "name2Key";
    public static final String Name3 = "name3Key";
    public static final String En1 = "en1Key";
    public static final String En2 = "en2Key";
    public static final String En3 = "en3Key";
    public static final String GroupID = "groupKey";
    public static final String Batch = "batch";

    TextView groupid,n1,n2,n3,d1,d2,d3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groupid=(TextView)findViewById(R.id.groupid);
        n1=(TextView)findViewById(R.id.textView2);
        n2=(TextView)findViewById(R.id.textView3);
        n3=(TextView)findViewById(R.id.textView4);
        d1=(TextView)findViewById(R.id.textView5);
        d2=(TextView)findViewById(R.id.textView6);
        d3=(TextView)findViewById(R.id.textView7);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
       if(sharedpreferences.contains(GroupID)) {

           groupid.setText(sharedpreferences.getString(GroupID, ""));
           n1.setText(sharedpreferences.getString(Name1,""));
           n2.setText(sharedpreferences.getString(Name2,""));
           n3.setText(sharedpreferences.getString(Name3,""));
           d1.setText(sharedpreferences.getString(En1,""));
           d2.setText(sharedpreferences.getString(En2,""));
           String temp=sharedpreferences.getString(Batch,"");
           d3.setText(sharedpreferences.getString(En3,"")+"\n\nBatch "+temp);

       }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Reg) {
            // Handle the register action
            if((sharedpreferences.getString(GroupID,"11"))!="11")
            {
                Toast.makeText(this,"Already Registered !",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent up=new Intent(this,RegActivity.class);
                startActivity(up);
            }
        } else if (id == R.id.up) {

            Intent up=new Intent(this,Main2Activity.class);
            startActivity(up);

        } else if (id == R.id.detail) {

            Intent info=new Intent(this,Main3Activity.class);
            startActivity(info);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
