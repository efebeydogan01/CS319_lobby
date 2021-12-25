package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.db.Section;
import lobby.pandemica.db.SectionWithSeats;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.MedicalEmployeeDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("academic_personnel")
public class AcademicPersonnelController extends BaseController<AcademicPersonnelDto> {

    private final AcademicPersonnelService academicPersonnelService;
    private final SectionService sectionService;

    public AcademicPersonnelController(AcademicPersonnelService academicPersonnelService, SectionService sectionService) {
        super(academicPersonnelService);
        this.academicPersonnelService = academicPersonnelService;
        this.sectionService = sectionService;
    }

    @GetMapping("sections/{id}")
    public ResponseEntity<RestResponse<List<Section>>> getSections(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(sectionService.getSections(UUID.fromString(id)), "Get",
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

    @GetMapping("sections_with_seats/{id}")
    public ResponseEntity<RestResponse<List<SectionWithSeats>>> getSectionsWithSeats(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(academicPersonnelService.getSectionsWithSeats(UUID.fromString(id)), "Get",
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