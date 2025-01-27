package lk.ijse.gdse68.greenshadowbackend.customerObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : sachini
 * @date : 2024-11-01
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldErrorResponse implements FieldResponse {
    private int errorCode;
    private String errorMassage;

    @Override
    public void setFieldImages(MultipartFile image1, MultipartFile image2) {

    }
}
