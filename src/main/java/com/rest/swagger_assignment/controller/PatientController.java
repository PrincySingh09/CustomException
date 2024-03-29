package com.rest.swagger_assignment.controller;


import java.util.List;

import com.rest.swagger_assignment.entities.*;
import com.rest.swagger_assignment.exception.UserNotFoundException;
import com.rest.swagger_assignment.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

  @Autowired
  private PatientService pService;

  @GetMapping("/patients")
  List<Patient> getPatients() {
    return pService.getAllPatients();
  }

  @GetMapping("/patients/{id}")
  public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {
    try {
      Patient patient = pService.getPatient(id);
      return ResponseEntity.ok(patient);
    } catch (UserNotFoundException e) {
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Patient not found");
    }
  }

  @PutMapping("/patients/{id}")
  Patient updatePatient(
    @PathVariable("id") Long id,
    @RequestBody Patient patient
  ) {
    return pService.updatePatient(id, patient);
  }

  @PostMapping("/patients")
  Patient savePatientWithRelations(@RequestBody Patient patient) {
    return pService.savePatient(patient);
  }

  @DeleteMapping("/patients/{id}")
  String deletePatient(@PathVariable("id") Long id) {
    pService.deleteById(id);
    return "Patient removed for id " + id;
  }
}
