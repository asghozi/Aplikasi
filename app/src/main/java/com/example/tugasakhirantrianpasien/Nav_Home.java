package com.example.tugasakhirantrianpasien;

import android.content.Intent;
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
import android.widget.LinearLayout;

public class Nav_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout lljadwal, llruangan, lldatapasien, llinformasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lljadwal = findViewById(R.id.lljadwal);
        llruangan = findViewById(R.id.llruangan);
        lldatapasien = findViewById(R.id.lldatapasien);
        llinformasi = findViewById(R.id.llinformasi);
        setSupportActionBar(toolbar);

        lljadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(Nav_Home.this, jadwal.class);
                startActivity(signup);
            }
        });

        llruangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(Nav_Home.this, PoliKlinik.class);
                startActivity(signup);
            }
        });

        lldatapasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent signup = new Intent(Nav_Home.this, crudpasien.class);
                    startActivity(signup);
                }
        });

        llinformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(Nav_Home.this, Informasi.class);
                startActivity(signup);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {
            // Handle the camera action
        } else if (id == R.id.nav_jadwal) {

        } else if (id == R.id.nav_poli) {

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
