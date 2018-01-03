package dev.sgp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.sgp.api.entite.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {

}
