package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class crudpasien extends AppCompatActivity {

    Button btntambahdatapasien, btnkempas, btnDataPasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudpasien);
        btnDataPasien = findViewById(R.id.btnDatapasien);
        btntambahdatapasien = findViewById(R.id.btntambahpasien);
        btnkempas = findViewById(R.id.btnkempas);

        btnDataPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(crudpasien.this, LihatDataPasien.class);
                startActivity(signup);
            }
        });
        btntambahdatapasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(crudpasien.this, tambah_data_pasien.class);
                startActivity(signup);
            }
        });
        btnkempas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(crudpasien.this, Nav_Home.class);
                startActivity(signup);
            }
        });
    }
}
