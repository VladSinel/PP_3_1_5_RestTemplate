package restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import restclient.DTO.User;
import restclient.config.ClientConfig;
import restclient.service.Communicator;

import java.util.List;

@SpringBootApplication
public class Pp315RestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(Pp315RestTemplateApplication.class, args);

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);

		Communicator communicator =  context.getBean("communicator", Communicator.class);
		User user = new User(3, "James", "Brown", (byte)1211);
		User user1 = new User(3, "Thomas", "Shelby", (byte)1211);

		communicator.getSessionId();
		List<User> allUsers = communicator.allUsers();
		System.out.println(allUsers);
		communicator.addUser(user);
		communicator.editUser(user1);
		communicator.deleteUser(3);
	}

}
