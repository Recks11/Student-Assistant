package com.emmanuelirem.studentassistant.services.impl;

import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.security.Roles;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.CourseRepository;
import com.emmanuelirem.studentassistant.repository.RolesService;
import com.emmanuelirem.studentassistant.repository.StudentRepository;
import com.emmanuelirem.studentassistant.repository.UsersService;
import com.emmanuelirem.studentassistant.services.CourseService;
import com.emmanuelirem.studentassistant.services.EncoderService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{


    private final CourseService courseService;
    private final StudentRepository studentRepository;
    private UsersService usersService;
    private RolesService rolesService;
    private final EncoderService encoderService;

    public StudentServiceImpl(CourseService courseService, StudentRepository studentRepository, UsersService usersService, RolesService rolesService, EncoderService encoderService) {
        this.courseService = courseService;
        this.studentRepository = studentRepository;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.encoderService = encoderService;
    }

    @Override
    public Student findByRegistrationNumber(String registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public Student getLoggedInStudentFromRequest(HttpServletRequest request) {
        return studentRepository.findByRegistrationNumber(request.getUserPrincipal().getName());
    }

    @Override
    public void save(Student student) {

        Users newUser = new Users();
        Roles userRole = new Roles();
        newUser.setRegistrationNumber(student.getRegistrationNumber().toLowerCase());
        newUser.setPassword(encoderService.passwordEncoder().encode(student.getPassword()));
        newUser.setEnabled(true);
        student.setRegistrationNumber(student.getRegistrationNumber().toLowerCase());

        userRole.setRegistrationNumber(student.getRegistrationNumber());
        userRole.setRole("ROLE_STUDENT");

        usersService.save(newUser);
        rolesService.save(userRole);

        student.setEmailAddress(student.getFirstName().toLowerCase()+"."+student.getLastName().toLowerCase()+"@stu.cu.edu.ng");
        studentRepository.save(student);
    }

    public void update(Student student){
        studentRepository.save(student);
    }

    public void saveAll(List<Student> studentList){
        studentList.forEach(this::save);
    }

    @Override
    public List<Course> findUnregisteredCoursesForStudent(Student student) {
        Program program = student.getProgram();
        List<Course> listOfProgramCourses = courseService.findCoursesContainingProgram(program);
        List<Course> listOfRegisteredCourses = student.getCourses();
        List<Course> listOfCoursesToBeRegistered = new ArrayList<>();

        listOfProgramCourses.forEach(course -> {
            if (!listOfRegisteredCourses.contains(course)) {
                listOfCoursesToBeRegistered.add(course);
            }
        });

        return listOfCoursesToBeRegistered;
    }
}
