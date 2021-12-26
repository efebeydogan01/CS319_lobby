package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.db.TempDetails;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.dto.base.RestResponse;
import lobby.pandemica.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

/**
 * Controller for Users, extends from Base Controller
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserDto>
{
	private final UserService userService;

	public UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}

	/**
	 * gets the user with the given details, used for authentication
	 * @param details of the user
	 * @return the user with the given details.
	 */
	@PostMapping(value = "get")
	public ResponseEntity<RestResponse<UserDto>> get(@RequestBody TempDetails details)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(userService.get(details), "Get",
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
