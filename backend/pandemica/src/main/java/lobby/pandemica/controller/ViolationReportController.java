package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.ViolationReportDto;
import lobby.pandemica.service.ViolationFormService;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("violation_report")
public class ViolationReportController extends BaseController<ViolationReportDto>
{
	private final ViolationFormService violationFormService;

	public ViolationReportController(BaseCrudService<ViolationReportDto> baseCrudService, ViolationFormService violationFormService)
	{
		super(baseCrudService);
		this.violationFormService = violationFormService;
	}
}
