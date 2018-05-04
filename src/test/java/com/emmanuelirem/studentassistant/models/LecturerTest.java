package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.enums.SemesterEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LecturerTest {

    private Course course;
    private Course secondCourse;
    private Lecturer lecturer; // to be used later

    @Before
    public void setup(){
        course = new Course(2,400,"CIS 421","Computer Security", true,"CIS 421", SemesterEnum.OMEGA);
        secondCourse = new Course(3,400,"CIS 422","Computer Imformation Systems", true, "CIS 422", SemesterEnum.OMEGA);
        lecturer = new Lecturer("Azubike","Azu","azulolo","12345","B401");
    }

    @Test
    public void setCourses() {
        List<Course> courseSet = new ArrayList<>();
        Lecturer testLecturer = new Lecturer("Azubike","Azu","azulolo","12345", courseSet);
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
        List<Course> tempCourseSet = new ArrayList<>();
        tempCourseSet.add(course);

        lecturer.addCourse(course);

        assertEquals(tempCourseSet, lecturer.getCourses());
        assertTrue(course.getLecturers().contains(lecturer));
    }

    @Test
    public void removeCourse() {
        List<Course> tempCourseSet = new ArrayList<>();
        lecturer.addCourse(course);

        assertTrue(course.getLecturers().contains(lecturer));

        lecturer.removeCourse(course);
        assertEquals(tempCourseSet, lecturer.getCourses());

    }

}