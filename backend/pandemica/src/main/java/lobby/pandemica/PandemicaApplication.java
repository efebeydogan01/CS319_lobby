package lobby.pandemica;

import lobby.pandemica.db.Role;
import lobby.pandemica.db.Status;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.AcademicPersonnelService;
import lobby.pandemica.service.CovidInformationService;
import lobby.pandemica.service.SectionService;
import lobby.pandemica.service.UserService;
import lobby.pandemica.dto.*;
import lobby.pandemica.service.*;
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
						  StudentService studentService,
						  AdminService adminService,
						  MedicalEmployeeService medicalEmployeeService,
						  AcademicPersonnelService academicPersonnelService,
						  SectionService sectionService) {
		return args -> {
			Integer dummyAge = 0;
			UserDto userDto1 = new UserDto(UUID.randomUUID(),"Mert Barkın Er", "password",
					21900000, new Date(101,8,3),
					"53112312123",0, "STUDENT");
			UserDto userDto2 = new UserDto(UUID.randomUUID(),"Efe Beydoğan", "password",
					21900001, new Date(101,3,26),
					"53112312123",1, "ADMIN");
			UserDto userDto3 = new UserDto(UUID.randomUUID(),"Arda Önal", "password",
					21900002, new Date(101,0,13),
					"53112312123",4, "MEDICAL_EMPLOYEE");
			UserDto userDto4 = new UserDto(UUID.randomUUID(),"Eren Polat", "password",
					21900003, new Date(101,8,19),
					"53112312123",0,"STUDENT");
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
			/*
//			StudentDto studentDto1 = new StudentDto(UUID.randomUUID(), userDto1, "CS", "3");
//			studentService.create(studentDto1);
//			AdminDto adminDto = new AdminDto(UUID.randomUUID(), userDto2);
//			adminService.create(adminDto);
//			MedicalEmployeeDto medicalEmployeeDto = new MedicalEmployeeDto(UUID.randomUUID(), userDto3, "KBB", "asda");
//			medicalEmployeeService.create(medicalEmployeeDto);
//			StudentDto studentDto2 = new StudentDto(UUID.randomUUID(), userDto4, "CS", "3");
//			studentService.create(studentDto2);
//			covidInformationService.create(covidInformationDto5);
			*/
			AcademicPersonnelDto academicPersonnelDto = new AcademicPersonnelDto(UUID.randomUUID(), "CS", userDto5);
			academicPersonnelService.create(academicPersonnelDto);

			SectionDto sectionDto1 = new SectionDto(UUID.randomUUID(), "CS319", 1, "B-204", academicPersonnelDto);
			SectionDto sectionDto2 = new SectionDto(UUID.randomUUID(), "CS319", 2, "B-204", academicPersonnelDto);
			SectionDto sectionDto3 = new SectionDto(UUID.randomUUID(), "CS319", 3, "B-204", academicPersonnelDto);
			SectionDto sectionDto4 = new SectionDto(UUID.randomUUID(), "CS319", 4, "EE-214", academicPersonnelDto);
			sectionService.create(sectionDto1);
			sectionService.create(sectionDto2);
			sectionService.create(sectionDto3);
			sectionService.create(sectionDto4);

		};
	}
}
