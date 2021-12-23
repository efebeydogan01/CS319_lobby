package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController<AdminDto> {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        super(adminService);
        this.adminService = adminService;
    }

    @Override
    @GetMapping("read/{id}")
    public ResponseEntity<RestResponse<AdminDto>> read(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(adminService.getUserWithRole(UUID.fromString(id)), "Get",
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
