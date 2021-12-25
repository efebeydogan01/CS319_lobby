package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.NotificationDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("notification")
public class NotificationController extends BaseController<NotificationDto>
{
	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService)
	{
		super(notificationService);
		this.notificationService = notificationService;
	}

	@GetMapping("get/{id}")
	public ResponseEntity<RestResponse<List<NotificationDto>>> getNotificationsForUser(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(notificationService.getNotificationsForUser(UUID.fromString(id)),"Getting Risk Status", "Getting Risk Status was successful."),
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
