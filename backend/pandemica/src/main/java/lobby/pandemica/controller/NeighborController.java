package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.NeighborDto;
import lobby.pandemica.service.NeighborService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("neighbor")
public class NeighborController extends BaseController<NeighborDto>
{
    private final NeighborService neighborService;

    public NeighborController(NeighborService neighborService) {
        super(neighborService);
        this.neighborService = neighborService;
    }
}
