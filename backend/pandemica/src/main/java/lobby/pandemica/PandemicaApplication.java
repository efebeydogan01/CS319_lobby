package lobby.pandemica;

import lobby.pandemica.dto.UserDto;
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
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.create(new UserDto(UUID.randomUUID(),"elif beydogan", "password",
					21212122, new Date(System.currentTimeMillis()), "53112312123"));
			userService.create(new UserDto(UUID.randomUUID(),"efe beydogan", "password",
					29992122, new Date(System.currentTimeMillis()), "53112312123"));
			userService.create(new UserDto(UUID.randomUUID(),"cagtay beydogan", "password",
					21000022, new Date(System.currentTimeMillis()), "53112312123"));
			userService.create(new UserDto(UUID.randomUUID(),"mama polat", "password",
					21212111, new Date(System.currentTimeMillis()), "53112312123"));
		};
	}
}
