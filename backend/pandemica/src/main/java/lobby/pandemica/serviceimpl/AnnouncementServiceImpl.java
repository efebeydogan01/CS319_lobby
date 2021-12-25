package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.AnnouncementDto;
import lobby.pandemica.repository.AnnouncementRepository;
import lobby.pandemica.repository.CovidInformationRepository;
import lobby.pandemica.repository.UserRepository;
import lobby.pandemica.service.AnnouncementService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AnnouncementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnnouncementServiceImpl extends BaseServiceImpl<Announcement, AnnouncementDto> implements AnnouncementService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AcademicPersonnelServiceImpl.class);

	private final AnnouncementMapper announcementMapper;
	private final AnnouncementRepository announcementRepository;
	private final CovidInformationRepository covidInformationRepository;
	private final UserRepository userRepository;

	public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper,
								   CovidInformationRepository covidInformationRepository, UserRepository userRepository) {
		super(announcementRepository, AnnouncementMapper.INSTANCE);
		this.announcementRepository = announcementRepository;
		this.announcementMapper = announcementMapper;
		this.covidInformationRepository = covidInformationRepository;
		this.userRepository = userRepository;
	}
	@Override
	public AnnouncementDto create(AnnouncementDto dto) throws EntityNotFoundException
	{

		Announcement entity = AnnouncementMapper.INSTANCE.dtoToEntity(dto);
		if (entity == null) {
			LOGGER.warn("The entity to save cannot be empty!");
			throw new EntityNotFoundException();
		}
		return super.create(AnnouncementMapper.INSTANCE.entityToDto(entity));
	}

	public AnnouncementDto get(UUID id) throws EntityNotFoundException
	{
		if (id == null)
		{
			LOGGER.warn("The id cannot be empty!");
			throw new EntityNotFoundException();
		}
		Optional<Announcement> announcementOptional = announcementRepository.findById(id);
		if (!announcementOptional.isPresent()) {
			LOGGER.warn("No such announcement!");
			throw new EntityNotFoundException();
		}
		return announcementMapper.entityToDto(announcementOptional.get());
	}

	@Override
	public GeneralInfo readGeneralInfo()
	{
		Integer academicCases = 0;
		Integer adminCases = 0;
		Integer staffCases = 0;
		Integer studentCases = 0;
		Integer vaccinationRate = 72;
		GeneralInfo generalInfo = new GeneralInfo();
		generalInfo.setAnnouncements(announcementRepository.findAll());
		List<CovidInformation> covidInformationList = covidInformationRepository.findAllByStatus(Status.RISK.POSITIVE.name());
		for (CovidInformation covidInformation: covidInformationList)
		{
			UUID userId = covidInformation.getUser().getId();
			User user = userRepository.getById(userId);
			switch (user.getRole()){
				case "ACADEMIC_PERSONNEL":
					academicCases++;
					break;
				case "ADMIN":
					adminCases++;
					break;
				case "STAFF":
					staffCases++;
					break;
				case "STUDENT":
					studentCases++;
					break;
				default:
					break;
			}
		}
		generalInfo.setAcademicCases(academicCases);
		generalInfo.setAdminCases(adminCases);
		generalInfo.setStudentCases(studentCases);
		generalInfo.setStaffCases(staffCases);
		generalInfo.setVaccinationRate(vaccinationRate);
		return generalInfo;
	}

}