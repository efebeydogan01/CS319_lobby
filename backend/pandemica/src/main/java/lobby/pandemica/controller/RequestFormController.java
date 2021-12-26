package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.RequestFormDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.RequestFormService;
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
 * Controller for Request Forms, extends from Base Controller
 */
@RestController
@RequestMapping("request_form")
public class RequestFormController extends BaseController<RequestFormDto>
{
	private final RequestFormService requestFormService;

	public RequestFormController(BaseCrudService<RequestFormDto> baseCrudService, RequestFormService requestFormService)
	{
		super(baseCrudService);
		this.requestFormService = requestFormService;
	}

	/**
	 * Returns all the  request forms the user with UUID id created.
	 * @param id the user with the UUID id
	 * @return the list of request forms created by the user with the UUID id
	 */
	@GetMapping("readAllFromUser/{id}")
	public ResponseEntity<RestResponse<List<RequestFormDto>>> readAllFromUser(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(requestFormService.readAllFromUser(UUID.fromString(id)), "Get",
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
