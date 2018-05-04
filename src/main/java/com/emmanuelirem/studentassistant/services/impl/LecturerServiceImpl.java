package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Roles;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.repository.LecturerRepository;
import com.emmanuelirem.studentassistant.repository.RolesService;
import com.emmanuelirem.studentassistant.repository.UsersService;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.EncoderService;
import com.emmanuelirem.studentassistant.services.LecturerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional
public class LecturerServiceImpl implements LecturerService{

    private LecturerRepository lecturerRepository;
    private CourseService courseService;

    private UsersService usersService;
    private RolesService rolesService;
    private final EncoderService encoderService;


    public LecturerServiceImpl(LecturerRepository lecturerRepository, CourseService courseService, UsersService usersService, RolesService rolesService, EncoderService encoderService) {
        this.lecturerRepository = lecturerRepository;
        this.courseService = courseService;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.encoderService = encoderService;
    }


    @Override
    public Lecturer fromStudent(Student student) {
        Lecturer lecturer = new Lecturer();
        lecturer.setFirstName(student.getFirstName());
        lecturer.setLastName(student.getLastName());
        lecturer.setUsername(student.getRegistrationNumber());
        lecturer.setPassword(student.getPassword());
        return lecturer;
    }

    @Override
    public void save(Lecturer lecturer) {
        Users newUser = new Users();
        Roles userRole = new Roles();
        newUser.setRegistrationNumber(lecturer.getUsername());
        newUser.setPassword(encoderService.passwordEncoder().encode(lecturer.getPassword()));
        newUser.setEnabled(true);

        userRole.setRegistrationNumber(lecturer.getUsername());
        userRole.setRole("ROLE_LECTURER");

        usersService.save(newUser);
        rolesService.save(userRole);

        lecturer.setSchoolEmailAddress(lecturer.getFirstName()+"."+lecturer.getLastName()+"@covenantuniversity.edu.ng");
        lecturerRepository.save(lecturer);
    }

    @Override
    public void update(Lecturer lecturer) {
        lecturerRepository.save(lecturer);
    }

    @Override
    public void addCourseToLecturer(Lecturer lecturer, Course course) {
        if(course!= null && !lecturer.getCourses().contains(course)){
            lecturer.addCourse(course);
            this.update(lecturer);
        } else {
            System.out.println("Lecturer doesn't lecture course");
        }
    }

    @Override
    public void removeCourseFromLecturer(Lecturer lecturer, Course course) {
        if(course != null && lecturer.getCourses().contains(course)){
            lecturer.removeCourse(course);
            this.update(lecturer);
        } else {
            System.out.println("course it either null or lecturer does not lecture course");
        }
    }

    @Override
    public List<Course> getCoursesWithLecturer(Lecturer lecturer) {
        return courseService.findCoursesWithLecturer(lecturer);
    }

    @Override
    public Lecturer getLecturerByIdentifier(String name) {
        return lecturerRepository.findLecturerByUsername(name);
    }

    @Override
    public Lecturer getLecturerFromRequest(HttpServletRequest request) {
        return lecturerRepository.findLecturerByUsername(request.getUserPrincipal().getName());
    }

    @Override
    public void setOrUpdateCoursePassword(Course course, String password) {
        course.setPassword(password);
        courseService.saveOrUpdate(course);
    }
}
