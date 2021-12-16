package com.vita.business;

import com.vita.dao.Doctor;
import com.vita.model.PatientDetails;
import com.vita.model.VitalDetails;

import java.util.List;

public interface IVitaComService {

    List<VitalDetails> getAllVitalsForPatient(String patientID);

    PatientDetails createPatient(PatientDetails patientDetails);

    PatientDetails getPatientDetails(String email);

    PatientDetails updatePatient(PatientDetails patientDetails);

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor);

    Doctor getDoctorDetailsById(String id);

    VitalDetails updateVitals(VitalDetails vitalDetails);

    PatientDetails addVitals(VitalDetails vitalDetails, String patientID);
}
