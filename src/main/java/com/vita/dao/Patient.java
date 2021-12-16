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
@Document(collection = "Patient")
public class Patient {
    @Id
    private String patientID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String doctorID;
}
