package com.tset.api.release_manager.web.v1.controller;

import com.tset.api.release_manager.data.ServiceReleaseRepository;
import com.tset.api.release_manager.data.model.ServiceRelease;
import com.tset.api.release_manager.service.ReleaseService;
import com.tset.api.release_manager.web.v1.model.ServiceReleaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, path = "/api/v1/releases")
public class ReleaseManagerController {

    private final ReleaseService releaseService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceRelease>> getServices(
            @RequestParam(name = "systemVersion", required = false)
            Long systemVersion) {

        return ResponseEntity.ok(releaseService.getServiceReleasesBySystemVersion(systemVersion));
    }

    @PostMapping("/deploy")
    public ResponseEntity<Long> addService(
            @RequestBody ServiceReleaseDto serviceRelease) {

        Long version = releaseService.addServiceRelease(serviceRelease);
        return ResponseEntity.ok(version);
    }
}
