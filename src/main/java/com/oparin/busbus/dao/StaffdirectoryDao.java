package com.oparin.busbus.dao;

import com.oparin.busbus.models.Staffdirectory;

public interface StaffdirectoryDao {
    void addStaff(Staffdirectory staff);
    Staffdirectory getStaffById(int id);
    void updateStaff(Staffdirectory staff);
    void deleteStaff(Staffdirectory staff);
}