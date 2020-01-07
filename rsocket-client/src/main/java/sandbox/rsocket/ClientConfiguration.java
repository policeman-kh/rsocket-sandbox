package sandbox.rsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Configuration
public class ClientConfiguration {
    private final RSocketRequester.Builder builder;

    @Bean
    public RSocketRequester rSocketRequester() {
        return builder.connectTcp("localhost", 7000)
                      .doOnNext(socket -> log.info("Connected to RSocket."))
                      .block();
    }
}
