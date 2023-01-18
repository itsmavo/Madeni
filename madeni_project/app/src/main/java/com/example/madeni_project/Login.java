package com.example.madeni_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {
EditText username, password;
MaterialButton Login;
TextView signup;
DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Boolean e=false, p=false;
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.loginbtn);
        dbHelper = new DBHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameCheck = username.getText().toString();
                String passwordCheck = password.getText().toString();
                Cursor cursor = dbHelper.getdata();
                if (cursor.getCount() == 0){
                    Toast.makeText(Login.this,"No entries Exists", Toast.LENGTH_LONG).show();
                }
                if (loginCheck(cursor,usernameCheck,passwordCheck)) {
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    intent.putExtra("username",usernameCheck);
                    username.setText("");
                    password.setText("");
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setCancelable(true);
                    builder.setTitle("Wrong Credential");
                    builder.setMessage("Wrong Credential");
                    builder.show();
                }
                dbHelper.close();
            }
        });
        signup = findViewById(R.id.posacc);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
            }
    public static boolean loginCheck(Cursor cursor,String usernameCheck,String passwordCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(usernameCheck)) {
                if (cursor.getString(2).equals(passwordCheck)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
        }