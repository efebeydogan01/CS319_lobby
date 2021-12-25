package lobby.pandemica.serviceimpl;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.AnnouncementDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.*;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.service.AnnouncementService;
import lobby.pandemica.service.VaccineInformationService;
import lobby.pandemica.serviceimpl.base.BaseServiceImpl;
import lobby.pandemica.serviceimpl.mapper.AcademicPersonnelMapper;
import lobby.pandemica.serviceimpl.mapper.AnnouncementMapper;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.java2d.loops.FillRect;

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
	private final VaccineInformationService vaccineInformationService;
	private final VaccineInformationRepository vaccineInformationRepository;

	public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper,
								   CovidInformationRepository covidInformationRepository, UserRepository userRepository,
								   VaccineInformationService vaccineInformationService, VaccineInformationRepository vaccineInformationRepository) {
		super(announcementRepository, AnnouncementMapper.INSTANCE);
		this.announcementRepository = announcementRepository;
		this.announcementMapper = announcementMapper;
		this.covidInformationRepository = covidInformationRepository;
		this.userRepository = userRepository;
		this.vaccineInformationService = vaccineInformationService;
		this.vaccineInformationRepository = vaccineInformationRepository;
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
		int academicCases = 0;
		int adminCases = 0;
		int staffCases = 0;
		int studentCases = 0;

		double vaccinationRate1 = 0;
		double vaccinationRate2 = 0;
		int vaccinatedCount1 = 0;
		int vaccinatedCount2 = 0;
		List<User> infoUsers = userRepository.findAll();
		if (infoUsers != null) {
			for (int i = 0; i < infoUsers.size(); i++) {
				User user = infoUsers.get(i);
				List<VaccineInformation> vaccineInformationList = vaccineInformationRepository.findAllByUserId(user.getId());
				if (vaccineInformationList != null) {
					if (vaccineInformationList.size() >= 1) {
						vaccinatedCount1++;
						if (vaccineInformationList.size() >= 2)
							vaccinatedCount2++;
					}
				}
			}
			vaccinationRate1 = 100.0 * vaccinatedCount1 / infoUsers.size();
			vaccinationRate1 = (double) Math.round(vaccinationRate1 * 10d) / 10d;
			vaccinationRate2 = 100.0 * vaccinatedCount2 / infoUsers.size();
			vaccinationRate2 = (double) Math.round(vaccinationRate2 * 10d) / 10d;
		}

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
		generalInfo.setVaccinationRate1(vaccinationRate1);
		generalInfo.setVaccinationRate2(vaccinationRate2);
		return generalInfo;
	}

}