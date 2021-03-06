package com.safety.savinglives.safetynetapplication.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safety.savinglives.safetynetapplication.DTO.ChildAlertDTO;
import com.safety.savinglives.safetynetapplication.DTO.CommunityEmailDTO;
import com.safety.savinglives.safetynetapplication.DTO.PersonInfoDTO;
import com.safety.savinglives.safetynetapplication.model.Person;
import com.safety.savinglives.safetynetapplication.service.PersonServices;

@RestController
public class PersonController {

	private static final Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	PersonServices personServices;

	@GetMapping(value = "/person")
	public ResponseEntity<Iterable<Person>> getAllPerson() {

		Iterable<Person> result = personServices.getAllPersons();

		if (result == null) {
			logger.info("Successfully return a satisfying result for GET /person ");
			return ResponseEntity.notFound().build();
		} else {
			logger.error("COULD NOT return a satisfying result for GET /person ");
			return ResponseEntity.ok().body(result);
		}
	}

	@PostMapping(value = "/person")
	public ResponseEntity<Void> saveAPerson(@RequestBody Person person) {

		Boolean result = personServices.saveANewPerson(person);

		if (result) {

			logger.info("Successfully return a satisfying result for POST /person ");
			return ResponseEntity.ok().build();
		} else {

			logger.error("COULD NOT return a satisfying result for POST /person ");
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping(value = "/person/{firstName}/{thelastName}")
	public ResponseEntity<Void> deleteAPerson(@PathVariable("firstName") String firstName,
			@PathVariable("thelastName") String thelastName) {

		Boolean result = personServices.deleteAPerson(firstName, thelastName);

		if (result) {
			logger.info("Successfully return a satisfying result for DELETE /person ");
			return ResponseEntity.ok().build();
		} else {
			logger.error("COULD NOT return a satisfying result for DELETE /person ");
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping(value = "/person")
	public ResponseEntity<Void> updateAPerson(@RequestBody Person p) {

		Person result = personServices.updateAPerson(p);

		if (result != null) {
			logger.info("Successfully return a satisfying result for PUT /person ");
			return ResponseEntity.ok().build();
		} else {
			logger.error("COULD NOT return a satisfying result for PUT /person ");
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping(value = "/childAlert")
	public ResponseEntity<ChildAlertDTO> listOfChildLivingInTheAdress(
			@RequestParam(name = "address", required = true) String address) throws Throwable {

		ChildAlertDTO result = personServices.getListOfChildBasedOnAddress(address);
		if (result.getChildInTheHouse().isEmpty()) {
			logger.error("the Call to GET /childAlert with adress " + address + " returned empty List");
			return ResponseEntity.ok().body(result);
		} else if (!result.getChildInTheHouse().isEmpty()) {
			logger.info("the Call to GET /childAlert with adress " + address + " returned successfully");
			return ResponseEntity.ok().body(result);
		} else {
			logger.info("the Call to GET /childAlert with adress " + address + "  return null ");
			return ResponseEntity.notFound().build();

		}
	}

	@GetMapping(value = "/personInfo")
	public ResponseEntity<Iterable<PersonInfoDTO>> getMedicalInformationOfPeople(
			@RequestParam(name = "firstName", required = true) String firstName,
			@RequestParam(name = "lastName", required = true) String lastName) {

		List<PersonInfoDTO> result = personServices.getMedicalInformationOfPeople(firstName, lastName);

		if (!result.isEmpty()) {
			logger.info(
					"Successfully return a satisfying  result with call GET /personInfo?firstName={firstName}&lastName={lastName} with firstName="
							+ firstName + " , and lastName = " + lastName);
			return ResponseEntity.ok().body(result);
		} else {
			logger.error("COULD NOT call GET /personInfo?firstName={firstName}&lastName={lastName} with firstName="
					+ firstName + " , and lastName = " + lastName);
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping(value = "/communityEmail")
	public ResponseEntity<Iterable<CommunityEmailDTO>> getAllEmailFromAllInhabitantOfCity(
			@RequestParam(name = "city", required = true) String city) {

		List<CommunityEmailDTO> result = personServices.getAllEmailFromAllInhabitantOfCity(city);
		logger.info("WUTTTTTTTTTTTTTTTTTTTTTTTT " + city);

		if (!result.isEmpty()) {
			logger.info("Successfully return a satisfying result with call GET /communityEmail?city={city} with city= "
					+ city);
			return ResponseEntity.ok().body(result);
		} else {
			logger.error("COULD NOT call GET /communityEmail?city={city} with city= " + city);
			return ResponseEntity.notFound().build();
		}

	}

}
