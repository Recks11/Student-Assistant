package com.emmanuelirem.studentassistant.bootstrap;


import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.security.Roles;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.*;
import com.emmanuelirem.studentassistant.repository.*;
import com.emmanuelirem.studentassistant.services.EncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class initTemporaryDbData implements ApplicationListener<ContextRefreshedEvent> {

    private EncoderService encoderService;
    private StudentRepository studentRepository;
    private ProgramRepository programRepository;
    private UsersService usersService;
    private RolesService rolesService;
    private final CourseRepository courseRepository;

    @Autowired
    public initTemporaryDbData(StudentRepository studentRepository,
                               UsersService usersService, RolesService rolesService,
                               EncoderService encoderService,
                               ProgramRepository programRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.encoderService = encoderService;
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //call methods here
        addRandomStudents();
        addUniversityCoursesDepartmentsAndColleges();
        addCourses();
    }

    private void addRandomStudents() {
        //create 5 random users in memory
        LinkedList<Student> newStudents = new LinkedList<>();
        newStudents.add(new Student("Rex","Ijiekhuamen","13cg015928","Daniel","B301","12345",null, new ArrayList<Course>()));
        newStudents.add(new Student("Emmanuel","Irem","13cg015929","Daniel","A407","12345",null, new ArrayList<Course>()));
        newStudents.add(new Student("Guy","Random","14CG083345","Paul","F401","12345",null, new ArrayList<Course>()));
        newStudents.add(new Student("Girl","Random","15AD015928","Esther","B301","12345",null, new ArrayList<Course>()));
        studentRepository.saveAll(newStudents);
        newStudents.forEach(student -> {
            //get reg numbers and password from linked list
            String registrationNumber = student.getRegistrationNumber();
            String password = encoderService.passwordEncoder().encode(student.getPassword());//encode password

            Users newUser = new Users(registrationNumber,password,true);
            Roles userRole = new Roles(registrationNumber,"ROLE_USER");

            //save new user login details
            usersService.save(newUser);
            rolesService.save(userRole);
        });
    }
    //
    private void addUniversityCoursesDepartmentsAndColleges() {

//        List<College> collegeList = new ArrayList<>();
//        List<Department> deptList = new ArrayList<>();
        List<Program> programList = new ArrayList<>();

        //Add Colleges Here
        College cst = makeCollege(CollegeEnum.College_of_Science_and_Technology);
//        College clds = makeCollege(CollegeEnum.College_of_Leadership_Development_Studies);
//        College cbss = makeCollege(CollegeEnum.College_of_Business_and_Social_Sciences);
//        College engineering = makeCollege(CollegeEnum.College_of_Engineering);

        //Add Departments here
//        Department architecture = makeDepartment(DepartmentsEnum.Architecture, cst);
//        Department bioChem = makeDepartment(DepartmentsEnum.Biochemistry, cst);
//        Department buildingTech = makeDepartment(DepartmentsEnum.Building_Technology, cst);
//        Department bioScience = makeDepartment(DepartmentsEnum.Biological_Sciences, cst);
        Department cmis = makeDepartment(DepartmentsEnum.Computer_and_Information_Sciences, cst);
//        Department estateManagement = makeDepartment(DepartmentsEnum.Estate_Management, cst);
//        Department mathematics = makeDepartment(DepartmentsEnum.Mathematics, cst);
        Department physics = makeDepartment(DepartmentsEnum.Physics, cst);

        //Add Programs here
        programList.add(makeProgram(ProgramEnum.Computer_Science, cmis));
        programList.add(makeProgram(ProgramEnum.Management_and_Information_Science, cmis));
        programList.add(makeProgram(ProgramEnum.Industrial_Physics, physics));

        programRepository.saveAll(programList);
    }

    private College makeCollege(CollegeEnum name) {
        College college = new College();
        college.setName(name);
        return college;
    }

    private Department makeDepartment(DepartmentsEnum name, College college ) {
        Department department = new Department();
        department.setName(name.name().replace('_',' '));//this line converts the enum to a string by replacing the _ with a blank space
        department.setCollege(college);
        return department;
    }

    private Program makeProgram(ProgramEnum name, Department department) {
        Program program = new Program();
        program.setName(name.name().replace('_',' '));//this line converts the enum to a string by replacing the _ with a blank space
        program.setCourses(new HashSet<>());
        program.setDepartment(department);
        return program;
    }

    private void addCourses() {
        Set<Course> coursesList = new HashSet<>();

        Course course1 = new Course(400,"CSC 411","idk");
        Course course2 = new Course(400,"CSC 412","idk");
        Course course3 = new Course(400,"CSC 413","idk");
        Course course4 = new Course(400,"CSC 421","idk");
        Course course5 = new Course(400,"CSC 425","Algorithms and Data Structures");
        Course course6 = new Course(400,"CSC 423","Graphics and Animation");
        Course course7 = new Course(400,"CSC 431","idk");
        Course course8 = new Course(400,"TMC 431","Total Man Concept");
        Course course9 = new Course(400,"TMC 432","TMC SPORTS");
        Course course0 = new Course(400,"EDS 431","Entrepreneurial Development Studies");

        coursesList.add(course1);
        coursesList.add(course2);
        coursesList.add(course3);
        coursesList.add(course4);
        coursesList.add(course5);
        coursesList.add(course6);
        coursesList.add(course7);
        coursesList.add(course8);
        coursesList.add(course9);
        coursesList.add(course0);

        Program program = programRepository.findProgramByName(ProgramEnum.Computer_Science.name().replace('_',' '));//get computer science program
        program.addCourse(course1);
        program.addCourse(course2);
        program.addCourse(course3);
        program.addCourse(course4);
        program.addCourse(course5);
        program.addCourse(course6);
        program.addCourse(course7);
        program.addCourse(course8);
        program.addCourse(course9);
        program.addCourse(course0);

        courseRepository.saveAll(coursesList);


    }
}
