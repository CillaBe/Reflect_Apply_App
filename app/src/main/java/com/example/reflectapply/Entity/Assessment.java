package com.example.reflectapply.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AssessmentTable")
public class Assessment {


    @PrimaryKey(autoGenerate = true)

    private int AssessmentID;
    private String AssessmentType;
    private String AssessmentTitle;
    private String AssessmentEnd;
    private String AssessmentStart;

    public String getAssessmentStart() {
        return AssessmentStart;
    }

    public String getAssessmentEnd() {
        return AssessmentEnd;
    }

    private int CourseID;

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "AssessmentID=" + AssessmentID +
                ", PerformanceAssessment='" + AssessmentType + '\'' +

                ", AssessmentTitle='" + AssessmentTitle + '\'' +

                ", AssessmentStart=" + AssessmentStart +
                ", AssessmentEnd=" + AssessmentEnd +
                ", CourseID=" + CourseID +
                '}';
    }

    public void setAssessmentStart(String assessmentStart) {
        AssessmentStart = assessmentStart;
    }

    public void setAssessmentEnd(String assessmentEnd) {
        AssessmentEnd = assessmentEnd;
    }

    public Assessment(int AssessmentID, int CourseID, String AssessmentType, String AssessmentTitle,String AssessmentStart, String AssessmentEnd ) {
        this.AssessmentID = AssessmentID;
        this.CourseID = CourseID;
        this.AssessmentType = AssessmentType;
        this.AssessmentTitle = AssessmentTitle;
        this.AssessmentEnd = AssessmentEnd;
        this.AssessmentStart = AssessmentStart;
    }








    public String getAssessmentTitle() {
        return AssessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        AssessmentTitle = assessmentTitle;
    }


    public String getAssessmentType() {
        return AssessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        AssessmentType = assessmentType;
    }

    public int getAssessmentID() {
        return AssessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        AssessmentID = assessmentID;
    }
}
