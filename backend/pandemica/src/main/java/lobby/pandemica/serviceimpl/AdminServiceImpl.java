package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.Admin;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.repository.AdminRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AdminService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, AdminDto> implements AdminService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository) {
        super(adminRepository, AdminMapper.INSTANCE);
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdminDto create(AdminDto dto) throws EntityNotFoundException
    {
        Admin entity = AdminMapper.INSTANCE.dtoToEntity(dto);
        if (entity == null) {
            LOGGER.warn("The entity to save cannot be empty!");
            throw new EntityNotFoundException();
        }
        Optional<User> infoUser = userRepository.findByBilkentId(dto.getUser().getBilkentId());
        if (!infoUser.isPresent())
        {
            LOGGER.warn("The user of the admin cannot be empty!");
            throw new EntityNotFoundException();
        }
        entity.setUser(infoUser.get());
        return super.create(AdminMapper.INSTANCE.entityToDto(entity));
    }

    @Override
    public AdminDto getUserWithRole(UUID userId)
    {
        Optional<Admin> optionalAdmin = adminRepository.findByUserId(userId);
        if (!optionalAdmin.isPresent())
        {
            LOGGER.warn("The entity to find does not exist!");
            throw new EntityNotFoundException();
        }
        return AdminMapper.INSTANCE.entityToDto(optionalAdmin.get());
    }
}
