package com.example.reflectapply.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reflectionTable")
public class Reflection {
    @PrimaryKey(autoGenerate = true)
    private int ReflectionID;

   private String ReflectionSummary;
    private String ReflectionApplication;
    private String ReflectionPrayer;
    private String ReflectionWord;
    private int PassageID;


    public String getReflectionWord() {
        return ReflectionWord;
    }

    public void setReflectionWord(String reflectionWord) {
        ReflectionWord = reflectionWord;
    }

    public String getReflectionPrayer() {
        return ReflectionPrayer;
    }

    public void setReflectionPrayer(String reflectionPrayer) {
        ReflectionPrayer = reflectionPrayer;
    }

    public String getReflectionApplication() {
        return ReflectionApplication;
    }

    public void setReflectionApplication(String reflectionApplication) {
        ReflectionApplication = reflectionApplication;
    }

    public String getReflectionSummary() {
        return ReflectionSummary;
    }

    public void setReflectionSummary(String reflectionSummary) {
        ReflectionSummary = reflectionSummary;
    }

    public int getReflectionID() {
        return ReflectionID;
    }

    public void setReflectionID(int reflectionID) {
        ReflectionID = reflectionID;
    }




    @Override
    public String toString() {
        return "Reflection{" +
                "ReflectionID=" + ReflectionID +
                ", ReflectionSummary='" + ReflectionSummary + '\'' +
                ", ReflectionApplication='" + ReflectionApplication + '\'' +
                ", ReflectionPrayer='" + ReflectionPrayer + '\'' +
                ", ReflectionWord='" + ReflectionWord + '\'' +
                ", PassageID=" + PassageID +
                '}';
    }

    public int getPassageID() {
        return PassageID;
    }

    public void setPassageID(int passageID) {
        PassageID = passageID;
    }

    public Reflection(int ReflectionID,int PassageID,  String ReflectionSummary, String ReflectionApplication, String ReflectionPrayer, String ReflectionWord){
        this.PassageID = PassageID;
        this.ReflectionID = ReflectionID;
        this.ReflectionSummary = ReflectionSummary;
        this.ReflectionApplication = ReflectionApplication;
        this.ReflectionPrayer = ReflectionPrayer;
        this.ReflectionWord =ReflectionWord;

    }
}
