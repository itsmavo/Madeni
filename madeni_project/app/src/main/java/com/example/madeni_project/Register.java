package com.example.madeni_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Register extends AppCompatActivity {
    EditText username,email,password;
    TextView Login;
    DBHelper dbHelper;
    MaterialButton register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.usernameR);
        email = findViewById(R.id.emailR);
        password = findViewById(R.id.passwordR);
        register = findViewById(R.id.regbtn);
        dbHelper = new DBHelper(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                boolean b =dbHelper.insetUserData(username1,email1,password1);
                if (b){
                    Toast.makeText(Register.this,"Data inserted",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Register.this,Login.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Register.this,"Failed To insert Data",Toast.LENGTH_SHORT).show();
                }
            }

        });
        Login = findViewById(R.id.sigacc);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
    }
}