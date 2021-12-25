package lobby.pandemica;

import lobby.pandemica.db.*;
import lobby.pandemica.dto.AcademicPersonnelDto;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.SectionDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.repository.SectionRepository;
import lobby.pandemica.repository.StudentRepository;
import lobby.pandemica.repository.UserRepository;
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

import java.util.*;

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
						  SectionService sectionService,
						  StudentSectionService studentSectionService,
						  SeatService seatService,
						  StudentRepository studentRepository,
						  UserRepository userRepository) {
		return args -> {
			Integer dummyAge = 0;
			UserDto userDto1 = new UserDto(UUID.randomUUID(),"Mert Barkın Er", "password",
					21900000, new Date(101,8,3),
					"53112312123",0, Role.ROLES.STUDENT.name());
			UserDto userDto2 = new UserDto(UUID.randomUUID(),"Efe Beydoğan", "password",
					21900001, new Date(101,3,26),
					"53112312123",1, Role.ROLES.ADMIN.name());
			UserDto userDto3 = new UserDto(UUID.randomUUID(),"Arda Önal", "password",
					21900002, new Date(101,0,13),
					"53112312123",4, Role.ROLES.MEDICAL_EMPLOYEE.name());
			UserDto userDto4 = new UserDto(UUID.randomUUID(),"Eren Polat", "password",
					21900003, new Date(101,8,19),
					"53112312123",0,Role.ROLES.STUDENT.name());
			UserDto userDto5 = new UserDto(UUID.randomUUID(),"Eray Tuzun", "password",
					21900004, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.ACADEMIC_PERSONNEL.name());
			UserDto userDto6 = new UserDto(UUID.randomUUID(),"Gokberk Beydemir", "password",
					21900005, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto7 = new UserDto(UUID.randomUUID(),"Beste Guney", "password",
					21900006, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto8 = new UserDto(UUID.randomUUID(),"Mehmet Tan Guvendik", "password",
					21900007, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto9 = new UserDto(UUID.randomUUID(),"Ece Kiziltepe", "password",
					21900008, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto10 = new UserDto(UUID.randomUUID(),"Gulce Kiziltepe", "password",
					21900009, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto11 = new UserDto(UUID.randomUUID(),"Berke Ucar", "password",
					21900010, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto12 = new UserDto(UUID.randomUUID(),"Elif Beydogan", "password",
					21900011, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto13 = new UserDto(UUID.randomUUID(),"Ozlem Beydemir", "password",
					21900012, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto14 = new UserDto(UUID.randomUUID(),"Mama Polat", "password",
					21900013, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto15 = new UserDto(UUID.randomUUID(),"Sari Ece", "password",
					21900014, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto16 = new UserDto(UUID.randomUUID(),"Elif Deniz", "password",
					21900015, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto17 = new UserDto(UUID.randomUUID(),"Kaan Kurtoglu", "password",
					21900016, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto18 = new UserDto(UUID.randomUUID(),"Gokberk Keskinkilic", "password",
					21900017, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto19 = new UserDto(UUID.randomUUID(),"Emir Melih Erdem", "password",
					21900018, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto20 = new UserDto(UUID.randomUUID(),"Doruk Onur Caliskan", "password",
					21900019, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			UserDto userDto21 = new UserDto(UUID.randomUUID(),"Idil Ece Ucar", "password",
					21900020, new Date(77,8,19),
					"53112312123",dummyAge, Role.ROLES.STUDENT.name());
			userService.create(userDto1);
			userService.create(userDto2);
			userService.create(userDto3);
			userService.create(userDto4);
			userService.create(userDto5);
			userService.create(userDto6);
			userService.create(userDto7);
			userService.create(userDto8);
			userService.create(userDto9);
			userService.create(userDto10);
			userService.create(userDto11);
			userService.create(userDto12);
			userService.create(userDto13);
			userService.create(userDto14);
			userService.create(userDto15);
			userService.create(userDto16);
			userService.create(userDto17);
			userService.create(userDto18);
			userService.create(userDto19);
			userService.create(userDto20);
			userService.create(userDto21);


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
			CovidInformationDto covidInformationDto6 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.POSITIVE.name(), "hes_code_6",false, userDto6);
			CovidInformationDto covidInformationDto7 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.RISKY.name(), "hes_code_7",false, userDto7);
			CovidInformationDto covidInformationDto8 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_8",true, userDto8);
			CovidInformationDto covidInformationDto9 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_9",true, userDto9);
			CovidInformationDto covidInformationDto10 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_10",true, userDto10);
			CovidInformationDto covidInformationDto11 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.POSITIVE.name(), "hes_code_11",false, userDto11);
			CovidInformationDto covidInformationDto12 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.RISKY.name(), "hes_code_12",false, userDto12);
			CovidInformationDto covidInformationDto13 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_13",true, userDto13);
			CovidInformationDto covidInformationDto14 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_14",true, userDto14);
			CovidInformationDto covidInformationDto15 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_15",true, userDto15);
			CovidInformationDto covidInformationDto16 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.POSITIVE.name(), "hes_code_16",false, userDto16);
			CovidInformationDto covidInformationDto17 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.RISKY.name(), "hes_code_17",false, userDto17);
			CovidInformationDto covidInformationDto18 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_18",true, userDto18);
			CovidInformationDto covidInformationDto19 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_19",true, userDto19);
			CovidInformationDto covidInformationDto20 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_20",true, userDto20);
			CovidInformationDto covidInformationDto21 = new CovidInformationDto(UUID.randomUUID(),
					Status.RISK.NEGATIVE.name(), "hes_code_21",true, userDto21);
			covidInformationService.create(covidInformationDto1);
			covidInformationService.create(covidInformationDto2);
			covidInformationService.create(covidInformationDto3);
			covidInformationService.create(covidInformationDto4);
			covidInformationService.create(covidInformationDto5);
			covidInformationService.create(covidInformationDto6);
			covidInformationService.create(covidInformationDto7);
			covidInformationService.create(covidInformationDto8);
			covidInformationService.create(covidInformationDto9);
			covidInformationService.create(covidInformationDto10);
			covidInformationService.create(covidInformationDto11);
			covidInformationService.create(covidInformationDto12);
			covidInformationService.create(covidInformationDto13);
			covidInformationService.create(covidInformationDto14);
			covidInformationService.create(covidInformationDto15);
			covidInformationService.create(covidInformationDto16);
			covidInformationService.create(covidInformationDto17);
			covidInformationService.create(covidInformationDto18);
			covidInformationService.create(covidInformationDto19);
			covidInformationService.create(covidInformationDto20);
			covidInformationService.create(covidInformationDto21);


			AdminDto adminDto = new AdminDto(UUID.randomUUID(), userDto2);
			adminService.create(adminDto);

			MedicalEmployeeDto medicalEmployeeDto = new MedicalEmployeeDto(UUID.randomUUID(), userDto3, "KBB", "asda");
			medicalEmployeeService.create(medicalEmployeeDto);

			AcademicPersonnelDto academicPersonnelDto = new AcademicPersonnelDto(UUID.randomUUID(), "CS", userDto5);
			academicPersonnelService.create(academicPersonnelDto);


			StudentDto studentDto1 = new StudentDto(UUID.randomUUID(), userDto1, "CS", "3");
			StudentDto studentDto2 = new StudentDto(UUID.randomUUID(), userDto4, "CS", "3");
			StudentDto studentDto3 = new StudentDto(UUID.randomUUID(), userDto6, "CS", "3");
			StudentDto studentDto4 = new StudentDto(UUID.randomUUID(), userDto7, "CS", "3");
			StudentDto studentDto5 = new StudentDto(UUID.randomUUID(), userDto8, "CS", "3");
			StudentDto studentDto6 = new StudentDto(UUID.randomUUID(), userDto9, "CS", "3");
			StudentDto studentDto7 = new StudentDto(UUID.randomUUID(), userDto10, "CS", "3");
			StudentDto studentDto8 = new StudentDto(UUID.randomUUID(), userDto11, "CS", "3");
			StudentDto studentDto9 = new StudentDto(UUID.randomUUID(), userDto12, "CS", "3");
			StudentDto studentDto10 = new StudentDto(UUID.randomUUID(), userDto13, "CS", "3");
			StudentDto studentDto11 = new StudentDto(UUID.randomUUID(), userDto14, "CS", "3");
			StudentDto studentDto12 = new StudentDto(UUID.randomUUID(), userDto15, "CS", "3");
			StudentDto studentDto13 = new StudentDto(UUID.randomUUID(), userDto16, "CS", "3");
			StudentDto studentDto14 = new StudentDto(UUID.randomUUID(), userDto17, "CS", "3");
			StudentDto studentDto15 = new StudentDto(UUID.randomUUID(), userDto18, "CS", "3");
			StudentDto studentDto16 = new StudentDto(UUID.randomUUID(), userDto19, "CS", "3");
			StudentDto studentDto17 = new StudentDto(UUID.randomUUID(), userDto20, "CS", "3");
			StudentDto studentDto18 = new StudentDto(UUID.randomUUID(), userDto21, "CS", "3");
			studentService.create(studentDto1);
			studentService.create(studentDto2);
			studentService.create(studentDto3);
			studentService.create(studentDto4);
			studentService.create(studentDto5);
			studentService.create(studentDto6);
			studentService.create(studentDto7);
			studentService.create(studentDto8);
			studentService.create(studentDto9);
			studentService.create(studentDto10);
			studentService.create(studentDto11);
			studentService.create(studentDto12);
			studentService.create(studentDto13);
			studentService.create(studentDto14);
			studentService.create(studentDto15);
			studentService.create(studentDto16);
			studentService.create(studentDto17);
			studentService.create(studentDto18);

			List<StudentDto> studentDtoList = new ArrayList<>();
			studentDtoList.add(studentDto1);
			studentDtoList.add(studentDto2);
			studentDtoList.add(studentDto3);
			studentDtoList.add(studentDto4);
			studentDtoList.add(studentDto5);
			studentDtoList.add(studentDto6);
			studentDtoList.add(studentDto7);
			studentDtoList.add(studentDto8);
			studentDtoList.add(studentDto9);
			studentDtoList.add(studentDto10);
			studentDtoList.add(studentDto11);
			studentDtoList.add(studentDto12);
			studentDtoList.add(studentDto13);
			studentDtoList.add(studentDto14);
			studentDtoList.add(studentDto15);
			studentDtoList.add(studentDto16);
			studentDtoList.add(studentDto17);
			studentDtoList.add(studentDto18);
			
			SectionDto sectionDto1 = new SectionDto(UUID.randomUUID(), "CS319", 1, "B-204", academicPersonnelDto);
			SectionDto sectionDto2 = new SectionDto(UUID.randomUUID(), "CS319", 2, "B-204", academicPersonnelDto);
			SectionDto sectionDto3 = new SectionDto(UUID.randomUUID(), "CS319", 3, "B-204", academicPersonnelDto);
			SectionDto sectionDto4 = new SectionDto(UUID.randomUUID(), "CS319", 4, "EE-214", academicPersonnelDto);
			sectionService.create(sectionDto1);
			sectionService.create(sectionDto2);
			sectionService.create(sectionDto3);
			sectionService.create(sectionDto4);

			List<SectionDto> sectionDtoList = new ArrayList<>();
			sectionDtoList.add(sectionDto1);
			sectionDtoList.add(sectionDto2);
			sectionDtoList.add(sectionDto3);
			sectionDtoList.add(sectionDto4);

			for (int i = 0; i < sectionDtoList.size(); i++) {
				SectionDto sectionDto = sectionDtoList.get(i);
				for (int j = 0; j < studentDtoList.size(); j++) {
					StudentDto studentDto = studentDtoList.get(j);
					StudentSectionDto studentSectionDto = new StudentSectionDto(UUID.randomUUID(), studentDto, sectionDto);
					studentSectionService.create(studentSectionDto);
				}
			}

			Classrooms classrooms = new Classrooms();
			Boolean[][] classroom = classrooms.getClassroom(sectionDto1.getClassroom());
			int rowLength = classroom.length; int columnLength = classroom[0].length;
			int studentIndex = 0; int maxStudentCount = studentDtoList.size();
			for (int i = 0; i < rowLength; i++) {
				for (int j = 0; j < columnLength; j++) {
					if (classroom[i][j] == true && studentIndex < maxStudentCount)  {
						RequestSeat requestSeat = new RequestSeat(sectionDto1.getCourseName(), sectionDto1.getSectionNo(), i, j);
						Optional<User> userOptional = userRepository.findByBilkentId(studentDtoList.get(studentIndex).getUser().getBilkentId());
						seatService.set(requestSeat, userOptional.get().getId());
						studentIndex++;
					}
				}
			}



		};
	}
}
