package io.aljavap.fillingStation.service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.aljavap.fillingStation.entity.AccessLog;
import io.aljavap.fillingStation.repository.AccessLogRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class AccessLogService {
	
	
    private final AccessLogRepository accessLogRepository;
    private final RestTemplate restTemplate;

    public AccessLogService(AccessLogRepository accessLogRepository, RestTemplate restTemplate) {
        this.accessLogRepository = accessLogRepository;
        this.restTemplate = restTemplate;
    }

    public AccessLog logAccess(HttpServletRequest request) {
        String ip = getClientIp(request);
        String pcName = getClientPcName();
        IpLocation location = getLocationFromIP(ip);

        AccessLog accessLog = new AccessLog();
        accessLog.setQuery(ip);
        accessLog.setClientPcName(pcName);
        
        if (location != null) {
            accessLog.setStatus(location.getStatus());
            accessLog.setCountry(location.getCountry());
            accessLog.setCountryCode(location.getCountryCode());
            accessLog.setRegion(location.getRegion());
            accessLog.setRegionName(location.getRegionName());
            accessLog.setCity(location.getCity());
            accessLog.setZip(location.getZip());
            accessLog.setLat(location.getLat());
            accessLog.setLon(location.getLon());
            accessLog.setTimezone(location.getTimezone());
            accessLog.setIsp(location.getIsp());
            accessLog.setOrg(location.getOrg());
            accessLog.setAsNumber(location.getAs());
        }

        return accessLogRepository.save(accessLog);
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private String getClientPcName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "Unknown-PC";
        }
    }

    private IpLocation getLocationFromIP(String ip) {
        try {
            String url = "http://ip-api.com/json/" + ip;
            return restTemplate.getForObject(url, IpLocation.class);
        } catch (Exception e) {
            return null;
        }
    }
}
