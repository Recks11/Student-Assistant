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

    @Autowired
    public initTemporaryDbData(StudentRepository studentRepository,
                               UsersService usersService, RolesService rolesService,
                               EncoderService encoderService,
                               ProgramRepository programRepository) {
        this.studentRepository = studentRepository;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.encoderService = encoderService;
        this.programRepository = programRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //call methods here
        addRandomStudents();
        addUniversityCoursesDepartmentsAndColleges();
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

}
