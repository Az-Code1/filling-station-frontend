package io.aljavap.fillingStation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.aljavap.fillingStation.entity.Staff;
import io.aljavap.fillingStation.service.StaffService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Create a new staff member
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff savedStaff = staffService.saveStaff(staff);
        return ResponseEntity.ok(savedStaff);
    }

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return ResponseEntity.ok(staffList);
    }

    // Get staff member by ID
    @GetMapping("/{staffId}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long staffId) {
        Optional<Staff> staff = staffService.getStaffById(staffId);
        return staff.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a staff member
    @PutMapping("/{staffId}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long staffId, @RequestBody Staff staffDetails) {
        Staff updatedStaff = staffService.updateStaff(staffId, staffDetails);
        return updatedStaff != null ? ResponseEntity.ok(updatedStaff) : ResponseEntity.notFound().build();
    }

    // Delete a staff member
    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long staffId) {
        staffService.deleteStaff(staffId);
        return ResponseEntity.noContent().build();
    }
}