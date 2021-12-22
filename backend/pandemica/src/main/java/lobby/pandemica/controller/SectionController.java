package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.service.SectionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("section")
public class SectionController extends BaseController<SectionDto> {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        super(sectionService);
        this.sectionService = sectionService;
    }
}
