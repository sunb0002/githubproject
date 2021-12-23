package com.precure.controllers.internal;

import com.precure.controllers.GeneralResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/home")
public class HomeController {

    @ApiOperation(tags = "Home", value = "Say hi to precure by param ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Invalid Request (BAD_REQUEST)", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Error", response = GeneralResponse.class)
    })
    @GetMapping("/hi")
    public GeneralResponse hi(@ApiParam(value = "id to query") @RequestParam(name = "id", required = false, defaultValue = "999") String id) {
        return new GeneralResponse("hi me-" + id);
    }

    @ApiOperation(tags = "Home", value = "Say hi to precure by path ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Invalid Request (BAD_REQUEST)", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Error", response = GeneralResponse.class)
    })
    @GetMapping("/hi/{id}")
    public GeneralResponse hi2(@ApiParam(value = "id to query", required = true) @PathVariable String id) {
        return new GeneralResponse("hi me-" + id);
    }

}
