package com.example.reflectapply.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Reflection;
import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewPassage extends AppCompatActivity {

    EditText PassageName;
    EditText editPassageDate;


    String name;
    String date;

    int passageID;
    Repository repository;




    String PassageDate;


    Repository repo;

    String DateFormatter = "MM/dd/yy";
    SimpleDateFormat SimpleFormat = new SimpleDateFormat(DateFormatter, Locale.US);


    final Calendar myCalenderBegin = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener startDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_passage);

        final ReflectionAdapter adapter = new ReflectionAdapter(this);


        repository = new Repository(getApplication());
        editPassageDate = findViewById(R.id.editPassageDate);
        PassageName =findViewById(R.id.editPassageName);

        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");

        passageID = getIntent().getIntExtra("id",-1);

        PassageName.setText(name);
        editPassageDate.setText(date);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Reflection> CoursesByTerm = new ArrayList<>();
        for (Reflection course : repository.getAllReflections()){
            if (course.getPassageID() == passageID) {
                CoursesByTerm.add(course);
            }
            adapter.setReflections(CoursesByTerm);

        }



        editPassageDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date;
                String dateinformation = editPassageDate.getText().toString();
                if (dateinformation.equals("")) dateinformation = "11/11/2011";
                try {
                    myCalenderBegin.setTime(SimpleFormat.parse(dateinformation));
                } catch (ParseException e) {
                    e.printStackTrace();

                }

                new DatePickerDialog(ViewPassage.this, startDate, myCalenderBegin.get(Calendar.YEAR), myCalenderBegin.get(Calendar.MONTH), myCalenderBegin.get(Calendar.DAY_OF_MONTH)).show();


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








    }
    private void updateLabelStart() {
        editPassageDate.setText(SimpleFormat.format(myCalenderBegin.getTime()));
    }
    public void savePassage(View view){
        Passage passage;
        if (passageID == -1) {
            int newID = (int)Math.random();
            List<Passage> allTerms = new ArrayList<>();
            for(Passage t : repository.getAllPassages()){
                if(t.getPassageID()== newID){
                    newID = (int)Math.random();
                }
            }
            passage = new Passage(newID, PassageName.getText().toString(), editPassageDate.getText().toString());
            repository.insert(passage);
            Intent newIntent = new Intent(ViewPassage.this, PassagesList.class);
            startActivity(newIntent);
        } else {
            passage = new Passage(passageID,  PassageName.getText().toString(), editPassageDate.getText().toString());
            repository.update(passage);
            Intent newIntent = new Intent(ViewPassage.this, PassagesList.class);
            startActivity(newIntent);
        }
    }
    public void GoToReflections(View view){
        Intent intent = new Intent(ViewPassage.this, ViewReflection.class);
        startActivity(intent);
    }

    public void DeletePassage(View view) {
        int numberOfCourses = 0;
        for (Reflection course : repository.getAllReflections()){
            if (course.getPassageID() == passageID ){
                ++numberOfCourses;}


        }
        if(numberOfCourses == 0) {
            for (Passage term : repository.getAllPassages()) {
                if (term.getPassageID() == passageID) {
                    repository.delete(term);
                }
                Toast.makeText(ViewPassage.this, " Confirmation: " + name + " has been successfully deleted! ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewPassage.this, PassagesList.class);
                startActivity(intent);
            }
        }
            else{
            Toast.makeText(ViewPassage.this, " Error " + name + " has associated courses and cannot be deleted! ", Toast.LENGTH_LONG).show();

            }
        }

    }

