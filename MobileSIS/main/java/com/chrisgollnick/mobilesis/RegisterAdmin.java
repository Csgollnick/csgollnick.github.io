package com.chrisgollnick.mobilesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class RegisterAdmin extends AppCompatActivity {

    private EditText rcode;
    private EditText firstname;
    private EditText lastname;
    private EditText password;
    private Button register;
    private String registerRole = "Platform Manager";
    private int registerID = 0;
    public UserDB userdb;
    private boolean exist = true;
    private boolean codestate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        rcode = (EditText) findViewById(R.id.code);
        firstname = (EditText) findViewById(R.id.registerFirst);
        lastname = (EditText) findViewById(R.id.registerLast);
        password = (EditText) findViewById(R.id.registerpassword);
        register = (Button) findViewById(R.id.registernewadmin);
        userdb = new UserDB(this);

        //Generate Id Number for a platform manager
        while(exist = true){
            Random r = new Random();
            int fd = 1000 + r.nextInt(9999);
            String suffix = Integer.toString(fd);
            String idstring = "9999" + suffix;
            registerID = Integer.parseInt(idstring);
            exist = userdb.checkUser(registerID);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registercode = rcode.getText().toString();
                String first = firstname.getText().toString();
                String last = lastname.getText().toString();
                String pass = password.getText().toString();

                codestate = userdb.CheckAdminRegisterCode(registercode);
                if (codestate = true) {
                    userdb.insertData(registerID, first, last, pass, registerRole);
                    Toast.makeText(RegisterAdmin.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterAdmin.this, MainActivity.class));
                } else {
                    Toast.makeText(RegisterAdmin.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterAdmin.this, MainActivity.class));
                }
            }
        });




    }
}