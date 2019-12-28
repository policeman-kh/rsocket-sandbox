package sandbox.rsocket.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sandbox.rsocket.RequestData;
import sandbox.rsocket.ResponseData;

public interface RSocketClient {
    Mono<ResponseData> getMono(RequestData data);

    Flux<ResponseData> getFlux(RequestData data);
}
