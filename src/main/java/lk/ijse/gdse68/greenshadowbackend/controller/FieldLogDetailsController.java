package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.dto.LogDTO;
import lk.ijse.gdse68.greenshadowbackend.service.FieldLogDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/field-log-details")
public class FieldLogDetailsController {

    private final FieldLogDetailsService fieldLogDetailsService;

    @Autowired
    public FieldLogDetailsController(FieldLogDetailsService fieldLogDetailsService) {
        this.fieldLogDetailsService = fieldLogDetailsService;
    }

    // Endpoint to create field log details
    @PostMapping
    public ResponseEntity<String> createFieldLogDetails(@RequestBody LogDTO logDTO) {
        try {
            fieldLogDetailsService.saveFieldLogDetails(logDTO);
            return ResponseEntity.ok("Field log details saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save field log details: " + e.getMessage());
        }
    }
}
