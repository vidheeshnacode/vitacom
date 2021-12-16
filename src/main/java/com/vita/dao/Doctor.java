package com.vita.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "Doctor")
public class Doctor {
    @Id
    private String doctorID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private List<String> specialization;
}