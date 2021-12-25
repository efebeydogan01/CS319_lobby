package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.MedicalEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

/**
 * Controller for Medical Employees, extends from Base Controller
 */
@RestController
@RequestMapping("medical_employee")
public class MedicalEmployeeController extends BaseController<MedicalEmployeeDto> {
    private final MedicalEmployeeService medicalEmployeeService;

    public MedicalEmployeeController(MedicalEmployeeService medicalEmployeeService) {
        super(medicalEmployeeService);
        this.medicalEmployeeService = medicalEmployeeService;
    }

    /**
     * Reads a users specific attributes of an medical employee
     * @param id is the UUID of the USER, not the respective medical employee
     * @return the medical employee associated with the user that has the UUID id
     */
    @Override
    @GetMapping("read/{id}")
    public ResponseEntity<RestResponse<MedicalEmployeeDto>> read(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(medicalEmployeeService.getUserWithRole(UUID.fromString(id)), "Get",
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
