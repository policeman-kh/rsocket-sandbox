package sandbox.rsocket;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Controller
public class RSocketController {
    @MessageMapping("getMono")
    public Mono<ResponseData> getMono(RequestData requestData) {
        log.info("Calling getMono method. request={}", requestData);
        return Mono.just(new ResponseData(requestData.getMessage()));
    }

    @MessageMapping("getFlux")
    public Flux<ResponseData> getFlux(RequestData requestData) {
        log.info("Calling getFlux method. request={}", requestData);
        final List<ResponseData> list = List.of(new ResponseData(requestData.getMessage() + "_1"),
                                                new ResponseData(requestData.getMessage() + "_2"),
                                                new ResponseData(requestData.getMessage() + "_3"));
        return Flux.fromIterable(list);
    }
}
