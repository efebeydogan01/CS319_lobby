package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.TempDetails;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.UserService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		super(userRepository, UserMapper.INSTANCE);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public UserDto get(TempDetails details) throws EntityNotFoundException
	{
		if (details.getBilkentId() == null || details.getPassword() == null )
		{
			LOGGER.warn("Credentials cannot be empty!");
			throw new EntityNotFoundException();
		}
		Optional<User> userOptional = userRepository.findByBilkentId(details.getBilkentId());
		if (!userOptional.isPresent()) {
			LOGGER.warn("No such user!");
			throw new EntityNotFoundException();
		}
		UserDto candidateUser = userMapper.entityToDto(userOptional.get());
		if (!candidateUser.getPassword().equals(details.getPassword()))
		{
			LOGGER.warn("Password does not match!");
			throw new EntityNotFoundException();
		}
		return candidateUser;
	}
}
