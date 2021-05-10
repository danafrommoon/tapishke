package com.example.tapishke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPass;
    Button btnLogin;
    TextView tvReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);

        btnLogin = findViewById(R.id.btnLogin);
        tvReg = findViewById(R.id.tvReg);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
                email = etEmail.getText().toString();
                pass = etPass.getText().toString();

                if (email.equals("")){
                    Toast.makeText(LoginActivity.this,"Email не должно быть пустым",Toast.LENGTH_SHORT).show();
                } else if(pass.equals("")){
                    Toast.makeText(LoginActivity.this,"Пароль не должно быть пустым",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Authentication
                    Intent i = new Intent(LoginActivity.this,Main_page.class);
                    startActivity(i);
                    finish();
                }
            }
        });
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}