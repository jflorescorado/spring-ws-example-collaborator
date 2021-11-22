package com.ti.customer.service;

import java.util.List;

import com.ti.customer.entity.Collaborator;

public interface CollaboratorService {

	public Collaborator getCollaboratorById(int id);
	
	public List<Collaborator> getAllCollaborator();
	
	
}
