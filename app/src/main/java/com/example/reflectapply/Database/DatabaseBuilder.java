package com.example.reflectapply.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.reflectapply.DAO.AssessmentDAO;
import com.example.reflectapply.DAO.CourseDAO;
import com.example.reflectapply.DAO.PassageDAO;
import com.example.reflectapply.Entity.Assessment;
import com.example.reflectapply.Entity.Course;
import com.example.reflectapply.Entity.Passage;

@Database(entities={Assessment.class, Passage.class,Course.class}, version=5, exportSchema = false)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract CourseDAO courseDAO();
    public abstract PassageDAO passageDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE==null){
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "myPassageDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

