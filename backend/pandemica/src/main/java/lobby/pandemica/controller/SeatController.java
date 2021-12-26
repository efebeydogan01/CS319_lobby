package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.RequestSeat;
import lobby.pandemica.db.TempDetails;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.SeatService;
import lobby.pandemica.service.SectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("seat")
public class SeatController extends BaseController<SeatDto> {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        super(seatService);
        this.seatService = seatService;
    }

    @PostMapping(value = "update/{id}")
    public ResponseEntity<RestResponse<SeatDto>> set(@RequestBody RequestSeat requestSeat, @PathVariable String id)
    {
        try
        {
            return new ResponseEntity<>(new RestResponse<>(seatService.set(requestSeat, UUID.fromString(id)), "Get",
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
