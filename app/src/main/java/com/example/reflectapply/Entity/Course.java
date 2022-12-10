package com.example.reflectapply.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CourseTable")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int CourseID;

   private String CourseTitle;
    private String CourseStart;
    private String CourseEnd;
    private String CourseStatus;
    private String CourseInstructorName;
    private String InstructorPhone;
    private String InstructorEmail;
    private String CourseNote;
    private int TermID;

    public String getCourseNote() {
        return CourseNote;
    }

    public void setCourseNote(String courseNote) {
        CourseNote = courseNote;
    }

    public String getInstructorEmail() {
        return InstructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        InstructorEmail = instructorEmail;
    }

    public String getInstructorPhone() {
        return InstructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        InstructorPhone = instructorPhone;
    }

    public String getCourseInstructorName() {
        return CourseInstructorName;
    }

    public void setCourseInstructorName(String courseInstructorName) {
        CourseInstructorName = courseInstructorName;
    }

    public String getCourseStatus() {
        return CourseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        CourseStatus = courseStatus;
    }

    public String getCourseEnd() {
        return CourseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        CourseEnd = courseEnd;
    }

    public String getCourseStart() {
        return CourseStart;
    }

    public void setCourseStart(String courseStart) {
        CourseStart = courseStart;
    }

    public String getCourseTitle() {
        return CourseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        CourseTitle = courseTitle;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int courseID) {
        CourseID = courseID;
    }




    @Override
    public String toString() {
        return "Course{" +
                "CourseID=" + CourseID +
                ", CourseTitle='" + CourseTitle + '\'' +
                ", CourseStart='" + CourseStart + '\'' +
                ", CourseEnd='" + CourseEnd + '\'' +
                ", CourseStatus='" + CourseStatus + '\'' +
                ", CourseInstructorName='" + CourseInstructorName + '\'' +
                ", InstructorPhone='" + InstructorPhone + '\'' +
                ", InstructorEmail='" + InstructorEmail + '\'' +
                ", CourseNote='" + CourseNote + '\'' +
                ", TermID=" + TermID +
                '}';
    }

    public int getTermID() {
        return TermID;
    }

    public void setTermID(int termID) {
        TermID = termID;
    }

    public Course(int CourseID, int TermID, String CourseTitle, String CourseStart, String CourseEnd, String CourseStatus, String CourseInstructorName, String InstructorPhone, String InstructorEmail, String CourseNote) {
        this.CourseID = CourseID;
        this.TermID = TermID;
        this.CourseTitle = CourseTitle;
        this.CourseStart = CourseStart;
        this.CourseEnd = CourseEnd;
        this.CourseStatus =CourseStatus;
        this.CourseInstructorName = CourseInstructorName;
        this.InstructorPhone = InstructorPhone;
        this.InstructorEmail = InstructorEmail;
        this.CourseNote = CourseNote;
    }
}
