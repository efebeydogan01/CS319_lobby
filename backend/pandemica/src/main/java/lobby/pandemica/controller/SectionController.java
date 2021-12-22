package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.RequestSeatingPlan;
import lobby.pandemica.db.TempDetails;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("section")
public class SectionController extends BaseController<SectionDto> {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        super(sectionService);
        this.sectionService = sectionService;
    }

    @PostMapping(value = "seating")
    public ResponseEntity<RestResponse<List<SeatDto>>> get(@RequestBody RequestSeatingPlan requestSeatingPlan)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(sectionService.getSeatingPlan(requestSeatingPlan), "Get",
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
