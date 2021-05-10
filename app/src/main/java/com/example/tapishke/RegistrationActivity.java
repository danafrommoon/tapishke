package com.example.tapishke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText etName, etEmail, etPass, etConPass;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);

        btnReg = findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, pass, conpass;
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                pass = etPass.getText().toString();
                conpass = etConPass.getText().toString();

                if (name.equals("")){
                    Toast.makeText(RegistrationActivity.this,"Имя не должно быть пустым",Toast.LENGTH_SHORT).show();
                } else if(email.equals("")){
                    Toast.makeText(RegistrationActivity.this,"Email не должно быть пустым",Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")){
                    Toast.makeText(RegistrationActivity.this,"Пароль не должно быть пустым",Toast.LENGTH_SHORT).show();
                } else if(conpass.equals("")){
                    Toast.makeText(RegistrationActivity.this,"Пароль не должно быть пустым",Toast.LENGTH_SHORT).show();
                }
                else if(!conpass.equals("pass")){
                    Toast.makeText(RegistrationActivity.this,"Пароли не совпадают",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Authentication
                    Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}