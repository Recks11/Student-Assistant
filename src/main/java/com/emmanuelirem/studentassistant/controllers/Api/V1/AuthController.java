package com.emmanuelirem.studentassistant.controllers.Api.V1;

import com.emmanuelirem.studentassistant.models.Lecturer;
import com.emmanuelirem.studentassistant.models.Student;
import com.emmanuelirem.studentassistant.services.data.LecturerService;
import com.emmanuelirem.studentassistant.services.data.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final StudentService studentService;
    private final LecturerService lecturerService;

    public AuthController(StudentService studentService,
                          LecturerService lecturerService) {
        this.studentService = studentService;
        this.lecturerService = lecturerService;
    }

    @PostMapping("/register/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> registerStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("/register/lecturer")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Lecturer> registerLecturer(@RequestBody Student student) {
        return lecturerService.fromStudent(student).flatMap(lecturerService::save);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Map> current(@AuthenticationPrincipal Mono<Principal> principal) {
        System.out.println("In");
        return principal
                .map( user -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", user.getName());
                    map.put("roles", AuthorityUtils.authorityListToSet(((Authentication) user)
                            .getAuthorities()));
                    return map;
                });
    }

    @GetMapping("/logout")
    public Mono<Void> logout(WebSession session) {
        return session.invalidate();
    }

}
