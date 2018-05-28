package com.all.emplaca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.all.emplaca.entities.Fabricante;

// http://www.baeldung.com/spring-data-repositories

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

	Optional<Fabricante> findByCnpj(String name);

}