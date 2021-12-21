package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.service.AcademicPersonnelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("academic")
public class AcademicPersonnelController extends BaseController<AcademicPersonnelDto> {
    private final AcademicPersonnelService academicPersonnelService;

    public AcademicPersonnelController(AcademicPersonnelService academicPersonnelService) {
        super(academicPersonnelService);
        this.academicPersonnelService = academicPersonnelService;
    }
}