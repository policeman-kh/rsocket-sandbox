package sandbox.rsocket.client;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sandbox.rsocket.RequestData;
import sandbox.rsocket.ResponseData;

@Component
@AllArgsConstructor
public class WebClient implements RSocketClient {
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
