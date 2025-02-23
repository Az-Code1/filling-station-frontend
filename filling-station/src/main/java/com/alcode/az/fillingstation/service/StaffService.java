package com.alcode.az.fillingstation.service;


import com.alcode.az.fillingstation.model.Staff;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

public class StaffService {
    private static final String API_URL = "http://localhost:8080/api/staff";
    private final RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Staff> getAllStaff() {
        System.out.println("StaffService.getAllStaff method entered");
        Staff[] staff = restTemplate.getForObject(API_URL, Staff[].class);
        System.out.println("StaffService.getAllStaff after restTemplate.getForObject called" + Arrays.toString(staff));
        assert staff != null;
        return Arrays.asList(staff);
    }

    public void saveStaff(Staff staff) {
        try {
            // Register the JavaTimeModule to handle Java 8 date/time types
            objectMapper.registerModule(new JavaTimeModule());
            String jsonStaff = objectMapper.writeValueAsString(staff);
            System.out.println("StaffService.saveStaff method entered " + staff.getStaffId());

            restTemplate.getMessageConverters().stream()
                    .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                    .forEach(converter -> ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper));

            //restTemplate.postForObject(API_URL, jsonStaff, Staff.class);
            System.out.println("StaffService.saveStaff after restTemplate.postForObject called " + jsonStaff);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStaff(Staff staff) {
        System.out.println("StaffService.updateStaff method entered " + staff.getStaffId()
        +"\n"+ staff.getStaffSurname());
        System.out.println(API_URL + "/" + staff.getStaffId() + staff);
        restTemplate.put(API_URL + "/" + staff.getStaffId(), staff);
    }

    public void deleteStaff(long staffId) {
        restTemplate.delete(API_URL + "/" + staffId);
    }
}