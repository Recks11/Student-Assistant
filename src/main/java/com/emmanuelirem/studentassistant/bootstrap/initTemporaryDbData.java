package com.emmanuelirem.studentassistant.bootstrap;


import com.emmanuelirem.studentassistant.models.Course;
import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.models.enums.CollegeEnum;
import com.emmanuelirem.studentassistant.models.enums.DepartmentsEnum;
import com.emmanuelirem.studentassistant.models.enums.ProgramEnum;
import com.emmanuelirem.studentassistant.models.enums.SemesterEnum;
import com.emmanuelirem.studentassistant.models.university.College;
import com.emmanuelirem.studentassistant.models.university.Department;
import com.emmanuelirem.studentassistant.models.university.Program;
import com.emmanuelirem.studentassistant.repository.*;
import com.emmanuelirem.studentassistant.services.LecturerService;
import com.emmanuelirem.studentassistant.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class initTemporaryDbData implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final LecturerService lecturerService;
    private final DepartmentRepository departmentRepository;
    private final CollegeRepository collegeRepository;
    private final ProgramRepository programRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final UsersDetailsRepository usersDetailsRepository;

    @Autowired
    public initTemporaryDbData(ProgramRepository programRepository, CourseRepository courseRepository, CollegeRepository collegeRepository, StudentService studentService, LecturerService lecturerService, DepartmentRepository departmentRepository, StudentRepository studentRepository, LecturerRepository lecturerRepository, UsersDetailsRepository usersDetailsRepository) {
        this.programRepository = programRepository;
        this.courseRepository = courseRepository;
        this.collegeRepository = collegeRepository;
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
        this.usersDetailsRepository = usersDetailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //call methods here
        usersDetailsRepository.deleteAll().subscribe();
        programRepository.deleteAll().subscribe();
        courseRepository.deleteAll().subscribe();
        studentRepository.deleteAll().subscribe();
        lecturerRepository.deleteAll().subscribe();
        collegeRepository.deleteAll().subscribe();
        departmentRepository.deleteAll().subscribe();


        addRandomStudents();
        addLecturer();
        addUniversityCoursesDepartmentsAndColleges();
        addCourses();
        addMisCourses();
    }

    private void addLecturer() {
        Lecturer lecturer = new Lecturer("janedoe", "oluwa", "cu00001", "12345", "CST B301");
        Lecturer lecturer2 = new Lecturer("Deez", "nuts", "cu00002", "12345", "CST B302");
        lecturerService.save(lecturer).subscribe();
        lecturerService.save(lecturer2).subscribe();
    }


    private void addRandomStudents() {
        //create 5 random users in memory
        LinkedList<Student> newStudents = new LinkedList<>();
        newStudents.add(new Student("Rex", "Ijiekhuamen", "13cg015928", "Daniel", "B301", "12345", null, new ArrayList<>()));
        newStudents.add(new Student("Emmanuel", "Irem", "13cg015929", "Daniel", "A407", "12345", null, new ArrayList<>()));
        newStudents.add(new Student("Guy", "Random", "14CG083345", "Paul", "F401", "12345", null, new ArrayList<>()));
        newStudents.add(new Student("Girl", "Random", "15AD015928", "Esther", "B301", "12345", null, new ArrayList<>()));
        newStudents.forEach(student -> studentService.save(student).subscribe());
    }

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
        collegeRepository.save(cst).subscribe();
        departmentRepository.save(cmis).subscribe();
        programRepository.saveAll(programList).subscribe();
    }

    private College makeCollege(CollegeEnum name) {
        College college = new College();
        college.setName(name);
        return college;
    }

    private Department makeDepartment(DepartmentsEnum name, College college) {
        Department department = new Department();
        department.setName(name.name().replace('_', ' '));//this line converts the enum to a string by replacing the _ with a blank space
        department.setCollege(college);
        return department;
    }

    private Program makeProgram(ProgramEnum name, Department department) {
        Program program = new Program();
        program.setName(name.name().replace('_', ' '));//this line converts the enum to a string by replacing the _ with a blank space
        program.setDepartment(department);
        return program;
    }

    private void addCourses() {
        List<Course> coursesList = new ArrayList<>();


        Course course1 = new Course(2, 400, "CIS 421", "Computer Security", true, "CIS 421", SemesterEnum.OMEGA);
        Course course2 = new Course(3, 400, "CSC 423", "Concept of Programming", true, "CIS 423", SemesterEnum.OMEGA);
        Course course3 = new Course(3, 400, "CSC 424", "Computer Network/ Communication", true, "csc 424", SemesterEnum.OMEGA);
        Course course4 = new Course(6, 400, "CSC 429", "Project", true, "CSC 429", SemesterEnum.OMEGA);
        Course course5 = new Course(2, 400, "CSC 441", "Human Computer Interaction", true, "CSC 441", SemesterEnum.OMEGA);
        Course course6 = new Course(2, 400, "CSC 422", "Computational Biology & Interdisciplinary Topics", true, "CSC 422", SemesterEnum.OMEGA);
        Course course7 = new Course(2, 400, "CSC 443", "Modelling and Simulation", false, "CSC 443", SemesterEnum.OMEGA);
        Course course8 = new Course(2, 400, "CSC 444", "Computer System Performance Evaluation", false, "CSC 444", SemesterEnum.OMEGA);
        Course course9 = new Course(2, 400, "CSC 445", "Queueing System", false, "CSC 445", SemesterEnum.OMEGA);
        Course course10 = new Course(2, 400, "CSC 446", "Entrepreneurial Development Studies", false, "CSC 446", SemesterEnum.OMEGA);
        Course course11 = new Course(2, 400, "CSC 447", "Formal Model of Computation", false, "CSC 447", SemesterEnum.OMEGA);
        Course course12 = new Course(1, 400, "EDS 421", "Entrepreneurial Development Studies VIII", true, "EDS 421", SemesterEnum.OMEGA);
        Course course13 = new Course(1, 400, "TMC 421", "Total Man Concept VIII", true, "TMC 421", SemesterEnum.OMEGA);
        Course course14 = new Course(1, 400, "TMC 422", "TMC Sports", true, "TMC 422", SemesterEnum.OMEGA);
        Course course15 = new Course(3, 400, "CSC 411", "Software Engineering", true, "CSC 411", SemesterEnum.ALPHA);
        Course course16 = new Course(3, 400, "CSC 413", "Algorithms & Complexity Analysis", true, "CSC 413", SemesterEnum.ALPHA);
        Course course17 = new Course(3, 400, "CSC 415", "Artificial Intelligence", true, "CSC 415", SemesterEnum.ALPHA);
        Course course18 = new Course(3, 400, "CSC 431", "Computaional Science & Numerical Method", true, "CSC 431", SemesterEnum.ALPHA);
        Course course19 = new Course(2, 400, "CSC 432", "File Processing", true, "CSC432", SemesterEnum.ALPHA);
        Course course20 = new Course(3, 400, "CSC 433", "Computer Graphics & Animation", false, "CSC 433", SemesterEnum.ALPHA);
        Course course21 = new Course(2, 400, "MIS 415", "Project Management", false, "MIS 415", SemesterEnum.ALPHA);
        Course course22 = new Course(1, 400, "EDS 411", "Entrepreneurial & Development Studies VII", true, "EDS 411", SemesterEnum.ALPHA);
        Course course23 = new Course(1, 400, "TMC 411", "Total Man Concept", true, "TMC 411", SemesterEnum.ALPHA);
        Course course24 = new Course(0, 400, "TMC 412", "Total Man Concept - Sports", true, "TMC 412", SemesterEnum.ALPHA);


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
        coursesList.add(course15);
        coursesList.add(course16);
        coursesList.add(course17);
        coursesList.add(course18);
        coursesList.add(course19);
        coursesList.add(course20);
        coursesList.add(course21);
        coursesList.add(course22);
        coursesList.add(course23);
        coursesList.add(course24);


        courseRepository.saveAll(coursesList).subscribe();

        programRepository.findProgramByName(ProgramEnum.Computer_Science.name().replace('_', ' ')).map(
                program -> {
                    program.setCourses(coursesList);
                    System.out.println("saved");
                    return programRepository.save(program).subscribe();
                }
        ).subscribe();//get computer science program


    }

    private void addMisCourses() {
        List<Course> miscoursesList = new ArrayList<>();

        Course miscourse1 = new Course(2, 400, "CSC 424", "Computer Network/Communication", true, "CSC 424", SemesterEnum.OMEGA);
        Course miscourse2 = new Course(3, 400, "CSC 411", "Software Engineering", true, "CSC 411", SemesterEnum.ALPHA);
        Course miscourse3 = new Course(3, 400, "MIS 421", "Decision Support System", true, "MIS 421", SemesterEnum.OMEGA);
        Course miscourse4 = new Course(3, 400, "CSC 415", "Artificial Intelligence", true, "MIS 421", SemesterEnum.ALPHA);
        Course miscourse5 = new Course(2, 400, "MIS 422", "Production & Operation Management", true, "MIS 422", SemesterEnum.OMEGA);
        Course miscourse6 = new Course(2, 400, "CSC 423", "File Processing", true, "CSC 423", SemesterEnum.ALPHA);
        Course miscourse7 = new Course(2, 400, "MIS 412", "Knowledge Management", true, "MIS 312", SemesterEnum.ALPHA);
        Course miscourse8 = new Course(2, 400, "MIS 415", "Project Management", true, "MIS 415", SemesterEnum.ALPHA);
        Course miscourse9 = new Course(3, 400, "MIS 418", "E-Commerce Technology", true, "MIS 418", SemesterEnum.ALPHA);
        Course miscourse10 = new Course(2, 400, "MIS 413", "System Accounting", false, "MIS 413", SemesterEnum.ALPHA);
        Course miscourse11 = new Course(2, 400, "MIS 414", "Financial Information System", false, "MIS 414", SemesterEnum.ALPHA);
        Course miscourse12 = new Course(1, 400, "EDS 411", "Entrepreneurial and Development Studies VII", true, "EDS 411", SemesterEnum.ALPHA);
        Course miscourse13 = new Course(1, 400, "TMC 411", "Total Man Concept VII", true, "TMC 411", SemesterEnum.ALPHA);
        Course miscourse14 = new Course(0, 400, "TMC 412", "Total Man Concept - Sports", true, "TMC412", SemesterEnum.ALPHA);
        Course miscourse15 = new Course(2, 400, "MIS 422", "Production & Operation Management", true, "MIS 422", SemesterEnum.OMEGA);
        Course miscourse16 = new Course(3, 400, "MIS 423", "Management Theory", true, "MIS 423", SemesterEnum.OMEGA);
        Course miscourse17 = new Course(6, 400, "MIS 429", "Project", true, "MIS 429", SemesterEnum.OMEGA);
        Course miscourse18 = new Course(3, 300, "BUS 326", "International Business", true, "BUS 326", SemesterEnum.OMEGA);
        Course miscourse19 = new Course(3, 200, "CBS 221", "Statistics For Business and Social Science", false, "CBS 221", SemesterEnum.OMEGA);
        Course miscourse20 = new Course(2, 400, "CSC 444", "Computer System Performance Evaluation", false, "CSC 444", SemesterEnum.OMEGA);
        Course miscourse21 = new Course(2, 400, "CSC 446", "Distributed Computing System", false, "CSC 446", SemesterEnum.OMEGA);
        Course miscourse22 = new Course(2, 400, "MIS 425", "System Security Management", false, "MIS 425", SemesterEnum.OMEGA);
        Course miscourse23 = new Course(2, 400, "MIS 426", "Supply Chain & Logistics Management", false, "MIS 426", SemesterEnum.OMEGA);
        Course miscourse24 = new Course(1, 400, "EDS 421", "Entrepreneurial & Development Studies VIII", true, "EDS 421", SemesterEnum.OMEGA);
        Course miscourse25 = new Course(1, 400, "TMC 421", "Total Man Concept VIII", true, "TMC 421", SemesterEnum.OMEGA);
        Course miscourse26 = new Course(0, 400, "TMC 422", "Total Man Concept - Sports", true, "TMC 422", SemesterEnum.OMEGA);


        miscoursesList.add(miscourse1);
        miscoursesList.add(miscourse2);
        miscoursesList.add(miscourse3);
        miscoursesList.add(miscourse4);
        miscoursesList.add(miscourse5);
        miscoursesList.add(miscourse6);
        miscoursesList.add(miscourse7);
        miscoursesList.add(miscourse8);
        miscoursesList.add(miscourse9);
        miscoursesList.add(miscourse10);
        miscoursesList.add(miscourse11);
        miscoursesList.add(miscourse12);
        miscoursesList.add(miscourse13);
        miscoursesList.add(miscourse14);
        miscoursesList.add(miscourse15);
        miscoursesList.add(miscourse16);
        miscoursesList.add(miscourse17);
        miscoursesList.add(miscourse18);
        miscoursesList.add(miscourse19);
        miscoursesList.add(miscourse20);
        miscoursesList.add(miscourse21);
        miscoursesList.add(miscourse22);
        miscoursesList.add(miscourse23);
        miscoursesList.add(miscourse24);
        miscoursesList.add(miscourse25);
        miscoursesList.add(miscourse26);

        courseRepository.saveAll(miscoursesList).subscribe();

        programRepository.findProgramByName(ProgramEnum.Management_and_Information_Science.name().replace('_', ' '))
                .map(program -> {
                    program.setCourses(miscoursesList);
                    return programRepository.save(program).subscribe();
                }).subscribe();



    }


}
