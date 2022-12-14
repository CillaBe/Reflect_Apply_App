package com.example.reflectapply.Database;

import android.app.Application;

import com.example.reflectapply.DAO.AssessmentDAO;
import com.example.reflectapply.DAO.ReflectionDAO;
import com.example.reflectapply.DAO.PassageDAO;
import com.example.reflectapply.Entity.Assessment;
import com.example.reflectapply.Entity.Reflection;
import com.example.reflectapply.Entity.Passage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssessmentDAO mAssessmentDAO;
    private ReflectionDAO mReflectionDAO;
    private PassageDAO mPassageDAO;
    private List<Assessment> mAllAssessments;
    private List<Reflection> mAllReflections;
    private List<Reflection> mAllReflectionsByID;
    private List<Passage> mAllPassages;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mPassageDAO=db.passageDAO();
        mReflectionDAO =db.reflectionDAO();
        mAssessmentDAO=db.assessmentDAO();
    }

    public void insert(Passage passage) {

        databaseExecutor.execute(() -> {
            mPassageDAO.insert(passage);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


        public void insert(Assessment assessment) {

            databaseExecutor.execute(() -> {
                mAssessmentDAO.insert(assessment);
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    public void insert(Reflection course){

        databaseExecutor.execute(()->{
            mReflectionDAO.insert(course);
        });
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Assessment>getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments=mAssessmentDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }
    public List<Reflection>getAllReflectionByPassageID(int passageID){
        databaseExecutor.execute(()->{
            mAllReflectionsByID= mReflectionDAO.getReflectionsByPassageID(passageID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllReflectionsByID;
    }

    public List<Reflection>getAllReflections(){
        databaseExecutor.execute(()->{
            mAllReflections= mReflectionDAO.getAllReflections();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllReflections;
    }

    public List<Passage>getAllPassages(){
        databaseExecutor.execute(()->{
            mAllPassages=mPassageDAO.getAllPassages();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllPassages;
    }

    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Reflection course){
        databaseExecutor.execute(()->{
            mReflectionDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Passage passage){
        databaseExecutor.execute(()->{
            mPassageDAO.update(passage);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Reflection course){
        databaseExecutor.execute(()->{
            mReflectionDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Passage passage){
        databaseExecutor.execute(()->{
            mPassageDAO.delete(passage);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
