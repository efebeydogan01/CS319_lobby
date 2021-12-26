package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.ViolationReportDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.ViolationFormService;
import lobby.pandemica.service.base.BaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * Controller for violation reports, extends from Base Controller
 */
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

	/**
	 * gets the list of violation reports created by the user with the UUID id
	 * @param id the UUID of the user
	 * @return the list of violation reports.
	 */
	@GetMapping("readAllFromUser/{id}")
	public ResponseEntity<RestResponse<List<ViolationReportDto>>> readAllFromUser(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(violationFormService.readAllFromUser(UUID.fromString(id)), "Get",
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
