package lk.ijse.gdse68.greenshadowbackend.service;

import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.dto.StaffFieldDetailsDTO;

import java.util.List;

/**
 * @author : sachini
 * @date : 2024-11-08
 **/
public interface StaffFieldDetailsService {
    void saveStaffFieldDetails(StaffFieldDetailsDTO staffFieldDetailsDTO);

    List<StaffFieldDetailsDTO> getAllStaffFieldDetails();
}