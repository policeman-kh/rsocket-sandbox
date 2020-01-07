package sandbox.rsocket;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class RSocketClientController {
    private final RSocketClientService clientService;

    /**
     * Get response mono data.
     */
    @GetMapping("/mono")
    public Mono<ResponseData> mono(@RequestParam String message) {
        return clientService.getMono(new RequestData(message));
    }

    /**
     * Get response flux data with server sent events.
     */
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/flux")
    public Flux<ResponseData> flux(@RequestParam String message) {
        return clientService.getFlux(new RequestData(message));
    }
}
