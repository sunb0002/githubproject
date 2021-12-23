package com.precure.controllers.internal;

import com.precure.controllers.GeneralResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/hi")
    public GeneralResponse hi(@RequestParam(name = "id", required = false, defaultValue = "999") String id) {
        return new GeneralResponse("hi me-" + id);
    }

    @GetMapping("/hi/{id}")
    public GeneralResponse hi2(@PathVariable String id) {
        return new GeneralResponse("hi me-" + id);
    }

}
