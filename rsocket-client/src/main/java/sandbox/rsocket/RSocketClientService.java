package sandbox.rsocket;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class RSocketClientService {
    private final RSocketRequester rSocketRequester;

    public Mono<ResponseData> getMono(RequestData data) {
        return rSocketRequester.route("getMono")
                               .data(data)
                               .retrieveMono(ResponseData.class);
    }

    public Flux<ResponseData> getFlux(RequestData data) {
        return rSocketRequester.route("getFlux")
                               .data(data)
                               .retrieveFlux(ResponseData.class);
    }
}
