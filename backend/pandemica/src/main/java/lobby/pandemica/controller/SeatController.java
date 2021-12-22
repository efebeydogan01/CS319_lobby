package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.SeatDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.service.SeatService;
import lobby.pandemica.service.SectionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seat")
public class SeatController extends BaseController<SeatDto> {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        super(seatService);
        this.seatService = seatService;
    }
}
