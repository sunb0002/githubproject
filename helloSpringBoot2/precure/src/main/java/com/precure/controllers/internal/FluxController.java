package com.precure.controllers.internal;

import com.precure.controllers.GeneralResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/flux")
public class FluxController {

    @GetMapping("m")
    public Mono<GeneralResponse> m() {
        GeneralResponse resp = new GeneralResponse("hiflux");
        return Mono.just(resp);
    }

    @GetMapping("f1")
    public Flux<GeneralResponse> f1() {
        List<GeneralResponse> list = Arrays.asList(
                new GeneralResponse("Blossom"),
                new GeneralResponse("Marine"),
                new GeneralResponse("Sunshine")
        );
        return Flux.fromIterable(list);
    }

    @GetMapping("f2")
    public Flux<String> f2() {
        Flux<String> f1 = Flux.just("A", "B", "C", "D");
        Flux<String> f2 = Flux.range(1, 4).map(String::valueOf);
        Flux<String> ans1 = Flux.merge(f1, f2); //ABCD1234 (merge each element by event sequence)
        Flux<String> ans2 = Flux.mergeSequential(f1, f2); //ABCD1234 (append flux after flux)
        Flux<String> ans3 = Flux.zip(f1, f2).flatMap(tp -> Flux.just(tp.getT1(), tp.getT2())); //A1B2C3D4 (interleaved/mixed)
        Flux<String> ans4 = Flux.mergeOrdered((a, b) -> a.compareTo(b), f1, f2); //1234ABCD (compare each element)
        return ans3;
    }

}
