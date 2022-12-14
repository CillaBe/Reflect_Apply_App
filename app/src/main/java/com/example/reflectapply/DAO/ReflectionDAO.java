package com.example.reflectapply.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.reflectapply.Entity.Reflection;

import java.util.List;

@Dao
public interface ReflectionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Reflection course);


    @Update
    void update(Reflection course);

    @Delete
    void delete(Reflection course);

    @Query("SELECT * FROM reflectionTable ORDER BY ReflectionID ASC")
    List<Reflection> getAllReflections();

    @Query("SELECT * FROM reflectionTable WHERE PassageID = :passageID ")
    public List<Reflection> getReflectionsByPassageID(int passageID);
}

