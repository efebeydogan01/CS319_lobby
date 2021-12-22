package lobby.pandemica;

import lobby.pandemica.db.Role;
import lobby.pandemica.db.Status;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.CovidInformationService;
import lobby.pandemica.service.SectionService;
import lobby.pandemica.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class PandemicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PandemicaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService,
						  CovidInformationService covidInformationService,
						  SectionService sectionService) {
		return args -> {
			Integer dummyAge = 0;
			UserDto userDto1 = new UserDto(UUID.randomUUID(),"Mert Barkın Er", "password",
					21900000, new Date(101,8,3),
					"53112312123", dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto2 = new UserDto(UUID.randomUUID(),"Efe Beydoğan", "password",
					21900001, new Date(101,3,26),
					"53112312123", dummyAge, Role.ROLES.ADMIN.name());
			UserDto userDto3 = new UserDto(UUID.randomUUID(),"Arda Önal", "password",
					21900002, new Date(100,0,13),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto4 = new UserDto(UUID.randomUUID(),"Eren Polat", "password",
					21900003, new Date(95,8,19),
					"53112312123",dummyAge, Role.ROLES.MEDICAL_EMPLOYEE.name());
			UserDto userDto5 = new UserDto(UUID.randomUUID(),"Eray Tuzun", "password",
					21900004, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.ACADEMIC_PERSONNEL.name());
			userService.create(userDto1);
			userService.create(userDto2);
			userService.create(userDto3);
			userService.create(userDto4);
			userService.create(userDto5);

			CovidInformationDto covidInformationDto1 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.POSITIVE.name(), "hes_code_1",false, userDto1);
			CovidInformationDto covidInformationDto2 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.RISKY.name(), "hes_code_2",false, userDto2);
			CovidInformationDto covidInformationDto3 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_3",true, userDto3);
			CovidInformationDto covidInformationDto4 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_4",true, userDto4);
			CovidInformationDto covidInformationDto5 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_5",true, userDto5);
			covidInformationService.create(covidInformationDto1);
			covidInformationService.create(covidInformationDto2);
			covidInformationService.create(covidInformationDto3);
			covidInformationService.create(covidInformationDto4);
			covidInformationService.create(covidInformationDto5);

			SectionDto sectionDto1 = new SectionDto(UUID.randomUUID(), "CS319", 1, "B-204", userDto5);
			SectionDto sectionDto2 = new SectionDto(UUID.randomUUID(), "CS319", 2, "B-204", userDto5);
			SectionDto sectionDto3 = new SectionDto(UUID.randomUUID(), "CS319", 3, "B-204", userDto5);
			SectionDto sectionDto4 = new SectionDto(UUID.randomUUID(), "CS319", 4, "EE-214", userDto5);
			sectionService.create(sectionDto1);
			sectionService.create(sectionDto2);
			sectionService.create(sectionDto3);
			sectionService.create(sectionDto4);

		};
	}
}
