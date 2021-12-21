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
			UserDto userDto1 = new UserDto(UUID.randomUUID(),"elif beydogan", "password",
					21212122, new Date(System.currentTimeMillis()),
					"53112312123", null);
			UserDto userDto2 = new UserDto(UUID.randomUUID(),"efe beydogan", "password",
					29992122, new Date(System.currentTimeMillis()),
					"53112312123",null);
			UserDto userDto3 = new UserDto(UUID.randomUUID(),"cagtay beydogan", "password",
					21000022, new Date(System.currentTimeMillis()),
					"53112312123",null);
			UserDto userDto4 = new UserDto(UUID.randomUUID(),"mama polat", "password",
					21212111, new Date(System.currentTimeMillis()),
					"53112312123",null);
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
