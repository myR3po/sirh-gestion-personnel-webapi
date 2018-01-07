package dev.sgp.api.controller;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.sgp.api.entite.Banque;
import dev.sgp.api.entite.Collaborateur;
import dev.sgp.api.repository.CollaborateurRepository;
import dev.sgp.api.repository.DepartementRepository;

@RestController
@RequestMapping(path = "/collaborateurs")
@CrossOrigin
public class CollaborateurController {
	
	@Autowired
	CollaborateurRepository collaborateurRepository;
	@Autowired
	DepartementRepository departementRepository;
		
	@GetMapping
	public List<Collaborateur> listerCollaborateur(@RequestParam(value = "departement", required = false) Integer id, @RequestParam(value = "name", required = false) String nom) {
		
		List<Collaborateur> collabs = collaborateurRepository.findAll();
		if(id != null) {
			collabs =  collaborateurRepository.findAll().stream().filter(c -> c.getDepartement().getId() == id).collect(toList());
		}
		
		if(nom != null) {
			collabs =  collaborateurRepository.findAll().stream().filter(c -> c.getNom().equalsIgnoreCase(nom.trim())).collect(toList());
		}
		
		return collabs;
	}
	
	@GetMapping(path = "/{matricule}")	
	public Collaborateur getCollaborateur(@PathVariable("matricule") String matricule) {
		if(matricule == null || matricule.trim().isEmpty()) {
			return null;
		}
		Collaborateur c = collaborateurRepository.findByMatricule(matricule.trim());
		return c;
	}
	
	@PutMapping(path = "/{matricule}")	
	public Collaborateur setCollaborateur(@PathVariable("matricule") String matricule, @RequestBody Collaborateur collab) {
		if(matricule == null || matricule.trim().isEmpty()) {
			return null;
		}
		
		Collaborateur toUpdate = collaborateurRepository.findByMatricule(matricule.trim());
		if(toUpdate == null) {
			return null;
		}
		
		updateCollaborateur(toUpdate, collab);

		return toUpdate;
	}
	
	@GetMapping(path = "/{matricule}/banque")	
	public Banque getBanqueCollaborateur(@PathVariable("matricule") String matricule) {
		if(matricule == null || matricule.trim().isEmpty()) {
			return null;
		}

		Collaborateur c = collaborateurRepository.findByMatricule(matricule.trim());
		
		if(c == null) {
			return null;
		}
		
		return c.getBanque();
	}
	
	
	@PutMapping(path = "/{matricule}/banque")	
	public Banque setBanqueCollaborateur(@PathVariable("matricule") String matricule, @RequestBody Banque banque) {
		if(matricule == null || matricule.trim().isEmpty()) {
			return null;
		}

		Collaborateur c = collaborateurRepository.findByMatricule(matricule.trim());
		
		if(c == null) {
			return null;
		}
		c.setBanque(banque); collaborateurRepository.save(c);
		return c.getBanque();
	}
	
	
	private void updateCollaborateur(Collaborateur toUpdate, Collaborateur receive) {
		
		if(receive.getActif() != toUpdate.getActif()) {
			toUpdate.setActif(receive.getActif());
		}
		
		if(receive.getAdresse().equalsIgnoreCase(toUpdate.getAdresse())) {
			toUpdate.setAdresse(receive.getAdresse().toLowerCase());
		}
		
		if(receive.getIntitulePoste().equalsIgnoreCase(toUpdate.getIntitulePoste())) {
			toUpdate.setIntitulePoste(receive.getIntitulePoste().toLowerCase());
		}
		
		collaborateurRepository.save(toUpdate);
	}
	
}
