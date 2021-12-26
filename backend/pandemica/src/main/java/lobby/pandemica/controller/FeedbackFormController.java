package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.FeedbackFormDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.FeedbackFormService;
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
 * Controller for Feedback Forms, extends from Base Controller
 */
@RestController
@RequestMapping("feedback_form")
public class FeedbackFormController extends BaseController<FeedbackFormDto>
{
	private final FeedbackFormService feedbackFormService;

	public FeedbackFormController(BaseCrudService<FeedbackFormDto> baseCrudService, FeedbackFormService feedbackFormService)
	{
		super(baseCrudService);
		this.feedbackFormService = feedbackFormService;
	}

	/**
	 * Reads all the feedback forms a user created
	 * @param id the users id
	 * @return the feedback forms the user with UUID id created
	 */
	@GetMapping("readAllFromUser/{id}")
	public ResponseEntity<RestResponse<List<FeedbackFormDto>>> readAllFromUser(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(feedbackFormService.readAllFromUser(UUID.fromString(id)), "Get",
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
