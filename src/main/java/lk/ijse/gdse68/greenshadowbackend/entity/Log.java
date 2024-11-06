package lk.ijse.gdse68.greenshadowbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "logs")
public class Log {
    @Id
    @Column(name = "log_code", nullable = false, unique = true)
    private String logCode;

    @Column(name = "log_date")
    private Date logDate;

    @Column(name = "log_details")
    private String logDetails;

    @Column(name = "observed_image", columnDefinition = "LONGTEXT")
    private String observedImage;

    // Define the many-to-one relationship with Crop
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_code")
    private Crop crop;



}
