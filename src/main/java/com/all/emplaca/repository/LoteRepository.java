package com.all.emplaca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.all.emplaca.entities.Lote;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false) // para evitar RestResource automático que estava gerando problemas no post com uri /lotes x /api/lotes
public interface LoteRepository extends JpaRepository<Lote, Long> {	
	Optional<List<Lote>> findByFabricanteId(Long idFabricante);
}