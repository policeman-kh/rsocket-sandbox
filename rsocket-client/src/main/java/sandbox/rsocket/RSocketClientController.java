package sandbox.rsocket;

import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class RSocketClientController {
    private final WebClient webClient;

    /**
     * Get response mono data.
     */
    @GetMapping("/mono")
    public Mono<ResponseData> mono(@RequestParam String message) {
        return webClient.getMono(new RequestData(message));
    }

    /**
     * Get response flux data with server sent events.
     */
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/flux")
    public Flux<ResponseData> flux(@RequestParam String message) {
        return webClient.getFlux(new RequestData(message));
    }

    interface RSocketClient {
        Mono<ResponseData> getMono(RequestData data);

        Flux<ResponseData> getFlux(RequestData data);
    }

    @Component
    @AllArgsConstructor
    static class WebClient implements RSocketClient {
        private final RSocketRequester rSocketRequester;

        @Override
        public Mono<ResponseData> getMono(RequestData data) {
            return rSocketRequester.route("getMono")
                                   .data(data)
                                   .retrieveMono(ResponseData.class);
        }

        @Override
        public Flux<ResponseData> getFlux(RequestData data) {
            return rSocketRequester.route("getFlux")
                                   .data(data)
                                   .retrieveFlux(ResponseData.class);
        }
    }
}
