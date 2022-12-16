package com.example.reflectapply.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.reflectapply.Database.Repository;
import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.R;

import java.util.ArrayList;
import java.util.List;

public class PassagesList extends AppCompatActivity {
    PassageAdapter adapter;
    private  SearchView mySearchView;
    private SearchManager mySearchManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passages_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView=findViewById(R.id.termrecyclerview);
        Repository repository = new Repository(getApplication());

        List<Passage> passages= repository.getAllPassages();
        adapter = new PassageAdapter(passages,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPassages(passages);

        //added below
        mySearchManager =(SearchManager) getSystemService(Context.SEARCH_SERVICE);

        //end of added
    }
    //added below


    //end of added

        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.search_menu, menu);
            ;

            //below was added
            MenuItem item = menu.findItem(R.id.search_passages);
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.filterList().filter(newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
            });
           //ending of what was added
            return super.onCreateOptionsMenu(menu);
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
