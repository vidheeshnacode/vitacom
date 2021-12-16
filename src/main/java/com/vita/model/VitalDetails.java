package com.vita.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VitalDetails {
    private String vitalID;
    private String systolic;
    private String diastolic;
    private String pulse;
    private String spo2;
    private String glucose;
    private String temperature;
    private String date;
    private String time;
}
