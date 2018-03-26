package com.emmanuelirem.studentassistant.bootstrap;


import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.enums.CollegeEnum;
import com.emmanuelirem.studentassistant.models.enums.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.enums.ProgramEnum;
import com.emmanuelirem.studentassistant.models.security.Roles;
import com.emmanuelirem.studentassistant.models.security.Users;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.university.*;
import com.emmanuelirem.studentassistant.repository.*;
import com.emmanuelirem.studentassistant.services.EncoderService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class initTemporaryDbData implements ApplicationListener<ContextRefreshedEvent> {

    private EncoderService encoderService;
    private StudentService studentService;
    private ProgramRepository programRepository;
    private UsersService usersService;
    private RolesService rolesService;
    private final CourseRepository courseRepository;

    @Autowired
    public initTemporaryDbData(StudentService studentService,
                               UsersService usersService, RolesService rolesService,
                               EncoderService encoderService,
                               ProgramRepository programRepository, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.encoderService = encoderService;
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
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
            studentService.saveAll(newStudents);
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
//            Department physics = makeDepartment(DepartmentsEnum.Physics, cst);

            //Add Programs here
            programList.add(makeProgram(ProgramEnum.Computer_Science, cmis));
            programList.add(makeProgram(ProgramEnum.Management_and_Information_Science, cmis));
//            programList.add(makeProgram(ProgramEnum.Industrial_Physics, physics));

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
            program.setCourses(new ArrayList<>());
            program.setDepartment(department);
            return program;
        }

        private void addCourses() {
            List<Course> coursesList = new ArrayList<>();

            Course course1 = new Course(2,400,"CIS 421","Computer Security", true);
            Course course2 = new Course(3,400,"CSC 423","Concept of Programming", true);
            Course course3 = new Course(3,400,"CSC 424","Computer Network/ Communication", true);
            Course course4 = new Course(6,400,"CSC 429","Project", true);
            Course course5 = new Course(2,400,"CSC 441","Human Computer Interaction", true);
            Course course6 = new Course(2,400,"CSC 422","Computational Biology & Interdisciplinary Topics", true);
            Course course7 = new Course(2,400,"CSC 443","Modelling and Simulation", false);
            Course course8 = new Course(2,400,"CSC 444","Computer System Performance Evaluation", false);
            Course course9 = new Course(2,400,"CSC 445","Queueing System", false);
            Course course10 = new Course(2,400,"CSC 446","Entrepreneurial Development Studies", false);
            Course course11 = new Course(2,400,"CSC 447","Formal Model of Computation", false);
            Course course12 = new Course(1,400,"EDS 421","Entrepreneurial Development Studies VIII", true);
            Course course13 = new Course(1,400,"TMC 421","Total Man Concept VIII", true);
            Course course14 = new Course(1,400,"TMC 422","TMC Sports",true);

            coursesList.add(course1);
            coursesList.add(course2);
            coursesList.add(course3);
            coursesList.add(course4);
            coursesList.add(course5);
            coursesList.add(course6);
            coursesList.add(course7);
            coursesList.add(course8);
            coursesList.add(course9);
            coursesList.add(course10);
            coursesList.add(course11);
            coursesList.add(course12);
            coursesList.add(course13);
            coursesList.add(course14);

            Program computerScience = programRepository.findProgramByName(ProgramEnum.Computer_Science.name().replace('_',' '));//get computer science program

            computerScience.addCourses(coursesList);

            courseRepository.saveAll(coursesList);


    }
}
