package io.aljavap.fillingStation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Data
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Version
    private Integer version;
    
    private String staffSurname;
    private String staffFirstName;
    private String staffOtherName;
    private String staffGender;
    private LocalDate staffDateOfBirth;
    private String staffNIN;
    private String staffPhoneNumber;
    private String secondPhoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staff_address_id")
    private Address staffAddress;

    private String staffImage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guarantor1_id")
    private Guarantor guarantor1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guarantor2_id")
    private Guarantor guarantor2;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    
   public Staff() {
	   
   }


public Staff(Long staffId, Integer version, String staffSurname, String staffFirstName, String staffOtherName,
		String staffGender, LocalDate staffDateOfBirth, String staffNIN, String staffPhoneNumber,
		String secondPhoneNumber, Address staffAddress, String staffImage, Guarantor guarantor1, Guarantor guarantor2,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
	super();
	this.staffId = staffId;
	this.version = version;
	this.staffSurname = staffSurname;
	this.staffFirstName = staffFirstName;
	this.staffOtherName = staffOtherName;
	this.staffGender = staffGender;
	this.staffDateOfBirth = staffDateOfBirth;
	this.staffNIN = staffNIN;
	this.staffPhoneNumber = staffPhoneNumber;
	this.secondPhoneNumber = secondPhoneNumber;
	this.staffAddress = staffAddress;
	this.staffImage = staffImage;
	this.guarantor1 = guarantor1;
	this.guarantor2 = guarantor2;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
}


public Integer getVersion() {
	return version;
}


public void setVersion(Integer version) {
	this.version = version;
}


public String getStaffSurname() {
	return staffSurname;
}


public void setStaffSurname(String staffSurname) {
	this.staffSurname = staffSurname;
}


public String getStaffFirstName() {
	return staffFirstName;
}


public void setStaffFirstName(String staffFirstName) {
	this.staffFirstName = staffFirstName;
}


public String getStaffOtherName() {
	return staffOtherName;
}


public void setStaffOtherName(String staffOtherName) {
	this.staffOtherName = staffOtherName;
}


public String getStaffGender() {
	return staffGender;
}


public void setStaffGender(String staffGender) {
	this.staffGender = staffGender;
}


public LocalDate getStaffDateOfBirth() {
	return staffDateOfBirth;
}


public void setStaffDateOfBirth(LocalDate staffDateOfBirth) {
	this.staffDateOfBirth = staffDateOfBirth;
}


public String getStaffNIN() {
	return staffNIN;
}


public void setStaffNIN(String staffNIN) {
	this.staffNIN = staffNIN;
}


public String getStaffPhoneNumber() {
	return staffPhoneNumber;
}


public void setStaffPhoneNumber(String staffPhoneNumber) {
	this.staffPhoneNumber = staffPhoneNumber;
}


public String getSecondPhoneNumber() {
	return secondPhoneNumber;
}


public void setSecondPhoneNumber(String secondPhoneNumber) {
	this.secondPhoneNumber = secondPhoneNumber;
}


public Address getStaffAddress() {
	return staffAddress;
}


public void setStaffAddress(Address staffAddress) {
	this.staffAddress = staffAddress;
}


public String getStaffImage() {
	return staffImage;
}


public void setStaffImage(String staffImage) {
	this.staffImage = staffImage;
}


public Guarantor getGuarantor1() {
	return guarantor1;
}


public void setGuarantor1(Guarantor guarantor1) {
	this.guarantor1 = guarantor1;
}


public Guarantor getGuarantor2() {
	return guarantor2;
}


public void setGuarantor2(Guarantor guarantor2) {
	this.guarantor2 = guarantor2;
}


public LocalDateTime getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}


public LocalDateTime getUpdatedAt() {
	return updatedAt;
}


public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}


public Long getStaffId() {
	return staffId;
}


@Override
public String toString() {
	return "Staff [staffId=" + staffId + ", version=" + version + ", staffSurname=" + staffSurname + ", staffFirstName="
			+ staffFirstName + ", staffOtherName=" + staffOtherName + ", staffGender=" + staffGender
			+ ", staffDateOfBirth=" + staffDateOfBirth + ", staffNIN=" + staffNIN + ", staffPhoneNumber="
			+ staffPhoneNumber + ", secondPhoneNumber=" + secondPhoneNumber + ", staffAddress=" + staffAddress
			+ ", staffImage=" + staffImage + ", guarantor1=" + guarantor1 + ", guarantor2=" + guarantor2
			+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
}
    
        
    
}