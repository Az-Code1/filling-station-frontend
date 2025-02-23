package com.alcode.az.fillingstation.viewmodel;

import com.alcode.az.fillingstation.model.Staff;
import com.alcode.az.fillingstation.service.StaffService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StaffViewModel {

    @Getter
    private final ObservableList<Staff> staffList = FXCollections.observableArrayList();
    private final StaffService service = new StaffService();

    public StaffViewModel() {
        loadStaff();
    }

    public void loadStaff() {
        staffList.setAll(service.getAllStaff());
    }

    public void saveStaff(Staff staff) {
        System.out.println("StaffViewModel.saveStaff method entered" + staff);
        service.saveStaff(staff);
        System.out.println("StaffViewModel.saveStaff after service.saveStaff called");
        loadStaff();
    }

    public void updateStaff(Staff staff) {
        System.out.println("StaffViewModel.updateStaff method entered" + staff.getStaffSurname());
        service.updateStaff(staff);
        System.out.println("StaffViewModel.updateStaff after service.updateStaff called");
        loadStaff();
    }

    public void deleteStaff(long staffId) {
        service.deleteStaff(staffId);
        loadStaff();
    }
}