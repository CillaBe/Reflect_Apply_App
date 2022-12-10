package com.example.reflectapply.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Assessment;
import com.example.reflectapply.Entity.Course;
import com.example.reflectapply.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewCourse extends AppCompatActivity {
    EditText EditCourseID;
    EditText EditTermID;
    EditText EditCourseTitle;
    EditText EditCourseStart;
    EditText EditCourseEnd;
    EditText EditCourseStatus;
    EditText EditInstructorName;
    EditText EditInstructorEmail;
    EditText EditInstructorPhone;
    EditText EditNotes;

    int CourseId;
    int TermId;
    String CourseTitle;
    String CourseStart;
    String CourseEnd;
    String CourseStatus;
    String InstructorName;
    String InstructorEmail;
    String InstructorPhone;
    String Notes;

    Repository repo;

    String DateFormatter = "MM/dd/yy";
    SimpleDateFormat SimpleFormat = new SimpleDateFormat(DateFormatter, Locale.US);


    final Calendar myCalenderBegin = Calendar.getInstance();
    final Calendar myCalenderEnd = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final AssessmentAdapter adapter = new AssessmentAdapter(this);

        repo = new Repository(getApplication());


        EditCourseID = findViewById(R.id.editCourseID);
        EditTermID = findViewById(R.id.editTermID);
        EditCourseTitle = findViewById(R.id.editCourseName);
        EditCourseStart = findViewById(R.id.editCourseStart);
        EditCourseEnd = findViewById(R.id.editCourseEnd);
        EditCourseStatus = findViewById(R.id.editCourseStatus);
        EditInstructorName = findViewById(R.id.editInstructorName);
        EditInstructorPhone = findViewById(R.id.editInstructorPhone);
        EditNotes = findViewById(R.id.editOptionalNotes);
        EditInstructorEmail = findViewById(R.id.editInstructorEmail);

        CourseId = getIntent().getIntExtra("CourseId", -1);
        TermId = getIntent().getIntExtra("TermID", -1);
        CourseTitle = getIntent().getStringExtra("CourseTitle");
        CourseStart = getIntent().getStringExtra("CourseStart");
        CourseEnd = getIntent().getStringExtra("CourseEnd");
        CourseStatus = getIntent().getStringExtra("CourseStatus");
        InstructorName = getIntent().getStringExtra("CourseInstructorName");
        InstructorEmail = getIntent().getStringExtra("InstructorEmail");
        InstructorPhone = getIntent().getStringExtra("InstructorPhone");
        Notes = getIntent().getStringExtra("CourseNote");

        EditCourseID.setText(String.valueOf(CourseId));
        EditTermID.setText(String.valueOf(TermId));
        EditCourseTitle.setText(CourseTitle);
        EditCourseStart.setText(CourseStart);
        EditCourseEnd.setText(CourseEnd);
        EditCourseStatus.setText(CourseStatus);
        EditInstructorName.setText(InstructorName);
        EditInstructorPhone.setText(InstructorPhone);
        EditInstructorEmail.setText(InstructorEmail);
        EditNotes.setText(Notes);

        RecyclerView recyclerView = findViewById(R.id.AssessmentRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new

                LinearLayoutManager(this));
        List<Assessment> AssessmentsByTerm = new ArrayList<>();
        for(
                Assessment assessment :repo.getAllAssessments())

        {
            if (assessment.getCourseID() == CourseId) {
                AssessmentsByTerm.add(assessment);
            }
            adapter.setAssessments(AssessmentsByTerm);

        }

        EditCourseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String dateinformation = EditCourseStart.getText().toString();
                if (dateinformation.equals("")) dateinformation = "11/11/2011";
                try {
                    myCalenderBegin.setTime(SimpleFormat.parse(dateinformation));
                } catch (ParseException e) {
                    e.printStackTrace();

                }

                new DatePickerDialog(ViewCourse.this, startDate, myCalenderBegin.get(Calendar.YEAR), myCalenderBegin.get(Calendar.MONTH), myCalenderBegin.get(Calendar.DAY_OF_MONTH)).show();


            }
        });

        startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalenderBegin.set(Calendar.YEAR, year);
                myCalenderBegin.set(Calendar.MONTH, month);
                myCalenderBegin.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

            }

        };
        EditCourseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String dateinformation = EditCourseEnd.getText().toString();
                if (dateinformation.equals("")) dateinformation = "11/11/2011";
                try {
                    myCalenderEnd.setTime(SimpleFormat.parse(dateinformation));
                } catch (ParseException e) {
                    e.printStackTrace();

                }

                new DatePickerDialog(ViewCourse.this, endDate, myCalenderEnd.get(Calendar.YEAR), myCalenderEnd.get(Calendar.MONTH), myCalenderEnd.get(Calendar.DAY_OF_MONTH)).show();


            }
        });


        endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalenderEnd.set(Calendar.YEAR, year);
                myCalenderEnd.set(Calendar.MONTH, month);
                myCalenderEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();

            }

        };
    }

    private void updateLabelStart() {
        EditCourseStart.setText(SimpleFormat.format(myCalenderBegin.getTime()));
    }
    private void updateLabelEnd() {
        EditCourseEnd.setText(SimpleFormat.format(myCalenderEnd.getTime()));
    }







    public void saveCourse(View view) {
        Course course;
        if (CourseId == -1) {
            int newID = (int) Math.random();

            for (Course c : repo.getAllCourses()) {
                if (c.getCourseID() == newID) {
                    newID = (int) Math.random();
                }
            }
            course = new Course(newID, Integer.parseInt(EditTermID.getText().toString()), EditCourseTitle.getText().toString(), EditCourseStart.getText().toString(), EditCourseEnd.getText().toString(), EditCourseStatus.getText().toString(), EditInstructorName.getText().toString(), EditInstructorPhone.getText().toString(), EditInstructorEmail.getText().toString(), EditNotes.getText().toString());
            repo.insert(course);
            Intent newIntent = new Intent(ViewCourse.this, PassagesList.class);
            startActivity(newIntent);
        } else {
            course = new Course(CourseId, Integer.parseInt(EditTermID.getText().toString()), EditCourseTitle.getText().toString(), EditCourseStart.getText().toString(), EditCourseEnd.getText().toString(), EditCourseStatus.getText().toString(), EditInstructorName.getText().toString(), EditInstructorPhone.getText().toString(), EditInstructorEmail.getText().toString(), EditNotes.getText().toString());
            repo.update(course);
            Intent newIntent = new Intent(ViewCourse.this, PassagesList.class);
            startActivity(newIntent);
        }
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(ViewCourse.this, ViewAssessment.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.courselist, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.ShareNotes:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, EditNotes.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, EditCourseTitle.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareNotes = Intent.createChooser(sendIntent, null);
                startActivity(shareNotes);
                return true;
            case R.id.NotifStartOfCourse:
                String dateFromApp = EditCourseStart.getText().toString();
                Date StartCourseDO = null;
                try{
                    StartCourseDO = SimpleFormat.parse(dateFromApp);
                }
                catch(ParseException e){
                    e.printStackTrace();
                }
                Long trigger = StartCourseDO.getTime();
                Intent intent = new Intent(ViewCourse.this,MyReceiver.class);
                intent.putExtra("key",EditCourseTitle.getText() + " Course Starts Today!");
                AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent sender = PendingIntent.getBroadcast(ViewCourse.this,MainActivity.numAlert++,intent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
                return true;
            case R.id.NotifEndOfCourse:
                String EnddateFromApp = EditCourseEnd.getText().toString();
                Date EndCourseDO = null;
                try{
                    EndCourseDO = SimpleFormat.parse(EnddateFromApp);
                }
                catch(ParseException e){
                    e.printStackTrace();
                }
                Long triggerEnd = EndCourseDO.getTime();
                Intent EndCourseintent = new Intent(ViewCourse.this,MyReceiver.class);
                EndCourseintent.putExtra("key",EditCourseTitle.getText() + " Course Ends Today!");
                AlarmManager EndAlarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
                PendingIntent EndSender = PendingIntent.getBroadcast(ViewCourse.this,MainActivity.numAlert++,EndCourseintent,0);
                EndAlarmManager.set(AlarmManager.RTC_WAKEUP,triggerEnd,EndSender);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void DeleteCourse(View view) {
        for (Course course : repo.getAllCourses()) {
            if (course.getCourseID() == CourseId) {
                repo.delete(course);
            }
            Toast.makeText(ViewCourse.this, " Confirmation: " + CourseTitle + " has been successfully deleted! ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ViewCourse.this, PassagesList.class);
            startActivity(intent);
        }
    }

}

