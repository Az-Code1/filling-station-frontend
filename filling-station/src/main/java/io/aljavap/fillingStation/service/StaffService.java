package io.aljavap.fillingStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.aljavap.fillingStation.entity.Staff;
import io.aljavap.fillingStation.repository.StaffRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private EntityManager entityManager;


    // Create or save a staff member
    @Transactional
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // Get all staff members
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Get staff member by ID
    public Optional<Staff> getStaffById(Long staffId) {
        return staffRepository.findById(staffId);
    }

    // Update a staff member
    @Transactional
    public Staff updateStaff(Long staffId, Staff staffDetails) {
    	
    	 if (staffDetails.getGuarantor1() != null) {
	        staffDetails.setGuarantor1(entityManager.merge(staffDetails.getGuarantor1()));
	    }
	    if (staffDetails.getGuarantor2() != null) {
	        staffDetails.setGuarantor2(entityManager.merge(staffDetails.getGuarantor2()));
	    }
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            staff.setStaffSurname(staffDetails.getStaffSurname());
            staff.setStaffFirstName(staffDetails.getStaffFirstName());
            staff.setStaffOtherName(staffDetails.getStaffOtherName());
            staff.setStaffGender(staffDetails.getStaffGender());
            staff.setStaffDateOfBirth(staffDetails.getStaffDateOfBirth());
            staff.setStaffNIN(staffDetails.getStaffNIN());
            staff.setStaffPhoneNumber(staffDetails.getStaffPhoneNumber());
            staff.setSecondPhoneNumber(staffDetails.getSecondPhoneNumber());
            staff.setStaffAddress(staffDetails.getStaffAddress());
            staff.setStaffImage(staffDetails.getStaffImage());
            staff.setGuarantor1(staffDetails.getGuarantor1());
            staff.setGuarantor2(staffDetails.getGuarantor2());
            return staffRepository.save(staff);
        }
        return null; // Or throw an exception
    }

    // Delete a staff member
    public void deleteStaff(Long staffId) {
        staffRepository.deleteById(staffId);
    }
}