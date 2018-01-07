package dev.sgp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sgp.api.entite.Departement;
import dev.sgp.api.repository.DepartementRepository;

@RestController
@CrossOrigin
public class DepartementController {
	
	@Autowired
	DepartementRepository departementRepository;
	
		
	@GetMapping(path = "/departements")	
	public List<Departement> listerDepartement() {
		return departementRepository.findAll();
	}
	
}
