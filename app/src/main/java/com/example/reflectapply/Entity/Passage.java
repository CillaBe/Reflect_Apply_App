package com.example.reflectapply.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "passageTable")
public class Passage {
    @PrimaryKey(autoGenerate = true)
    private int PassageID;

    private String PassageName;
    private String ReflectionSummary;
    private String ReflectionApplication;
    private String ReflectionPrayer;
    private String ReflectionWord;
    private String ReflectionEntryDateTime;
    //added for new view
    public static int recordtype= 1;
    public static int listtype =0;



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

    public int getPassageID() {
        return PassageID;
    }

    public void setPassageID(int passageID) {
        PassageID = passageID;
    }

    private String PassageDate;

    @Override
    public String toString() {
        return "Passage{" +
                "PassageID=" + PassageID +
                ", PassageName='" + PassageName + '\'' +
                ", ReflectionSummary='" + ReflectionSummary + '\'' +
                ", ReflectionApplication='" + ReflectionApplication + '\'' +
                ", ReflectionPrayer='" + ReflectionPrayer + '\'' +
                ", ReflectionWord='" + ReflectionWord + '\'' +
                ", ReflectionEntryDateTime='" + ReflectionEntryDateTime + '\'' +
                ", PassageDate='" + PassageDate + '\'' +
                '}';
    }

    public String getReflectionEntryDateTime() {
        return ReflectionEntryDateTime;
    }

    public void setReflectionEntryDateTime(String reflectionEntryDateTime) {
        ReflectionEntryDateTime = reflectionEntryDateTime;
    }

    public Passage(int PassageID, String PassageName, String PassageDate, String ReflectionSummary, String ReflectionApplication, String ReflectionPrayer, String ReflectionWord, String ReflectionEntryDateTime) {
        this.PassageID = PassageID;
        this.PassageName = PassageName;
        this.PassageDate = PassageDate;
        this.ReflectionSummary = ReflectionSummary;
        this.ReflectionApplication = ReflectionApplication;
        this.ReflectionPrayer = ReflectionPrayer;
        this.ReflectionWord = ReflectionWord;
        this.ReflectionEntryDateTime = ReflectionEntryDateTime;

    }

    public String getPassageName() {
        return PassageName;
    }

    public void setPassageName(String passageName) {
        PassageName = passageName;
    }

    public String getPassageDate() {
        return PassageDate;
    }

    public void setPassageDate(String passageDate) {
        PassageDate = passageDate;
    }
}


