package com.vita.presentation;

import com.vita.business.FileService;
import com.vita.business.IVitaComService;
import com.vita.dao.Doctor;
import com.vita.model.LoadFile;
import com.vita.model.PatientDetails;
import com.vita.model.VitalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VitaComController {

    @Autowired
    private IVitaComService iVitacomService;

    @Autowired
    private FileService fileService;


    @PostMapping("/createPatient")
    public PatientDetails createPatient(@RequestBody PatientDetails patientDetails) {
        return iVitacomService.createPatient(patientDetails);
    }

    @GetMapping("/patientDetails/{email}")
    public PatientDetails getPatientDetails(@PathVariable String email) {
        return iVitacomService.getPatientDetails(email);
    }

    //update patient details
    @PutMapping("/updatePatient")
    public PatientDetails updatePatient(@RequestBody PatientDetails patientDetails) {
        return iVitacomService.updatePatient(patientDetails);
    }

    @PostMapping("/createDoctor")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return iVitacomService.createDoctor(doctor);
    }

    @GetMapping("/doctorDetails/{id}")
    public Doctor getDoctorDetails(@PathVariable String id) {
        return iVitacomService.getDoctorDetailsById(id);
    }

    @PutMapping("/updateDoctor")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return iVitacomService.updateDoctor(doctor);
    }

    @GetMapping("/patients/{patientID}/vitals")
    public List<VitalDetails> getAllVitals(@PathVariable String patientID) {
        return iVitacomService.getAllVitalsForPatient(patientID);
    }

    @PutMapping("/updateVital")
    public VitalDetails updateVitals(@RequestBody VitalDetails vitalDetails) {
        return iVitacomService.updateVitals(vitalDetails);
    }

    //add vital details for a patient
    @PostMapping("/patients/{patientID}/addVitals")
    public PatientDetails addVitals(@RequestBody VitalDetails vitalDetails, @PathVariable String patientID) {
        return iVitacomService.addVitals(vitalDetails, patientID);
    }


    //File Management
    @PostMapping("/file/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.addFile(file), HttpStatus.OK);
    }

    @GetMapping("/file/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        LoadFile loadFile = fileService.downloadFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }
}