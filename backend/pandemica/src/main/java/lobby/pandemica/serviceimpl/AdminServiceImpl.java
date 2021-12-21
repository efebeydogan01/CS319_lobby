package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.Admin;
import lobby.pandemica.dto.AdminDto;
import lobby.pandemica.repository.AdminRepository;
import lobby.pandemica.service.AdminService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, AdminDto> implements AdminService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        super(adminRepository, AdminMapper.INSTANCE);
        this.adminRepository = adminRepository;
    }
}
