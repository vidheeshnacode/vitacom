package com.vita.repository;

import com.vita.dao.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {

    @Query("{'email':?0}")
    public Patient findByEmail(String email);
}
