package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.ExtendedCovidInformation;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.CovidInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * Controller for Covid Information, extends from Base Controller
 */
@RestController
@RequestMapping("covid")
public class CovidInformationController extends BaseController<CovidInformationDto>
{
    private final CovidInformationService covidInformationService;

    public CovidInformationController(CovidInformationService covidInformationService) {
        super(covidInformationService);
        this.covidInformationService = covidInformationService;
    }

    /**
     * Gets the associated covid information from the user with the UUID id
     * @param id the UUID of the user
     * @return the covid information of the user with the UUID id
     */
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

    @GetMapping(value = "extend")
    public ResponseEntity<RestResponse<List<ExtendedCovidInformation>>> getExtendedCovidInformation()
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(covidInformationService.getExtendedCovidInformation(), "Get",
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
