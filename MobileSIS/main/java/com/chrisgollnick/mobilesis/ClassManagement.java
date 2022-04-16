package com.chrisgollnick.mobilesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class ClassManagement extends AppCompatActivity {

    private RadioButton add;
    private RadioButton remove;
    private Button submitButton;
    private ImageButton homeButton;
    private EditText classid;
    private EditText className;
    private ClassDB classdb;
    private MainActivity mainclass;
    private int radio = 0;

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.addclass:
                if (checked)
                    radio = 1;
                break;
            case R.id.removeclass:
                if(checked)
                    radio = 2;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_management);

        add = (RadioButton) findViewById(R.id.addclass);
        remove = (RadioButton) findViewById(R.id.removeclass);
        submitButton = (Button) findViewById(R.id.submit);
        homeButton = (ImageButton) findViewById(R.id.homeclass);
        classid =(EditText) findViewById(R.id.classid);
        className = (EditText) findViewById(R.id.classname);
        classdb = new ClassDB(this);
        mainclass = new MainActivity();

        String cid= classid.getText().toString();
        String cname = className.getText().toString();

        if (radio == 1) {
            classdb.AddClass(cid, cname);
        } else if (radio == 2) {
            classdb.RemoveClass(cid);
        }
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roleaccess = mainclass.roleAccess;
                if(roleaccess == "Platform Manager") {
                    startActivity(new Intent(ClassManagement.this, PMView.class));
                } else if (roleaccess == "Academic Admin") {
                    startActivity(new Intent(ClassManagement.this, AcademicAdminView.class));
                } else if (roleaccess == "Faculty") {
                    startActivity(new Intent(ClassManagement.this, FacultyView.class));
                } else if (roleaccess == "Student") {
                    startActivity(new Intent(ClassManagement.this, StudentView.class));
                }

            }
        });

    }
}