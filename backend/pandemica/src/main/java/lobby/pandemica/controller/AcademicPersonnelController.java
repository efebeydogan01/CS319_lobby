package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.AcademicPersonnelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("academic_personnel")
public class AcademicPersonnelController extends BaseController<AcademicPersonnelDto> {
    private final AcademicPersonnelService academicPersonnelService;

    public AcademicPersonnelController(AcademicPersonnelService academicPersonnelService) {
        super(academicPersonnelService);
        this.academicPersonnelService = academicPersonnelService;
    }

    @Override
    @GetMapping("read/{id}")
    public ResponseEntity<RestResponse<AcademicPersonnelDto>> read(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(academicPersonnelService.getUserWithRole(UUID.fromString(id)), "Get",
                    "Getting an entity was successful."),
                    HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Get",
                    "Getting entity was unsuccessful due to an error with the entities given."),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Get","There was an unexpected error."),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}