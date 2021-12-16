package com.vita.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Vital")
public class Vital {
    @Id
    private String vitalID;
    private String patientID;
    private String systolic;
    private String diastolic;
    private String pulse;
    private String spo2;
    private String glucose;
    private String temperature;
    private String date;
    private String time;
}
