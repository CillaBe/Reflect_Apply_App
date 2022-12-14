package com.example.reflectapply.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.reflectapply.Entity.Passage;
import com.example.reflectapply.Entity.Reflection;

import java.util.List;

@Dao
public interface PassageDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Passage passage);


    @Update
    void update(Passage passage);

    @Delete
    void delete(Passage passage);

    @Query("SELECT * FROM PASSAGETABLE ORDER BY PassageID ASC")
    List<Passage> getAllPassages();



}

