package com.chrisgollnick.mobilesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PMView extends AppCompatActivity {

    private Button adminbutton;
    private Button facultybutton;
    private Button studentbutton;
    private Button usermanage;
    private Button classmanage;
    private Button schedulemanage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmview);

        adminbutton = (Button) findViewById(R.id.adminbutton);
        facultybutton = (Button) findViewById(R.id.facultybutton);
        studentbutton = (Button) findViewById(R.id.studentbutton);
        usermanage = (Button) findViewById(R.id.usermanagement);
        classmanage = (Button) findViewById(R.id.classmanagement);
        schedulemanage = (Button) findViewById(R.id.schedulemanagement);

        adminbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PMView.this, AcademicAdminView.class));
            }
        });

        facultybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PMView.this, FacultyView.class));
            }
        });

        studentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PMView.this, StudentView.class));
            }
        });

        usermanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PMView.this, UserManagement.class));
            }
        });

        classmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PMView.this, ClassManagement.class));
            }
        });

        schedulemanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PMView.this, ScheduleManagement.class));
            }
        });


    }
}