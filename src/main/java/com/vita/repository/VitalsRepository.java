package com.vita.repository;

import com.vita.dao.Vital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitalsRepository extends MongoRepository<Vital, String> {
    @Query("{'patientID':?0}")
    public List<Vital> findByPatientID(String patientID);
}
