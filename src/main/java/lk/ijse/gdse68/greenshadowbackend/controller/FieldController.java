package lk.ijse.gdse68.greenshadowbackend.controller;

import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldErrorResponse;
import lk.ijse.gdse68.greenshadowbackend.customerObj.FieldResponse;
import lk.ijse.gdse68.greenshadowbackend.dto.FieldDTO;
import lk.ijse.gdse68.greenshadowbackend.exception.DataPersistFailedException;
import lk.ijse.gdse68.greenshadowbackend.exception.FieldNoteFoundException;
import lk.ijse.gdse68.greenshadowbackend.service.FieldService;
import lk.ijse.gdse68.greenshadowbackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@RequiredArgsConstructor
public class FieldController {
    @Autowired
    private final FieldService fieldService;

    //TODO: Field CRUD Implement
    //TODO: Save Filed

    @PostMapping
    public ResponseEntity<String> saveField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("fieldLocation") String fieldLocation,
            @RequestPart("extentSize") String extentSize,
            @RequestPart("fieldImage1") MultipartFile fieldImage1,
            @RequestPart("fieldImage2") MultipartFile fieldImage2){
        try {
            // Get Base64 strings for both images
            String[] base64Images = AppUtil.toBase64Images(fieldImage1, fieldImage2);

            // Create and populate FieldDTO
            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setFieldCode(fieldCode);
            fieldDTO.setFieldName(fieldName);
            fieldDTO.setFieldLocation(fieldLocation);
            fieldDTO.setExtentSize(Double.parseDouble(extentSize));
            fieldDTO.setFieldImages(fieldImage1, fieldImage2); // Pass MultipartFile images for field
            // Save the field data
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>("Field Details Saved Successfully!", HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            return new ResponseEntity<>("Field data could not be saved, data persistence failed.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error occurred while saving the field.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   //TODO:Update
    @PatchMapping(value = "/{fieldCode}")
    public ResponseEntity<String> updateField(
            @RequestPart("updateFieldName") String updateFieldName,
            @RequestPart("updateFieldLocation") String updateFieldLocation,
            @RequestPart("updateExtentSize") String updateExtentSize,
            @RequestPart("updateFieldImage1") MultipartFile updateFieldImage1,
            @RequestPart("updateFieldImage2") MultipartFile updateFieldImage2,
            @RequestPart("fieldCode") String fieldCode) {
        try {
            String[] base64Images = AppUtil.toBase64Images(updateFieldImage1,updateFieldImage2);

            FieldDTO buildfieldDTO = new FieldDTO();
            buildfieldDTO.setFieldCode(fieldCode);
            buildfieldDTO.setFieldName(updateFieldName);
            buildfieldDTO.setFieldLocation(updateFieldLocation);
            buildfieldDTO.setExtentSize(Double.valueOf(updateExtentSize));
            buildfieldDTO.setFieldImages(updateFieldImage1, updateFieldImage2); // Pass MultipartFile images for field
            fieldService.updateField(buildfieldDTO);
            return new ResponseEntity<>("Field Update Successfully!!",HttpStatus.OK);
        } catch (FieldNoteFoundException | ClassNotFoundException e) {
            return new ResponseEntity<>("Field not found!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO:Delete
    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<String> deleteField(@PathVariable ("fieldCode") String fieldCode){
        try {
            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>("Field Details Delete Successfully!!",HttpStatus.OK);
        }catch (FieldNoteFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>("Field not found!",HttpStatus.NOT_FOUND);
        }
    }

    //TODO: Select id
    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldResponse> getSelectedField(@PathVariable ("fieldCode") String fieldCode){
        FieldResponse fieldResponse = fieldService.getSelectedField(fieldCode);
        if (fieldResponse instanceof FieldDTO){
            return new ResponseEntity<>(fieldResponse,HttpStatus.OK);
        }else if (fieldResponse instanceof FieldErrorResponse){
            return new ResponseEntity<>(fieldResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
    //TODO:GetAll
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllField(){
        List<FieldDTO> fieldDTOS = fieldService.getAllField();
        if (!fieldDTOS.isEmpty()){
            return new ResponseEntity<>(fieldDTOS,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
