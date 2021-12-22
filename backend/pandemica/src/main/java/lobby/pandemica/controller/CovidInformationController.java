package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.CovidInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("covid")
public class CovidInformationController extends BaseController<CovidInformationDto>
{
    private final CovidInformationService covidInformationService;

    public CovidInformationController(CovidInformationService covidInformationService) {
        super(covidInformationService);
        this.covidInformationService = covidInformationService;
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<RestResponse<CovidInformationDto>> get(@PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(covidInformationService.get(UUID.fromString(id)), "Get",
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
