package com.alcode.az.fillingstation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Staff {

    private final LongProperty staffId = new SimpleLongProperty();
    private final StringProperty staffSurname = new SimpleStringProperty();
    private final StringProperty staffFirstName = new SimpleStringProperty();
    private final StringProperty staffOtherName = new SimpleStringProperty();
    private final StringProperty staffGender = new SimpleStringProperty();
    private final ObjectProperty<LocalDate>  staffDateOfBirth = new SimpleObjectProperty<>();
    private final StringProperty staffNIN = new SimpleStringProperty();
    private final StringProperty staffPhoneNumber = new SimpleStringProperty();
    private final StringProperty secondPhoneNumber = new SimpleStringProperty();
    private final ObjectProperty<Address> staffAddress = new SimpleObjectProperty<>();
    private final ObjectProperty<Guarantor> guarantor1 = new SimpleObjectProperty<>();
    private final ObjectProperty<Guarantor> guarantor2 = new SimpleObjectProperty<>();
    private final StringProperty staffImage = new SimpleStringProperty();

    // Constructors
    public Staff() {
    }
    public Staff(long staffId, String staffSurname, String staffFirstName, String staffOtherName,
                 String staffGender, LocalDate staffDateOfBirth, String staffNIN, String staffPhoneNumber,
                 String secondPhoneNumber, Address staffAddress, Guarantor guarantor1, Guarantor guarantor2,
                 String staffImage) {
        this.staffId.set(staffId);
        this.staffSurname.set(staffSurname);
        this.staffFirstName.set(staffFirstName);
        this.staffOtherName.set(staffOtherName);
        this.staffGender.set(staffGender);
        this.staffDateOfBirth.set(staffDateOfBirth);
        this.staffNIN.set(staffNIN);
        this.staffPhoneNumber.set(staffPhoneNumber);
        this.secondPhoneNumber.set(secondPhoneNumber);
        this.staffAddress.set(staffAddress);
        this.guarantor1.set(guarantor1);
        this.guarantor2.set(guarantor2);
        this.staffImage.set(staffImage);
    }

    // Getters and Setters for properties
    @JsonIgnore
    public long getStaffId() { return staffId.get(); }
    @JsonProperty
    public void setStaffId(long staffId) { this.staffId.set(staffId); }
    public LongProperty staffIdProperty() { return staffId; }

    public String getStaffSurname() { return staffSurname.get(); }
    public void setStaffSurname(String staffSurname) { this.staffSurname.set(staffSurname); }
    public StringProperty staffSurnameProperty() { return staffSurname; }

    public String getStaffFirstName() { return staffFirstName.get(); }
    public void setStaffFirstName(String staffFirstName) { this.staffFirstName.set(staffFirstName); }
    public StringProperty staffFirstNameProperty() { return staffFirstName; }

    public String getStaffOtherName() { return staffOtherName.get(); }
    public void setStaffOtherName(String staffOtherName) { this.staffOtherName.set(staffOtherName); }
    public StringProperty staffOtherNameProperty() { return staffOtherName; }

    public String getStaffGender() { return staffGender.get(); }
    public void setStaffGender(String staffGender) { this.staffGender.set(staffGender); }
    public StringProperty StaffGenderProperty() { return staffGender; }

    public String getStaffNIN() { return staffNIN.get(); }
    public void setStaffNIN(String staffNIN) { this.staffNIN.set(staffNIN); }
    public StringProperty staffNINProperty() { return staffNIN; }

    public String getStaffPhoneNumber() { return staffPhoneNumber.get(); }
    public void setStaffPhoneNumber(String staffPhoneNumber) { this.staffPhoneNumber.set(staffPhoneNumber); }
    public StringProperty staffPhoneNumberProperty() { return staffPhoneNumber; }

    public String getSecondPhoneNumber() { return secondPhoneNumber.get(); }
    public void setSecondPhoneNumber(String secondPhoneNumber) { this.secondPhoneNumber.set(secondPhoneNumber); }
    public StringProperty secondPhoneNumberProperty() { return secondPhoneNumber; }

    public LocalDate getStaffDateOfBirth() {
        return toJSonLocalDate(staffDateOfBirth.get());
    }
    public void setStaffDateOfBirth(LocalDate staffDateOfBirth) {
        this.staffDateOfBirth.set(toJSonLocalDate(staffDateOfBirth));
    }
    public ObjectProperty<LocalDate> staffDateOfBirthProperty() { return staffDateOfBirth; }

    public String getStaffImage() { return staffImage.get(); }
    public void setStaffImage(String staffImage) { this.staffImage.set(staffImage); }
    public StringProperty staffImageProperty() { return staffImage; }

    // Repeat for other properties...

    public Address getStaffAddress() { return staffAddress.get(); }
    public void setStaffAddress(Address staffAddress) { this.staffAddress.set(staffAddress); }
    public ObjectProperty<Address> staffAddressProperty() { return staffAddress; }

    public Guarantor getGuarantor1() { return guarantor1.get(); }
    public void setGuarantor1(Guarantor guarantor1) { this.guarantor1.set(guarantor1); }
    public ObjectProperty<Guarantor> guarantor1Property() { return guarantor1; }

    public Guarantor getGuarantor2() { return guarantor2.get(); }
    public void setGuarantor2(Guarantor guarantor2) { this.guarantor2.set(guarantor2); }
    public ObjectProperty<Guarantor> guarantor2Property() { return guarantor2; }


    public LocalDate toJSonLocalDate(LocalDate localDate) {
        LocalDate parsedLocalDate;
        // Convert LocalDate to yyyy-mm-dd format string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Parse the formatted string back into a LocalDate
        parsedLocalDate = LocalDate.parse(localDate.toString(), formatter);

        return parsedLocalDate;
    }
}