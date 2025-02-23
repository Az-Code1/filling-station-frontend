package com.alcode.az.fillingstation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Guarantor {
    private final LongProperty guarantorId = new SimpleLongProperty();
    private final StringProperty guarantorSurname = new SimpleStringProperty();
    private final StringProperty guarantorFirstName = new SimpleStringProperty();
    private final StringProperty guarantorOtherName = new SimpleStringProperty();
    private final StringProperty guarantorPhoneNumber = new SimpleStringProperty();
    private final StringProperty guarantorOccupation = new SimpleStringProperty();
    private final StringProperty guarantorGender = new SimpleStringProperty();
    private final StringProperty guarantorRelationship = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> guarantorDateOfBirth = new SimpleObjectProperty<>();
    private final StringProperty guarantorNIN = new SimpleStringProperty();
    private final ObjectProperty<Address> guarantorAddress = new SimpleObjectProperty<>();

    // Constructors
    public Guarantor() {
    }
    public Guarantor(long guarantorId, String guarantorSurname, String guarantorFirstName, String guarantorOtherName,
                     String guarantorOccupation, String guarantorPhoneNumber, String guarantorGender,
                     String guarantorRelationship, LocalDate guarantorDateOfBirth, String guarantorNIN,
                     Address guarantorAddress) {
        this.guarantorId.set(guarantorId);
        this.guarantorSurname.set(guarantorSurname);
        this.guarantorFirstName.set(guarantorFirstName);
        this.guarantorOtherName.set(guarantorOtherName);
        this.guarantorOccupation.set(guarantorOccupation);
        this.guarantorPhoneNumber.set(guarantorPhoneNumber);
        this.guarantorGender.set(guarantorGender);
    }

    // Getters and Setters for properties
    @JsonIgnore
    public long getGuarantorId() { return guarantorId.get(); }
    @JsonProperty
    public void setGuarantorId(long guarantorId) { this.guarantorId.set(guarantorId); }
    public LongProperty guarantorIdProperty() { return guarantorId; }

    public String getGuarantorSurname() { return guarantorSurname.get(); }
    public void setGuarantorSurname(String guarantorSurname) { this.guarantorSurname.set(guarantorSurname); }
    public StringProperty guarantorSurnameProperty() { return guarantorSurname; }

    public String getGuarantorFirstName() { return guarantorFirstName.get(); }
    public void setGuarantorFirstName(String guarantorFirstName) { this.guarantorFirstName.set(guarantorFirstName); }
    public StringProperty guarantorFirstNameProperty() { return guarantorFirstName; }

    public String getGuarantorOtherName() { return guarantorOtherName.get(); }
    public void setGuarantorOtherName(String guarantorOtherName) { this.guarantorOtherName.set(guarantorOtherName); }
    public StringProperty guarantorOtherNameProperty() { return guarantorOtherName; }

    public String getGuarantorOccupation() { return guarantorOccupation.get(); }
    public void setGuarantorOccupation(String guarantorOccupation) { this.guarantorOccupation.set(guarantorOccupation); }
    public StringProperty guarantorOccupationProperty() { return guarantorOccupation; }

    public String getGuarantorPhoneNumber() { return guarantorPhoneNumber.get(); }
    public void setGuarantorPhoneNumber(String guarantorPhoneNumber) { this.guarantorPhoneNumber.set(guarantorPhoneNumber); }
    public StringProperty guarantorPhoneNumberProperty() { return guarantorPhoneNumber; }

    public String getGuarantorGender() { return guarantorGender.get(); }
    public void setGuarantorGender(String guarantorGender) { this.guarantorGender.set(guarantorGender); }
    public StringProperty guarantorGenderProperty() { return guarantorGender; }

    public String getGuarantorRelationship() { return guarantorRelationship.get(); }
    public void setGuarantorRelationship(String guarantorRelationship) { this.guarantorRelationship.set(guarantorRelationship); }
    public StringProperty guarantorRelationshipProperty() { return guarantorRelationship; }

    public LocalDate getGuarantorDateOfBirth() { return guarantorDateOfBirth.get(); }
    public void setGuarantorDateOfBirth(LocalDate guarantorDateOfBirth) { this.guarantorDateOfBirth.set(guarantorDateOfBirth); }
    public ObjectProperty<LocalDate> guarantorDateOfBirthProperty() { return guarantorDateOfBirth; }

    public String getGuarantorNIN() { return guarantorNIN.get(); }
    public void setGuarantorNIN(String guarantorNIN) { this.guarantorNIN.set(guarantorNIN); }
    public StringProperty guarantorNINProperty() { return guarantorNIN; }

    public Address getGuarantorAddress() { return guarantorAddress.get(); }
    public void setGuarantorAddress(Address guarantorAddress) { this.guarantorAddress.set(guarantorAddress); }
    public ObjectProperty<Address> guarantorAddressProperty() { return guarantorAddress; }
    // Repeat for other properties...
}