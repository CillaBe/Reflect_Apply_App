package com.example.reflectapply.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ViewPassage extends AppCompatActivity {

    EditText PassageName;
    EditText editPassageDate;
    EditText EditReflectionSummary;
    EditText EditReflectionApplication;
    EditText EditReflectionPrayer;
    EditText EditReflectionOneWord;




    int passageID;
    Repository repository;


    String name;
    String date;
    String ReflectionApplication;
    String ReflectionSummary;
    String ReflectionPrayer;
    String ReflectionOneWord;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");



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






        repository = new Repository(getApplication());
        editPassageDate = findViewById(R.id.editPassageDate);
        PassageName =findViewById(R.id.editPassageName);
        EditReflectionSummary = findViewById(R.id.editReflectionSummary);
        EditReflectionApplication = findViewById(R.id.editApplication);
        EditReflectionPrayer = findViewById(R.id.editPrayer);
        EditReflectionOneWord = findViewById(R.id.editOneWordTakeAway);


        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        passageID = getIntent().getIntExtra("idD", -1);
        ReflectionSummary = getIntent().getStringExtra("summary");
        ReflectionApplication = getIntent().getStringExtra("application");
        ReflectionPrayer = getIntent().getStringExtra("prayer");
        ReflectionOneWord = getIntent().getStringExtra("word");

        passageID = getIntent().getIntExtra("id",-1);

        PassageName.setText(name);
        editPassageDate.setText(date);
        EditReflectionSummary.setText(String.valueOf(ReflectionSummary));
        EditReflectionApplication.setText(ReflectionApplication);
        EditReflectionSummary.setText(ReflectionSummary);
        EditReflectionPrayer.setText(ReflectionPrayer);
        EditReflectionOneWord.setText(ReflectionOneWord);






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
    public void updateLabelStart() {
        editPassageDate.setText(SimpleFormat.format(myCalenderBegin.getTime()));
    }
    public void savePassage(View view){
        Passage passage;
        String formattedNow = now.format(formatter);
        if (passageID == -1) {
            int newID = (int)Math.random();
            List<Passage> allTerms = new ArrayList<>();
            for(Passage t : repository.getAllPassages()){
                if(t.getPassageID()== newID){
                    newID = (int)Math.random();
                }
            }
            passage = new Passage(newID, PassageName.getText().toString(), editPassageDate.getText().toString(),EditReflectionSummary.getText().toString(), EditReflectionApplication.getText().toString(), EditReflectionPrayer.getText().toString(), EditReflectionOneWord.getText().toString(),formattedNow);
            repository.insert(passage);
            Intent newIntent = new Intent(ViewPassage.this, PassagesList.class);
            startActivity(newIntent);
        } else {
            passage = new Passage(passageID,PassageName.getText().toString(), editPassageDate.getText().toString(),EditReflectionSummary.getText().toString(), EditReflectionApplication.getText().toString(), EditReflectionPrayer.getText().toString(), EditReflectionOneWord.getText().toString(),formattedNow);
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

            for (Passage term : repository.getAllPassages()) {
                if (term.getPassageID() == passageID) {
                    repository.delete(term);
                }
                Toast.makeText(ViewPassage.this, " Confirmation: " + name + " has been successfully deleted! ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewPassage.this, PassagesList.class);
                startActivity(intent);
            }
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

    }

