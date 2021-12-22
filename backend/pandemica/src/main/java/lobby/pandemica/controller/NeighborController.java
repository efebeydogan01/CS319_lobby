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

@RestController
@RequestMapping("neighbor")
public class NeighborController extends BaseController<NeighborDto>
{
    private final NeighborService neighborService;

    public NeighborController(NeighborService neighborService) {
        super(neighborService);
        this.neighborService = neighborService;
    }

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
