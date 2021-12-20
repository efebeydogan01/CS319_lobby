package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.StudentDto;
import lobby.pandemica.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController extends BaseController<StudentDto> {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        super(studentService);
        this.studentService = studentService;
    }
}
