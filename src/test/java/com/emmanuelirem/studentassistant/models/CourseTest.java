package com.emmanuelirem.studentassistant.models;

import com.emmanuelirem.studentassistant.models.enums.SemesterEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CourseTest {

    private Course course;
    private Lecturer lecturer;
    private Student student;

    @Before
    public void setup() {
        course = new Course(2, 400, "CIS 421", "Computer Security", true,"CIS 421", SemesterEnum.OMEGA);
        lecturer = new Lecturer("Azubike", "Azu", "azulolo", "12345");
        student = new Student("Rex", "Ijiekhuamen", "13cg015928", "Daniel", "B301", "12345", null, new ArrayList<>());
        course.addLecturer(lecturer);
        course.addStudent(student);

    }

    @Test
    public void getPrograms() {
    }

    @Test
    public void addProgram() {
    }

    @Test
    public void removeProgram() {
    }

    @Test
    public void getLecturers() {
        System.out.println("running get Lecturers");
        Set<Lecturer> controlLecturerSet = new HashSet<>();
        controlLecturerSet.add(lecturer);

        Set<Lecturer> courseLecturers = course.getLecturers();

        assertNotNull(courseLecturers);
        assertEquals(courseLecturers, controlLecturerSet);
        assertTrue(courseLecturers.contains(lecturer));
    }

    @Test
    public void addLecturer() {
        System.out.println("add Lecturer");
        Lecturer newLecturer = new Lecturer("Ade","Ife","temo","1223");
        course.addLecturer(newLecturer);
        assertTrue(course.getLecturers().contains(newLecturer));
        assertTrue(newLecturer.getCourses().contains(course));
    }

    @Test
    public void removeLecturer() {
        System.out.println("remove Lecturer");
        Lecturer newLecturer = new Lecturer("AdeSecond","Ife","temo","1223");
        course.addLecturer(newLecturer);

        assertTrue(course.getLecturers().contains(newLecturer));
        assertTrue(newLecturer.getCourses().contains(course));

        System.out.println("Size of lecturers is: "+course.getLecturers().size()+" Expected: 3 if less then methods run separately");

        course.removeLecturer(newLecturer);
        assertTrue(!course.getLecturers().contains(newLecturer));
        assertTrue(!newLecturer.getCourses().contains(course));
    }

    @Test
    public void getStudents() {
        assertNotNull(course.getStudents());
        assertTrue(course.getStudents().contains(student));
    }


    @Test
    public void setStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        course.setStudents(studentList);

        assertEquals(studentList, course.getStudents());
    }

    @Test
    public void addStudentDoesNotDuplicateEntries(){

        course.addStudent(student);
        System.out.println(course.getStudents());
        assertEquals(1,course.getStudents().size());
    }

    @Test
    public void addStudent() {
        List<Course> courseList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        courseList.add(course);
        studentList.add(student);

        course.addStudent(student);

        assertEquals(studentList, course.getStudents());
        assertEquals(courseList, student.getCourses());

    }

    @Test
    public void removeStudent() {
        List<Course> emptyCourseList = new ArrayList<>();
        List<Student> emptyStudentList = new ArrayList<>();

        Course tempCourse = new Course(3, 400, "CIS 441", "Computer Games Security", true, "CIS 441", SemesterEnum.OMEGA);
        Student tempStudent = new Student("Damilola", "Oluwayemi", "GREATNESS", "Daniel", "B301", "12345", null, new ArrayList<>());


        tempCourse.addStudent(tempStudent);

        assertTrue(tempCourse.getStudents().contains(tempStudent));
        assertTrue(tempStudent.getCourses().contains(tempCourse));

        tempCourse.removeStudent(tempStudent);

        assertEquals(emptyStudentList, tempCourse.getStudents());
        assertEquals(emptyCourseList, tempStudent.getCourses());
    }

}