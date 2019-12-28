package sandbox.rsocket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sandbox.rsocket.client.WebClient;

@AllArgsConstructor
@RestController
public class RSocketController {
    private final WebClient webClient;

    @GetMapping("/mono")
    public Mono<ResponseData> mono(@RequestParam String code){
        return webClient.getMono(new RequestData(code));
    }

    @GetMapping("/flux")
    public Flux<ResponseData> flux(@RequestParam String code){
        return webClient.getFlux(new RequestData(code));
    }
}
