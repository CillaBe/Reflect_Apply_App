package com.example.reflectapply.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Course;
import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPassage extends AppCompatActivity {

    EditText PassageName;
    EditText PassageDate;


    String name;
    String date;

    int passageID;
    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_passage);

        final CourseAdapter adapter = new CourseAdapter(this);


        repository = new Repository(getApplication());
        PassageDate = findViewById(R.id.editPassageDate);
        PassageName =findViewById(R.id.editPassageName);

        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");

        passageID = getIntent().getIntExtra("id",-1);

        PassageName.setText(name);
        PassageDate.setText(date);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Course> CoursesByTerm = new ArrayList<>();
        for (Course course : repository.getAllCourses()){
            if (course.getTermID() == passageID) {
                CoursesByTerm.add(course);
            }
            adapter.setCourse(CoursesByTerm);

        }







    }
    public void saveTerm(View view){
        Passage passage;
        if (passageID == -1) {
            int newID = (int)Math.random();
            List<Passage> allTerms = new ArrayList<>();
            for(Passage t : repository.getAllPassages()){
                if(t.getPassageID()== newID){
                    newID = (int)Math.random();
                }
            }
            passage = new Passage(newID, PassageName.getText().toString(),PassageDate.getText().toString());
            repository.insert(passage);
            Intent newIntent = new Intent(ViewPassage.this, PassagesList.class);
            startActivity(newIntent);
        } else {
            passage = new Passage(passageID,  PassageName.getText().toString(),PassageDate.getText().toString());
            repository.update(passage);
            Intent newIntent = new Intent(ViewPassage.this, PassagesList.class);
            startActivity(newIntent);
        }
    }
    public void goToCourseDetails(View view){
        Intent intent = new Intent(ViewPassage.this,ViewCourse.class);
        startActivity(intent);
    }

    public void DeleteTerm(View view) {
        int numberOfCourses = 0;
        for (Course course : repository.getAllCourses()){
            if (course.getTermID() == passageID ){
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

