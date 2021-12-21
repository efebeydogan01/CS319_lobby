package lobby.pandemica;

import lobby.pandemica.db.CovidInformation;
import lobby.pandemica.db.Status;
import lobby.pandemica.db.User;
import lobby.pandemica.dto.CovidInformationDto;
import lobby.pandemica.dto.UserDto;
import lobby.pandemica.service.CovidInformationService;
import lobby.pandemica.service.UserService;
import lobby.pandemica.serviceimpl.mapper.CovidInformationMapper;
import lobby.pandemica.serviceimpl.mapper.UserMapper;
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
	CommandLineRunner run(UserService userService, UserMapper userMapper,
						  CovidInformationService covidInformationService,
						  CovidInformationMapper covidInformationMapper) {
		return args -> {
			UserDto userDto1 = new UserDto(UUID.randomUUID(),"Mert Barkın Er", "password",
					0, new Date(101,8,3),
					"53112312123", 0);
			UserDto userDto2 = new UserDto(UUID.randomUUID(),"Efe Beydoğan", "password",
					1, new Date(101,3,26),
					"53112312123",0);
			UserDto userDto3 = new UserDto(UUID.randomUUID(),"Arda Önal", "password",
					2, new Date(101,0,13),
					"53112312123",0);
			UserDto userDto4 = new UserDto(UUID.randomUUID(),"Eren Polat", "password",
					3, new Date(101,8,19),
					"53112312123",0);
			userService.create(userDto1);
			userService.create(userDto2);
			userService.create(userDto3);
			userService.create(userDto4);

			CovidInformationDto covidInformationDto1 = new CovidInformationDto(UUID.randomUUID(),
					"POSITIVE", "hes_code_1",false, userDto1);
			CovidInformationDto covidInformationDto2 = new CovidInformationDto(UUID.randomUUID(),
					"RISKY", "hes_code_2",false, userDto2);
			CovidInformationDto covidInformationDto3 = new CovidInformationDto(UUID.randomUUID(),
					"NEGATIVE", "hes_code_3",true, userDto3);
			CovidInformationDto covidInformationDto4 = new CovidInformationDto(UUID.randomUUID(),
					"NEGATIVE", "hes_code_4",true, userDto4);
			covidInformationService.create(covidInformationDto1);
			covidInformationService.create(covidInformationDto2);
			covidInformationService.create(covidInformationDto3);
			covidInformationService.create(covidInformationDto4);
		};
	}
}
