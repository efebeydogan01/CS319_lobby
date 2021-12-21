package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.service.MedicalEmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medical_employee")
public class MedicalEmployeeController extends BaseController<MedicalEmployeeDto> {
    private final MedicalEmployeeService medicalEmployeeService;

    public MedicalEmployeeController(MedicalEmployeeService medicalEmployeeService) {
        super(medicalEmployeeService);
        this.medicalEmployeeService = medicalEmployeeService;
    }
}
