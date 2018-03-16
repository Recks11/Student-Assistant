package com.emmanuelirem.studentassistant.Bootstrap;


import com.emmanuelirem.studentassistant.Models.Security.Roles;
import com.emmanuelirem.studentassistant.Models.Security.Users;
import com.emmanuelirem.studentassistant.Models.Student;
import com.emmanuelirem.studentassistant.Repository.RolesService;
import com.emmanuelirem.studentassistant.Repository.StudentRepository;
import com.emmanuelirem.studentassistant.Repository.UsersService;
import com.emmanuelirem.studentassistant.Services.EncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.LinkedList;

@Component
public class initTemporaryDbData implements ApplicationListener<ContextRefreshedEvent> {

    private EncoderService encoderService;
    private StudentRepository studentRepository;
    private UsersService usersService;
    private RolesService rolesService;

    @Autowired
    public initTemporaryDbData(StudentRepository studentRepository, UsersService usersService, RolesService rolesService, EncoderService encoderService) {
        this.studentRepository = studentRepository;
        this.usersService = usersService;
        this.rolesService = rolesService;
        this.encoderService = encoderService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //call methods here
        addRandomStudents();
    }

    private void addRandomStudents() {
        //create 5 random users in memory
        LinkedList<Student> newStudents = new LinkedList<>();
        newStudents.add(new Student("Rex","Ijiekhuamen","13cg015928","Daniel","B301","12345"));
        newStudents.add(new Student("Emmanuel","Irem","13cg015929","Daniel","A407","12345"));
        newStudents.add(new Student("Guy","Random","14CG083345","Paul","F401","12345"));
        newStudents.add(new Student("Girl","Random","15AD015928","Esther","B301","12345"));
        newStudents.add(new Student("Rex","Ijiekhuamen","13cg015928","Daniel","B301","12345"));

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
}
