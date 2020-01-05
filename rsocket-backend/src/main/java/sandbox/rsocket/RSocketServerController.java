package sandbox.rsocket;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Controller
public class RSocketServerController {
    @MessageMapping("getMono")
    public Mono<ResponseData> getMono(RequestData requestData) {
        log.info("Calling getMono method. request={}", requestData);
        return Mono.just(new ResponseData(requestData.getMessage()));
    }

    @MessageMapping("getFlux")
    public Flux<ResponseData> getFlux(RequestData requestData) {
        log.info("Calling getFlux method. request={}", requestData);
        final List<ResponseData> list =
                IntStream.rangeClosed(1, 10)
                         .boxed()
                         .map(i -> new ResponseData(requestData.getMessage() + '_' + i))
                         .collect(Collectors.toList());
        return Flux.fromIterable(list)
                   .delayElements(Duration.ofSeconds(1));
    }
}
