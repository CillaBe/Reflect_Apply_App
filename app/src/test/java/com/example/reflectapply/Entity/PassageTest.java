package com.example.reflectapply.Entity;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.reflect.Field;

public class PassageTest {

    @Test
    public void getReflectionWordTest() throws Exception {
        Passage passage = new Passage();
        //when
        passage.setReflectionWord("Test Word");
        //then
        assertTrue(passage.getReflectionWord() == "Test Word");
    }

    @Test
    public void getReflectionPrayerTest() {
        Passage passage = new Passage();
        //when
        passage.setReflectionPrayer("Prayer Test");
        //then
        assertTrue(passage.getReflectionPrayer() == "Prayer Test");
    }

    @Test
    public void getReflectionApplicationTest() {
        Passage passage = new Passage();
        //when
        passage.setReflectionApplication("Test Application");
        //then
        assertTrue(passage.getReflectionApplication() == "Test Application");
    }

    @Test
    public void getReflectionSummaryTest() {
        Passage passage = new Passage();
        //when
        passage.setReflectionSummary("Test Reflection");
        //then
        assertTrue(passage.getReflectionSummary() == "Test Reflection");
    }

    @Test
    public void getPassageIDTest() {
        Passage passage = new Passage();
        //when
        passage.setPassageID(1);
        //then
        assertTrue(passage.getPassageID() == 1);
    }

    @Test
    public void getReflectionEntryDateTimeTest() {
        Passage passage = new Passage();
        //when
        passage.setReflectionEntryDateTime("Test Entry Date and Time");
        //then
        assertTrue(passage.getReflectionEntryDateTime() == "Test Entry Date and Time");


    }

    @Test
    public void getPassageNameTest() {
        Passage passage = new Passage();
        //when
        passage.setPassageName("Psalm 103");
        //then
        assertTrue(passage.getPassageName() == "Psalm 103");
    }

    @Test
    public void getPassageDate() {

        Passage passage = new Passage();
        //when
        passage.setPassageDate("Test Date");
        //then
        assertTrue(passage.getPassageDate() == "Test Date");
    }
}