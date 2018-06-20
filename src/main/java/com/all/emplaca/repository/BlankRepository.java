package com.all.emplaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.all.emplaca.entities.Blank;

public interface BlankRepository extends JpaRepository<Blank, Long>{
	
	@Query(value = "SELECT nextval('serial_blanks_seq')", nativeQuery = true)
	public long GetNextValOfSerialBlankSeq();

}
