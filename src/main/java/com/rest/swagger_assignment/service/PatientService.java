package com.rest.swagger_assignment.service;

import com.rest.swagger_assignment.entities.Patient;
import com.rest.swagger_assignment.exception.UserNotFoundException;
import com.rest.swagger_assignment.repositories.PatientRepository;
import java.util.List;
// Add the necessary dependency for javax.persistence
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

  public List<Patient> getAllPatients() {
    List<Patient> patientList = patientRepository.findAll();
    return patientList;
  }

  public Patient savePatient(Patient patient) {
    return patientRepository.save(patient);
  }

  public Patient getPatient(Long id) {
    //
    Optional<Patient> patient = patientRepository.findById(id);
    if (patient.isPresent()) {
      return patient.get();
    } else {
      throw new UserNotFoundException("Patient not found");
    }
  }

  public Patient updatePatient(Long id, Patient patient) {
    Optional<Patient> patientToUpdateOptional = patientRepository.findById(id);

    if (!patientToUpdateOptional.isPresent()) {
      throw new UserNotFoundException("Patient not found");
    }
    Patient patientToUpdate = patientToUpdateOptional.get();

    patientToUpdate.setFirstName(patient.getFirstName());
    patientToUpdate.setLastName(patient.getLastName());
    patientToUpdate.setDateOfBirth(patient.getDateOfBirth());
    patientToUpdate.setGender(patient.getGender());
    return patientRepository.save(patientToUpdate);
  }

  public String deleteById(Long id) {
    patientRepository.deleteById(id);
    return "Deleted Patient with patient id " + id;
  }
}
