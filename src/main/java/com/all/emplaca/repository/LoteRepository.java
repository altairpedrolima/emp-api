package com.all.emplaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.all.emplaca.entities.Lote;

//http://www.baeldung.com/spring-data-repositories

public interface LoteRepository extends JpaRepository<Lote, Long> {	
	List<Lote> findByFabricanteId(Long idFabricante); 

}