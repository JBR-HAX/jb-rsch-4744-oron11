package org.jetbrains.assignment.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.assignment.service.RobotService;
import org.jetbrains.assignment.web.request.LocationRequest;
import org.jetbrains.assignment.web.request.MoveRequest;
import org.jetbrains.assignment.web.response.LocationResponse;
import org.jetbrains.assignment.web.response.MoveResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RobotController {

    private final RobotService robotService;

    @PostMapping("/locations")
    public List<LocationResponse> getLocations(@Valid @RequestBody @NotEmpty List<MoveRequest> moveRequests) {
        log.info("Get locations, moveRequests={}", moveRequests);
        return robotService.getLocations(moveRequests);
    }

    @PostMapping("/moves")
    public List<MoveResponse> getMoves(@Valid @RequestBody @NotEmpty List<LocationRequest> locationRequests) {
        log.info("Get locations, locationRequests={}", locationRequests);
        return robotService.getMoves(locationRequests);
    }
}
