package com.safety.savinglives.safetynetapplication.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safety.savinglives.safetynetapplication.model.MedicalRecord;
import com.safety.savinglives.safetynetapplication.service.MedicalRecordServices;

@RestController
public class MedicalRecordsController {

	private static final Logger logger = LogManager.getLogger(MedicalRecordsController.class);

	@Autowired
	MedicalRecordServices medicalRecordServices;

	@GetMapping(value = "/medicalrecords")
	public ResponseEntity<Iterable<MedicalRecord>> getAllRecords() {

		Iterable<MedicalRecord> result = medicalRecordServices.getAllMedicalRecords();
		if (result == null) {
			logger.error("COULD NOT return a satisfying result for GET /medicalrecords ");
			return ResponseEntity.notFound().build();
		} else {
			logger.info("Successfully return a satisfying result for GET /medicalrecords ");
			return ResponseEntity.ok().body(result);
		}

	}

	@PostMapping(value = "/medicalrecords")
	public ResponseEntity<Void> postANewMedicalRecord(@RequestBody MedicalRecord MedRec) {
		Boolean result = medicalRecordServices.saveANewMedicalRecord(MedRec);

		if (result) {

			logger.info("Successfully return a satisfying a result for POST /medicalrecords ");

			return ResponseEntity.ok().build();
		} else {

			logger.error("COULD NOT return a satisfying result for POST /medicalrecords ");

			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping(value = "/medicalrecords/{firstName}/{thelastName}")
	public ResponseEntity<Void> createAPerson(@PathVariable("firstName") String firstName,
			@PathVariable("thelastName") String thelastName) {

		Boolean result = medicalRecordServices.deleteAMedicalFile(firstName, thelastName);

		if (result) {

			logger.info(
					"Successfully return a satisfying result for DELETE /medicalrecords/{firstName}/{thelastName} with firstName = "
							+ firstName + ", lastName =" + thelastName);
			return ResponseEntity.ok().build();
		} else {
			logger.error(
					"COULD NOT return a satisfying result for DELETE /medicalrecords/{firstName}/{thelastName} with firstName = "
							+ firstName + ", lastName =" + thelastName);
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping(value = "/medicalrecords")
	public ResponseEntity<Void> updateAPerson(@RequestBody MedicalRecord medRec) {

		MedicalRecord result = medicalRecordServices.updateAMedicalFile(medRec);

		if (result == null) {
			logger.error("COULD NOT return a satisfying result for PUT /medicalrecords ");
			return ResponseEntity.notFound().build();
		} else {
			logger.info("Successfully return a satisfying result for PUT /medicalrecords ");
			return ResponseEntity.ok().build();

		}
	}

}
