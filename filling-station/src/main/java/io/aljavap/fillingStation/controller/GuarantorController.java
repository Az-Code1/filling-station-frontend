package io.aljavap.fillingStation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.aljavap.fillingStation.entity.Guarantor;
import io.aljavap.fillingStation.service.GuarantorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guarantors")
public class GuarantorController {

    @Autowired
    private GuarantorService guarantorService;

    // Create a new guarantor
    @PostMapping
    public ResponseEntity<Guarantor> createGuarantor(@RequestBody Guarantor guarantor) {
        Guarantor savedGuarantor = guarantorService.saveGuarantor(guarantor);
        return ResponseEntity.ok(savedGuarantor);
    }

    // Get all guarantors
    @GetMapping
    public ResponseEntity<List<Guarantor>> getAllGuarantors() {
        List<Guarantor> guarantors = guarantorService.getAllGuarantors();
        return ResponseEntity.ok(guarantors);
    }

    // Get guarantor by ID
    @GetMapping("/{guarantorId}")
    public ResponseEntity<Guarantor> getGuarantorById(@PathVariable Long guarantorId) {
        Optional<Guarantor> guarantor = guarantorService.getGuarantorById(guarantorId);
        return guarantor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a guarantor
    @PutMapping("/{guarantorId}")
    public ResponseEntity<Guarantor> updateGuarantor(@PathVariable Long guarantorId, @RequestBody Guarantor guarantorDetails) {
        Guarantor updatedGuarantor = guarantorService.updateGuarantor(guarantorId, guarantorDetails);
        return updatedGuarantor != null ? ResponseEntity.ok(updatedGuarantor) : ResponseEntity.notFound().build();
    }

    // Delete a guarantor
    @DeleteMapping("/{guarantorId}")
    public ResponseEntity<Void> deleteGuarantor(@PathVariable Long guarantorId) {
        guarantorService.deleteGuarantor(guarantorId);
        return ResponseEntity.noContent().build();
    }
}
