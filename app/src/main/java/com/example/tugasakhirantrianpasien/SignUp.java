package com.example.tugasakhirantrianpasien;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasakhirantrianpasien.model.Akun;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "signup";
    private TextView tvLoginActivity;
    private Button mTombolSignUp;
    private EditText editTextEmail;
    private EditText editPassword1;
    private EditText editPassword2;
    private EditText editNoKtp;
    private EditText editNamaPasien;
    private EditText editTglLahir;
    private EditText editAlamat;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tvLoginActivity = findViewById(R.id.tv_sudah_punya_akun);
        mTombolSignUp = findViewById(R.id.tombol_signup);
        editNoKtp = findViewById(R.id.no_ktp);
        editNamaPasien = findViewById(R.id.nama_pasien);
        editTglLahir = findViewById(R.id.tgl_lahir);
        editAlamat = findViewById(R.id.alamat_pasien);
        editTextEmail = findViewById(R.id.email_signup);
        editPassword1 = findViewById(R.id.password1);
        editPassword2 = findViewById(R.id.password2);
        progressBar1 = findViewById(R.id.progressbar_signup);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        tvLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        mTombolSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("message");
//
//                myRef.setValue("Hello, World!");

                // Read from the database
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//                        String value = dataSnapshot.getValue(String.class);
//                        Log.d(TAG, "Value is: " + value);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
//                    }
//                });

                registerUser();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void registerUser() {
        final String no_ktp = editNoKtp.getText().toString().trim();
        final String nama_pasien = editNamaPasien.getText().toString().trim();
        final String tgl_lahir = editTglLahir.getText().toString().trim();
        final String alamat_pasien = editAlamat.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editPassword1.getText().toString().trim();

        if (no_ktp.isEmpty()) {
            editNoKtp.setError("NIK is required!");
            editNoKtp.requestFocus();
            return;
        }

        if (nama_pasien.isEmpty()) {
            editNamaPasien.setError("Name is required!");
            editNamaPasien.requestFocus();
            return;
        }

        if (tgl_lahir.isEmpty()) {
            editTglLahir.setError("Birthday is required!");
            editTglLahir.requestFocus();
            return;
        }

        if (alamat_pasien.isEmpty()) {
            editAlamat.setError("Address is required!");
            editAlamat.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editPassword1.setError("Password is required!");
            editPassword1.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editPassword1.setError("Minimum 6 digit password!");
            editPassword1.requestFocus();
            return;
        }

        if (!password.equals(editPassword2.getText().toString())){
            editPassword2.setError("Password isn't match!");
            editPassword2.requestFocus();
            return;
        }

        progressBar1.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar1.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    CollectionReference dbAkun = db.collection("akun");

                    Akun akun = new Akun(no_ktp, nama_pasien, tgl_lahir, alamat_pasien,
                            email,
                            password, "1"
                    );

                    Log.d("", akun.toString());

                    dbAkun.add(akun)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(SignUp.this, "Akun Ditambahkan", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(SignUp.this, LoginActivity.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
