package com.ti.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ti.customer.entity.Collaborator;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer> {

	@Query("select c from Collaborator c where c.collabid = :id")
	public Collaborator getCollaborator(@Param("id") int id);
	
	@Query("select c from Collaborator c")
	public List<Collaborator> getListAllCollaborator();
}
