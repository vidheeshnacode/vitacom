package com.vita.business;

import com.vita.dao.Doctor;
import com.vita.dao.Patient;
import com.vita.dao.Vital;
import com.vita.model.PatientDetails;
import com.vita.model.VitalDetails;
import com.vita.repository.DoctorRepository;
import com.vita.repository.PatientRepository;
import com.vita.repository.VitalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VitaComServiceImpl implements IVitaComService {

    @Autowired
    private VitalsRepository vitalsRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<VitalDetails> getAllVitalsForPatient(String patientID) {

        List<VitalDetails> vitalDetails = new ArrayList<>();
        List<Vital> response = vitalsRepository.findByPatientID(patientID);
        for (Vital vital : response) {
            vitalDetails.add(convertVitalFromDao(vital));
        }
        return vitalDetails;
    }

    @Override
    public PatientDetails createPatient(PatientDetails patientDetails) {
        Patient patient = convertPatientToDao(patientDetails);
        patient = patientRepository.save(patient);
        patientDetails.setPatientID(patient.getPatientID());
        return patientDetails;
    }

    @Override
    public PatientDetails getPatientDetails(String email) {
        Patient patient = patientRepository.findByEmail(email);
        return convertPatientFromDao(patient);
    }

    @Override
    public PatientDetails updatePatient(PatientDetails patientDetails) {
        Patient patient = patientRepository.findById(patientDetails.getPatientID()).orElse(null);
        patientRepository.deleteById(patient.getPatientID());
        Patient patientModified = convertPatientToDao(patientDetails);
        patientModified.setPatientID(patient.getPatientID());
        return convertPatientFromDao(patientRepository.save(patientModified));
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        doctor = doctorRepository.save(doctor);
        doctor.setDoctorID(doctor.getDoctorID());
        return doctor;
    }

    @Override
    public Doctor getDoctorDetailsById(String id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        return doctor;
    }

    @Override
    public VitalDetails updateVitals(VitalDetails vitalDetails) {
        Vital vital = vitalsRepository.findById(vitalDetails.getVitalID()).orElse(null);
        vitalsRepository.deleteById(vital.getVitalID());
        Vital vitalModified = convertVitalToDao(vitalDetails);
        vitalModified.setVitalID(vital.getVitalID());
        return convertVitalFromDao(vitalsRepository.save(vitalModified));
    }

    @Override
    public PatientDetails addVitals(VitalDetails vitalDetails, String patientID) {
        Patient patient = patientRepository.findById(patientID).orElse(null);
        if (null != patient) {
            Vital vital = convertVitalToDao(vitalDetails);
            vital.setPatientID(patientID);
            vitalsRepository.save(vital);
            PatientDetails patientDetails = convertPatientFromDao(patient);
            List<VitalDetails> vitalDetailsList = new ArrayList<>();
            List<Vital> vitals = vitalsRepository.findByPatientID(patientID);
            for (Vital v : vitals) {
                vitalDetailsList.add(convertVitalFromDao(v));
            }
            patientDetails.setVitalDetails(vitalDetailsList);
            return patientDetails;
        }

        return null;
    }

    @Override
    public Doctor updateDoctor(Doctor doctorDetails) {
        Doctor doctorFetched = doctorRepository.findById(doctorDetails.getDoctorID()).orElse(null);
        doctorRepository.deleteById(doctorFetched.getDoctorID());
        doctorDetails.setDoctorID(doctorFetched.getDoctorID());
        return doctorRepository.save(doctorDetails);
    }


    private PatientDetails convertPatientFromDao(Patient patient) {
        PatientDetails patientDetails = new PatientDetails();
        patientDetails.setPatientID(patient.getPatientID());
        patientDetails.setEmail(patient.getEmail());
        patientDetails.setDoctorID(patient.getDoctorID());
        patientDetails.setGender(patient.getGender());
        patientDetails.setFirstName(patient.getFirstName());
        patientDetails.setLastName(patient.getLastName());
        patientDetails.setPhone(patient.getPhone());
        return patientDetails;
    }

    private Patient convertPatientToDao(PatientDetails patientDetails) {
        Patient patient = new Patient();
        patient.setEmail(patientDetails.getEmail());
        patient.setDoctorID(patientDetails.getDoctorID());
        patient.setGender(patientDetails.getGender());
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setPhone(patientDetails.getPhone());
        return patient;
    }

    private VitalDetails convertVitalFromDao(Vital input) {
        VitalDetails output = new VitalDetails();
        output.setVitalID(input.getVitalID());
        output.setSystolic(input.getSystolic());
        output.setDiastolic(input.getDiastolic());
        output.setPulse(input.getPulse());
        output.setSpo2(input.getSpo2());
        output.setGlucose(input.getGlucose());
        output.setTemperature(input.getTemperature());
        output.setDate(input.getDate());
        output.setTime(input.getTime());
        return output;
    }

    private Vital convertVitalToDao(VitalDetails input) {
        Vital output = new Vital();
        output.setSystolic(input.getSystolic());
        output.setDiastolic(input.getDiastolic());
        output.setPulse(input.getPulse());
        output.setSpo2(input.getSpo2());
        output.setGlucose(input.getGlucose());
        output.setTemperature(input.getTemperature());
        output.setDate(input.getDate());
        output.setTime(input.getTime());
        return output;
    }
}
