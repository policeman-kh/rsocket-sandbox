package sandbox.rsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.rsocket.RSocketRequester;

@SpringBootApplication
public class RSocketApplication {
	public static void main(String[] args) {
		SpringApplication.run(RSocketApplication.class, args);
	}

}
