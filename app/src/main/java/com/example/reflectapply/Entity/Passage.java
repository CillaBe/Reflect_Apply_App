package com.example.reflectapply.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "passageTable")
public class Passage {
    @PrimaryKey(autoGenerate = true)
    private int PassageID;

    private String PassageName;

    public int getPassageID() {
        return PassageID;
    }

    public void setPassageID(int passageID) {
        PassageID = passageID;
    }

    private String PassageDate;


    public Passage(int PassageID, String PassageName, String PassageDate) {
        this.PassageID = PassageID;
        this.PassageName = PassageName;
        this.PassageDate = PassageDate;

    }

    @Override
    public String toString() {
        return "Passage{" +
                "PassageID=" + PassageID +
                ", PassageName='" + PassageName + '\'' +
                ", PassageDate='" + PassageDate + '\'' +

                '}';
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


