package com.example.reflectapply.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.R;

import java.util.List;

public class PassagesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passages_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView=findViewById(R.id.termrecyclerview);
        Repository repository = new Repository(getApplication());
        List<Passage> passages= repository.getAllPassages();
        final PassageAdapter adapter = new PassageAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPassages(passages);
    }
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_termlist, menu);
            return true;
        }
        public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    this.finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

    public void goToReflectionDetail(View view) {
        Intent intent = new Intent(PassagesList.this, ViewPassage.class);
        startActivity(intent);
    }
}
