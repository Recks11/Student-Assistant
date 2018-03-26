package com.emmanuelirem.studentassistant.models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class LecturerTest {

    private Course course;
    private Course secondCourse;
    private Lecturer lecturer; // to be used later
    private Student student; //to be used later

    @Before
    public void setup(){
        course = new Course(2,400,"CIS 421","Computer Security", true);
        secondCourse = new Course(3,400,"CIS 422","Computer Imformation Systems", true);
        lecturer = new Lecturer("Azubike","Azu","azu@gmail.com","azulolo","12345");
        student = new Student("Rex","Ijiekhuamen","13cg015928","Daniel","B301","12345",null, new ArrayList<>());
    }

    @Test
    public void setCourses() {
        Set<Course> courseSet = new HashSet<>();
        Lecturer testLecturer = new Lecturer("Azubike","Azu","azu@gmail.com","azulolo","12345", courseSet);
        assertNotNull(testLecturer.getCourses()); //lecturer initialises and courses are not null

        courseSet.add(course);
        courseSet.add(secondCourse);
        testLecturer.setCourses(courseSet);

        assertEquals(courseSet, testLecturer.getCourses());

        courseSet.forEach(course1 -> {
            System.out.println("asserting course:"+course1.toString()+" contains lecturers");
            assertTrue(course1.getLecturers().contains(testLecturer));
            assertTrue(testLecturer.getCourses().contains(course1));
        });


    }

    @Test
    public void addCourse() {
        Set<Course> tempCourseSet = new HashSet<>();
        tempCourseSet.add(course);

        lecturer.addCourse(course);

        assertEquals(tempCourseSet, lecturer.getCourses());
        assertTrue(course.getLecturers().contains(lecturer));
    }

    @Test
    public void removeCourse() {
        Set<Course> tempCourseSet = new HashSet<>();
        lecturer.addCourse(course);

        assertTrue(course.getLecturers().contains(lecturer));

        lecturer.removeCourse(course);
        assertEquals(tempCourseSet, lecturer.getCourses());

    }

}