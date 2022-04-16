package com.chrisgollnick.mobilesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class UserManagement extends AppCompatActivity {

    private RadioButton create;
    private RadioButton update;
    private RadioButton view;
    private RadioButton delete;
    private EditText first;
    private EditText last;
    private EditText password;
    private EditText userid;
    private EditText username;
    private EditText role;
    private Button submit;
    private ImageButton homebutton;
    private int radio;
    public MainActivity main;
    public UserDB userdb;


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.adduser:
                if (checked)
                    radio = 1;
                break;
            case R.id.viewuser:
                if(checked)
                    radio = 2;
                break;
            case R.id.updateuser:
                if(checked)
                    radio = 3;
                break;
            case R.id.deleteuser:
                if(checked)
                    radio = 4;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        create = (RadioButton) findViewById(R.id.adduser);
        update = (RadioButton) findViewById(R.id.updateuser);
        view = (RadioButton)  findViewById(R.id.viewuser);
        delete = (RadioButton) findViewById(R.id.deleteuser);
        first = (EditText) findViewById(R.id.firstname);
        last = (EditText)  findViewById(R.id.lastname);
        password = (EditText) findViewById(R.id.password);
        userid = (EditText) findViewById(R.id.userid);
        username = (EditText) findViewById(R.id.username);
        role = (EditText) findViewById(R.id.role);
        submit = (Button) findViewById(R.id.submitbutton);
        homebutton = (ImageButton) findViewById(R.id.home);
        userdb = new UserDB(this);
        main = new MainActivity();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = first.getText().toString();
                String lastname = last.getText().toString();
                String upassword = password.getText().toString();
                String uname = username.getText().toString();
                String sID = userid.getText().toString();
                int id = Integer.parseInt(sID);
                String uRole = role.getText().toString();

                //Add User
                if(radio == 1) {
                    boolean exist = userdb.checkUser(id);
                    if(exist = false){
                        boolean state = userdb.insertData(id,firstname, lastname, upassword, uRole);
                        if(state = true) {
                            Toast.makeText(UserManagement.this, "User Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserManagement.this, "User Not Added", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserManagement.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                    //View User
                } else if (radio == 2) {
                    first.setText(userdb.GetfirstName(id));
                    last.setText(userdb.GetlastName(id));
                    role.setText(userdb.GetRole(id));
                    username.setText(userdb.GetuserName(id));
                //Update User
                } else if(radio == 3) {

                    if(uname != null || uname != "" ) {
                        userdb.UpdateUserName(id, uname);
                    }
                    if(firstname != null || firstname != "") {
                        userdb.UpdateFirstName(id, firstname);
                    }
                    if(lastname != null || lastname !="") {
                        userdb.UpdateLastName(id, lastname);
                    }
                    if(upassword != null || upassword != "") {
                        userdb.UpdatePassword(id, upassword);
                    }
                    if(uRole != null || uRole != "") {
                        userdb.UpdateRole(id, uRole);
                    }
                    Toast.makeText(UserManagement.this, "Updates Complete", Toast.LENGTH_SHORT).show();
                }
                //delete user
                else if(radio == 4) {
                    userdb.deleteUser(id);
                    Toast.makeText(UserManagement.this, "User Deleted", Toast.LENGTH_SHORT).show();
                }



            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roleaccess = main.roleAccess;
                if(roleaccess == "Platform Manager") {
                    startActivity(new Intent(UserManagement.this, PMView.class));
                } else if (roleaccess == "Academic Admin") {
                    startActivity(new Intent(UserManagement.this, AcademicAdminView.class));
                } else if (roleaccess == "Faculty") {
                    startActivity(new Intent(UserManagement.this, FacultyView.class));
                } else if (roleaccess == "Student") {
                    startActivity(new Intent(UserManagement.this, StudentView.class));
                }

            }
        });


    }


}