package com.chrisgollnick.mobilesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    private Button register;
    public UserDB userdb;
    public String roleAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        loginButton = (Button) findViewById((R.id.loginbutton));
        register = (Button) findViewById(R.id.adminbutton);
        loginButton.setEnabled(false);
        userdb= new UserDB(this);

        username.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence u, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence u, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable u) {
                if(u.toString().equals("")) {
                    loginButton.setEnabled(false);
                } else {
                    loginButton.setEnabled(true);
                }
            }
        }));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                }

                roleAccess = userdb.checkUserPasswordRole(user, pass);
                if (roleAccess == "INVALID") {
                    Toast.makeText(MainActivity.this, "INVALID CREDENTIALS", Toast.LENGTH_LONG).show();
                } else if (roleAccess == "Student") {
                    startActivity(new Intent(MainActivity.this, StudentView.class));
                } else if (roleAccess == "PlatformManager") {
                    startActivity(new Intent(MainActivity.this, PMView.class));
                } else if (roleAccess == "Faculty") {
                    startActivity(new Intent(MainActivity.this, FacultyView.class));
                } else if (roleAccess == "AcademicAdmin") {
                    startActivity(new Intent(MainActivity.this, AcademicAdminView.class));
                } else {
                    startActivity(new Intent(MainActivity.this, UhOhError.class));
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterAdmin.class));
            }
        });

    }
}