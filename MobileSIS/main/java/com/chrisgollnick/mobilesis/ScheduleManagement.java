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

public class ScheduleManagement extends AppCompatActivity {

    private RadioButton addSchedule, updateSchedule, viewSchedule, deleteSchedule;
    private EditText studentID, aBlock, bBlock, cBlock, dBlock, eBlock;
    private Button submitSchedule;
    private ImageButton homeFromSchedule;
    private int radioSchedule = 0;
    private MainActivity mainclass;
    private StudentScheduleDB schedDB;

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.addschedule:
                if (checked)
                    radioSchedule = 1;
                break;
            case R.id.viewschedule:
                if(checked)
                    radioSchedule = 2;
                break;
            case R.id.updateschedule:
                if(checked)
                    radioSchedule = 3;
                break;
            case R.id.deleteschedule:
                if(checked)
                    radioSchedule = 4;
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_management);

        addSchedule = (RadioButton) findViewById(R.id.addschedule);
        viewSchedule = (RadioButton) findViewById(R.id.viewschedule);
        updateSchedule = (RadioButton) findViewById(R.id.updateschedule);
        deleteSchedule = (RadioButton) findViewById(R.id.deleteschedule);
        studentID = (EditText) findViewById(R.id.studentID);
        aBlock = (EditText) findViewById(R.id.ablock);
        bBlock = (EditText) findViewById(R.id.bblock);
        cBlock = (EditText) findViewById(R.id.cblock);
        dBlock = (EditText) findViewById(R.id.dblock);
        eBlock = (EditText) findViewById(R.id.eblock);
        submitSchedule = (Button)  findViewById(R.id.submitschedule);
        homeFromSchedule = (ImageButton) findViewById(R.id.homefromschedule);

        homeFromSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roleaccess = mainclass.roleAccess;
                if(roleaccess == "Platform Manager") {
                    startActivity(new Intent(ScheduleManagement.this, PMView.class));
                } else if (roleaccess == "Academic Admin") {
                    startActivity(new Intent(ScheduleManagement.this, AcademicAdminView.class));
                } else if (roleaccess == "Faculty") {
                    startActivity(new Intent(ScheduleManagement.this, FacultyView.class));
                } else if (roleaccess == "Student") {
                    startActivity(new Intent(ScheduleManagement.this, StudentView.class));
                }

            }
        });

        submitSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        if(radioSchedule == 1) {
            submitSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sid = studentID.getText().toString();
                    String newA = aBlock.getText().toString();
                    String newB = bBlock.getText().toString();
                    String newC = cBlock.getText().toString();
                    String newD = dBlock.getText().toString();
                    String newE = eBlock.getText().toString();

                    boolean result = schedDB.CreateNewSchedule(sid, newA, newB, newC, newD, newE);
                    if (result = true) {
                        Toast.makeText(ScheduleManagement.this, "Schedule Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ScheduleManagement.this, "Schedule Creation Failure", Toast.LENGTH_SHORT).show();
                    }


                }
            });

        } else if(radioSchedule == 2) {
            submitSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sid = studentID.getText().toString();
                    aBlock.setText(schedDB.GetA(sid));
                    bBlock.setText(schedDB.GetB(sid));
                    cBlock.setText(schedDB.GetC(sid));
                    dBlock.setText(schedDB.GetD(sid));
                    eBlock.setText(schedDB.GetE(sid));

                }
            });
        } else if(radioSchedule == 3) {
            submitSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sid = studentID.getText().toString();
                    String newA = aBlock.getText().toString();
                    String newB = bBlock.getText().toString();
                    String newC = cBlock.getText().toString();
                    String newD = dBlock.getText().toString();
                    String newE = eBlock.getText().toString();
                    int tick = 0;

                    if (!(newA.equals("")) || !(newA.equals(" "))) {
                        boolean a = schedDB.UpdateA(sid, newA);
                        if (a = true) {
                            tick++;
                        }
                    }

                    if (!(newB.equals("")) || !(newB.equals(" "))) {
                        boolean b = schedDB.UpdateB(sid, newB);
                        if (b = true) {
                            tick++;
                        }
                    }

                    if (!(newC.equals("")) || !(newC.equals(" "))) {
                        boolean c = schedDB.UpdateC(sid, newC);
                        if (c = true) {
                            tick++;
                        }
                    }

                    if (!(newD.equals("")) || !(newD.equals(" "))) {
                        boolean d = schedDB.UpdateD(sid, newD);
                        if (d = true) {
                            tick++;
                        }
                    }

                    if (!(newE.equals("")) || !(newE.equals(" "))) {
                        boolean e = schedDB.UpdateE(sid, newE);
                        if (e = true) {
                            tick++;
                        }
                    }

                    if (tick == 0) {
                        Toast.makeText(ScheduleManagement.this, "Schedule Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ScheduleManagement.this, "1 or More Errors.Schedule Update Failed", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        else if(radioSchedule == 4) {
            submitSchedule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sid = studentID.getText().toString();

                    boolean r = schedDB.deleteSchedule(sid);
                    if (r= true) {
                        Toast.makeText(ScheduleManagement.this, "Schedule Deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ScheduleManagement.this, "Schedule Deletion Failure", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }




    }
}