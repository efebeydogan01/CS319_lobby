package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.service.CovidInformationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("covidInformation")
public class CovidInformationController extends BaseController<CovidInformationDto>
{
    private final CovidInformationService covidInformationService;

    public CovidInformationController(CovidInformationService covidInformationService) {
        super(covidInformationService);
        this.covidInformationService = covidInformationService;
    }
}
