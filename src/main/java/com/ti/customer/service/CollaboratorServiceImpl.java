package com.ti.customer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.customer.dao.CollaboratorRepository;
import com.ti.customer.entity.Collaborator;

@Service("collaboratorservice")
@Transactional
public class CollaboratorServiceImpl implements CollaboratorService{

	private static final Logger logger = LogManager.getLogger(CollaboratorServiceImpl.class);
	
	@Autowired
	CollaboratorRepository collaboratorRepository;
	
	@Override
	public Collaborator getCollaboratorById(int id) {
		// TODO Auto-generated method stub
		return collaboratorRepository.getCollaborator(id);
	}

	@Override
	public List<Collaborator> getAllCollaborator() {
		// TODO Auto-generated method stub
		logger.info("TAMANO DE LA LISTA COLABORADORES EN LA CAPA DE SERVICE ES :" + collaboratorRepository.getListAllCollaborator());
		return collaboratorRepository.getListAllCollaborator();
	}

}
