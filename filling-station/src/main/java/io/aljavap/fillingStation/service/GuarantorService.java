package io.aljavap.fillingStation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.aljavap.fillingStation.entity.Guarantor;
import io.aljavap.fillingStation.repository.GuarantorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GuarantorService {

    @Autowired
    private GuarantorRepository guarantorRepository;

    // Create or save a guarantor
    public Guarantor saveGuarantor(Guarantor guarantor) {
        return guarantorRepository.save(guarantor);
    }

    // Get all guarantors
    public List<Guarantor> getAllGuarantors() {
        return guarantorRepository.findAll();
    }

    // Get guarantor by ID
    public Optional<Guarantor> getGuarantorById(Long guarantorId) {
        return guarantorRepository.findById(guarantorId);
    }

    // Update a guarantor
    public Guarantor updateGuarantor(Long guarantorId, Guarantor guarantorDetails) {
        Optional<Guarantor> optionalGuarantor = guarantorRepository.findById(guarantorId);
        if (optionalGuarantor.isPresent()) {
            Guarantor guarantor = optionalGuarantor.get();
            guarantor.setGuarantorSurname(guarantorDetails.getGuarantorSurname());
            guarantor.setGuarantorFirstName(guarantorDetails.getGuarantorFirstName());
            guarantor.setGuarantorOtherName(guarantorDetails.getGuarantorOtherName());
            guarantor.setGuarantorPhoneNumber(guarantorDetails.getGuarantorPhoneNumber());
            guarantor.setGuarantorOccupation(guarantorDetails.getGuarantorOccupation());
            guarantor.setGuarantorGender(guarantorDetails.getGuarantorGender());
            guarantor.setGuarantorRelationship(guarantorDetails.getGuarantorRelationship());
            guarantor.setGuarantorDateOfBirth(guarantorDetails.getGuarantorDateOfBirth());
            guarantor.setGuarantorNIN(guarantorDetails.getGuarantorNIN());
            guarantor.setGuarantorAddress(guarantorDetails.getGuarantorAddress());
            return guarantorRepository.save(guarantor);
        }
        return null; // Or throw an exception
    }

    // Delete a guarantor
    public void deleteGuarantor(Long guarantorId) {
        guarantorRepository.deleteById(guarantorId);
    }
}
