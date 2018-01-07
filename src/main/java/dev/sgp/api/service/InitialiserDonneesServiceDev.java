package dev.sgp.api.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sgp.api.entite.Banque;
import dev.sgp.api.entite.Collaborateur;
import dev.sgp.api.entite.Departement;
import dev.sgp.api.repository.CollaborateurRepository;
import dev.sgp.api.repository.DepartementRepository;


@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	CollaborateurRepository collaborateurRepository;
	@Autowired
	DepartementRepository departementRepository;
	
	public InitialiserDonneesServiceDev(){
		
	}

	@Override
	@Transactional
	public void initialiser() {
		
		em.persist(new Departement("Comptabilite"));
		em.persist(new Departement("Ressources Humaines"));
		em.persist(new Departement("Informatique"));
		em.persist(new Departement("Administratif"));
		
		List<Departement> depts = departementRepository.findAll();
		
		final String NOM_DOMAINE_SOCIETE = "societe.com";
		String adresse = "rue intel, 44000 nantes";
		String numeroSecuriteSociale = "1236548970123654";

		char male = 'm';
		char femelle = 'f' ;
		
		Collaborateur c = new Collaborateur("jean", "pierre", LocalDate.of(1985, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("comptable");
		c.setDepartement(depts.get(0));
		
		Banque b = new Banque();
		b.setDenomination("lcl");
		b.setBic("FLDPE5241634DLDLF");
		b.setIban("FLDPE5241634DLDLF");
		c.setBanque(b);
		em.persist(c);
		
		
		c = new Collaborateur("alfred", "pierre", LocalDate.of(1995, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("developpeur");
		c.setDepartement(depts.get(2));
		c.setActif(false);
		em.persist(c);
		
		c = new Collaborateur("jean", "kevin", LocalDate.of(1989, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("chef de projet");
		c.setDepartement(depts.get(2));
		em.persist(c);
		
		c = new Collaborateur("ruth", "alicia", LocalDate.of(1995, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(femelle);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("Charge de recherche");
		c.setDepartement(depts.get(1));
		c.setActif(false);
		em.persist(c);
		
		c = new Collaborateur("john", "doe", LocalDate.of(1975, 5, 16), adresse, numeroSecuriteSociale);
		c.setCivilite(male);
		c.setEmailPro(c.getPrenom() +"."+ c.getNom() + "@"+ NOM_DOMAINE_SOCIETE);
		c.setIntitulePoste("directeur");
		c.setDepartement(depts.get(3));
		em.persist(c);
		
	}

}
