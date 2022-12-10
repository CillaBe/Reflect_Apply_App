package com.example.reflectapply.Database;

import android.app.Application;

import com.example.reflectapply.DAO.AssessmentDAO;
import com.example.reflectapply.DAO.CourseDAO;
import com.example.reflectapply.DAO.PassageDAO;
import com.example.reflectapply.Entity.Assessment;
import com.example.reflectapply.Entity.Course;
import com.example.reflectapply.Entity.Passage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private AssessmentDAO mAssessmentDAO;
    private CourseDAO mCourseDAO;
    private PassageDAO mPassageDAO;
    private List<Assessment> mAllAssessments;
    private List<Course> mAllCourses;
    private List<Passage> mAllPassages;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mPassageDAO=db.passageDAO();
        mCourseDAO=db.courseDAO();
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
    public void insert(Course course){

        databaseExecutor.execute(()->{
            mCourseDAO.insert(course);
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

    public List<Course>getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses=mCourseDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
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
    public void update(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.update(course);
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

    public void delete(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(course);
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
