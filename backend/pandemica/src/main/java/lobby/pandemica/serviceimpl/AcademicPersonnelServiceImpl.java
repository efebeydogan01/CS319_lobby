package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.AcademicPersonnel;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.repository.AcademicPersonnelRepository;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AcademicPersonnelServiceImpl extends BaseServiceImpl<AcademicPersonnel, AcademicPersonnelDto> implements AcademicPersonnelService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicPersonnelServiceImpl.class);

    private final AcademicPersonnelRepository academicPersonnelRepository;

    public AcademicPersonnelServiceImpl(AcademicPersonnelRepository academicPersonnelRepository) {
        super(academicPersonnelRepository, AcademicPersonnelMapper.INSTANCE);
        this.academicPersonnelRepository = academicPersonnelRepository;
    }
}