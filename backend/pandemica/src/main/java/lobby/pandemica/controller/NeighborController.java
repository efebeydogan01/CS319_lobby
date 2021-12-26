package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.NeighborService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

/**
 * Controller for Neighbor, extends from Base Controller
 */
@RestController
@RequestMapping("neighbor")
public class NeighborController extends BaseController<NeighborDto>
{
    private final NeighborService neighborService;

    public NeighborController(NeighborService neighborService) {
        super(neighborService);
        this.neighborService = neighborService;
    }

    /**
     * Finds the risk status of a student user, since other types of users can not have neighbors.
     * @param id the UUID of the user
     * @return True if the user is risk-free, False if the user is at risk due to one of their neighbors having COVID
     */
    @GetMapping("getRiskStatus/{id}")
    public ResponseEntity<RestResponse<Boolean>> getRiskStatus(@PathVariable String id){
        try
        {
            return new ResponseEntity<>(
                    new RestResponse<>(neighborService.getRiskStatus(UUID.fromString(id)),"Getting Risk Status", "Getting Risk Status was successful."),
                    HttpStatus.OK);
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Getting Risk Status",
                    "Getting Risk Status was unsuccessful due to an error with the entities given."),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new RestResponse<>(null, "Getting Risk Status", "There was an unexpected error."),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}
