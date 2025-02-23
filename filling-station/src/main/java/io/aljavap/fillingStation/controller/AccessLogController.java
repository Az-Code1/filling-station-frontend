package io.aljavap.fillingStation.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import io.aljavap.fillingStation.entity.AccessLog;
import io.aljavap.fillingStation.service.AccessLogService;

@RestController
@RequestMapping("/access-log")

public class AccessLogController {
	
    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @GetMapping
    public AccessLog logAccess(HttpServletRequest request) {
        return accessLogService.logAccess(request);
    }
}