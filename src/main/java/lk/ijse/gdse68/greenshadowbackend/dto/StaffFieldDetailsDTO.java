package lk.ijse.gdse68.greenshadowbackend.dto;

import lk.ijse.gdse68.greenshadowbackend.customerObj.StaffFieldDetailsResponse;
import lk.ijse.gdse68.greenshadowbackend.entity.Field;
import lk.ijse.gdse68.greenshadowbackend.entity.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;
/**
 * @author : sachini
 * @date : 2024-11-08
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class StaffFieldDetailsDTO implements StaffFieldDetailsResponse, Serializable {
    private Staff staff;
    private Field field;
    private String description;
    private int work_staff_count;
    private Date date;
}
