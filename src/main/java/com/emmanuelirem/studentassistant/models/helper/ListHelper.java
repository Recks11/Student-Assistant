package com.emmanuelirem.studentassistant.models.helper;

import com.emmanuelirem.studentassistant.models.Course;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {

    private List<Course> coursesList = new ArrayList<>();

    public ListHelper() {
    }

    public List<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }
}
