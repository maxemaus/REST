package JM313.Rest;

import JM313.Rest.service.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

	public static UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
