package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.User;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.UserService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository, UserMapper.INSTANCE);
		this.userRepository = userRepository;
	}
}
