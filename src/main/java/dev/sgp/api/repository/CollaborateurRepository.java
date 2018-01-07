package dev.sgp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sgp.api.entite.Collaborateur;
import dev.sgp.api.entite.Departement;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Integer> {
	List<Collaborateur> findByDepartement(Departement departement);
	Collaborateur findByMatricule(String matricule);
}
