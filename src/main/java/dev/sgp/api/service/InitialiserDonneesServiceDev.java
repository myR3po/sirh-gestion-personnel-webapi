package dev.sgp.api.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import dev.sgp.api.entite.Departement;


@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	ApplicationContext ctx;
		
	@PersistenceContext
	EntityManager em;
	
	public InitialiserDonneesServiceDev(){
		
	}

	@Override
	@Transactional
	public void initialiser() {
		
		em.persist(new Departement("Comptabilite"));
		em.persist(new Departement("Ressources Humaines"));
		em.persist(new Departement("Informatique"));
		em.persist(new Departement("Administratif"));
		
	}

}
