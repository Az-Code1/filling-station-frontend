package com.alcode.az.fillingstation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
public class StaffPunctuality {
    // Getters and Setters
    private String staffId;
    private String name;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private String status; // Punctual, Late, Absent, etc.

    // Constructor
    public StaffPunctuality(String staffId, String name) {
        this.staffId = staffId;
        this.name = name;
    }

}