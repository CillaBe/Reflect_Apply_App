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
import com.example.reflectapply.Entity.Reflection;
import com.example.reflectapply.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewReflection extends AppCompatActivity {
    EditText EditReflectionSummary;
    EditText EditReflectionApplication;
    EditText EditReflectionPrayer;
    EditText EditReflectionOneWord;



    int ReflectionID;
    int PassageID;
    String ReflectionApplication;
    String ReflectionSummary;
    String ReflectionPrayer;
    String ReflectionOneWord;


    int TermId;


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
        setContentView(R.layout.activity_view_reflection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final AssessmentAdapter adapter = new AssessmentAdapter(this);

        repo = new Repository(getApplication());


        EditReflectionSummary = findViewById(R.id.editReflectionSummary);
        EditReflectionApplication = findViewById(R.id.editApplication);
        EditReflectionPrayer = findViewById(R.id.editPrayer);
        EditReflectionOneWord = findViewById(R.id.editOneWordTakeAway);


        ReflectionID = getIntent().getIntExtra("ReflectionID", -1);
        TermId = getIntent().getIntExtra("PassageID", -1);
        ReflectionSummary = getIntent().getStringExtra("ReflectionSummary");
        ReflectionApplication = getIntent().getStringExtra("ReflectionApplication");
        ReflectionPrayer = getIntent().getStringExtra("ReflectionPrayer");
        ReflectionOneWord = getIntent().getStringExtra("ReflectionPrayer");


        EditReflectionSummary.setText(String.valueOf(ReflectionSummary));

        EditReflectionApplication.setText(ReflectionApplication);
        EditReflectionSummary.setText(ReflectionSummary);
        EditReflectionPrayer.setText(ReflectionPrayer);
        EditReflectionOneWord.setText(ReflectionOneWord);
    }








    public void saveReflection(View view) {
        Reflection reflection;
        if (ReflectionID == -1) {
            int newID = (int) Math.random();

            for (Reflection c : repo.getAllReflections()) {
                if (c.getReflectionID() == newID) {
                    newID = (int) Math.random();
                }
            }
            reflection = new Reflection (newID, PassageID, EditReflectionSummary.getText().toString(), EditReflectionApplication.getText().toString(), EditReflectionPrayer.getText().toString(), EditReflectionOneWord.getText().toString());
            repo.insert(reflection);
            Intent newIntent = new Intent(ViewReflection.this, PassagesList.class);
            startActivity(newIntent);
        } else {
            reflection = new Reflection(ReflectionID, PassageID, EditReflectionSummary.getText().toString(), EditReflectionApplication.getText().toString(), EditReflectionPrayer.getText().toString(), EditReflectionOneWord.getText().toString());
            repo.update(reflection);
            Intent newIntent = new Intent(ViewReflection.this, PassagesList.class);
            startActivity(newIntent);
        }
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(ViewReflection.this, ViewAssessment.class);
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, EditReflectionSummary.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, EditReflectionOneWord.getText().toString());
                sendIntent.setType("text/plain");
                Intent shareNotes = Intent.createChooser(sendIntent, null);
                startActivity(shareNotes);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    public void DeleteReflection(View view) {
        for (Reflection course : repo.getAllReflections()) {
            if (course.getReflectionID() == ReflectionID) {
                repo.delete(course);
            }
            Toast.makeText(ViewReflection.this, " Confirmation: your Reflection has been successfully deleted! ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ViewReflection.this, PassagesList.class);
            startActivity(intent);
        }
    }

}

