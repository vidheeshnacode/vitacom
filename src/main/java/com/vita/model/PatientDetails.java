package com.vita.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDetails {
    private String patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String doctorID;
    private List<VitalDetails> vitalDetails = new ArrayList<>();
}
