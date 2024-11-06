package lk.ijse.gdse68.greenshadowbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lk.ijse.gdse68.greenshadowbackend.customerObj.LogResponse;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
/**
 * @author : sachini
 * @date : 2024-11-06
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements LogResponse,SuperDTO {
    @NotNull(message = "Log code is mandatory")
    @Pattern(regexp = "^LOG-\\d{3}$", message = "Log code must be in the format 'LOG-001'")
    private String logCode;

    @NotNull(message = "Date is required.")
    private Date logDate;

    @NotNull(message = "Log details is required.")
    @Size(max = 500, message = "Log details must not exceed 500 characters")
    private String logDetails;

    private String observedImage;
    @NotBlank(message = "Crop code is required.")
    private String cropCode;
@Override
    public void setLogImage(MultipartFile image) {
        String[] base64Images = AppUtil.toBase64Images(image);
        this.observedImage = base64Images[0];
    }

}