package lobby.pandemica.controller;

import lobby.pandemica.controller.base.BaseController;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserDto>
{
	private final UserService userService;

	public UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}
}
