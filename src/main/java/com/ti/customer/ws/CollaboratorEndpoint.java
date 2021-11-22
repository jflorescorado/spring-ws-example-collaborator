package com.ti.customer.ws;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ti.customer.entity.Collaborator;
import com.ti.customer.schema.*;

import com.ti.customer.service.CollaboratorServiceImpl;

@Endpoint
public class CollaboratorEndpoint<collaboratorResponse>{

	private static final String NAMESPACE_URI = "http://www.example.org/Collaborator";
	
	@Autowired
	CollaboratorServiceImpl customerServiceImpl;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "collaboratorRequest")
	@ResponsePayload
	public JAXBElement<CollaboratorResponse> getCollaborator(@RequestPayload JAXBElement<CollaboratorRequest> request) {
		
		ObjectFactory objectFactory = new ObjectFactory();
		CollaboratorResponse collaboratorResponsefactory = objectFactory.createCollaboratorResponse();
		JAXBElement<CollaboratorResponse> collaboratorResponse = objectFactory.createCollaboratorResponse(collaboratorResponsefactory);
		
		try {
			int action = request.getValue().getAction();
			
			if (action ==1) {
				
				CollaboratorResponse collaboratorResponseObject = new CollaboratorResponse();
				List<Collaborator> listCollaborator = customerServiceImpl.getAllCollaborator();
				
				if (listCollaborator != null) {
					for(Collaborator collab:listCollaborator) {
						CollaboratorDetails collaboratorDetails = new CollaboratorDetails();
						collaboratorDetails.setId(collab.getCollabid());
						collaboratorDetails.setName(collab.getCollabname());
						collaboratorDetails.setAlias(collab.getCollabalias());
						collaboratorDetails.setDui(collab.getCollabdui());
						collaboratorDetails.setEmail(collab.getCollabemail());
						collaboratorDetails.setImageCollaborator(collab.getCollabimage());
						collaboratorDetails.setPassword(collab.getCollabpassword());
						
						collaboratorResponseObject.getCollaboratorDetails().add(collaboratorDetails);
						
					}
				}
				collaboratorResponse.setValue(collaboratorResponseObject);
				
			}else if(action ==2) {
				CollaboratorResponse collaboratorResponseObject = new CollaboratorResponse();
				Collaborator collaborator = customerServiceImpl.getCollaboratorById(new Integer(request.getValue().getId()));
				CollaboratorDetails collaboratorDetails = new CollaboratorDetails();
				if (collaborator != null) {
				collaboratorDetails.setId(collaborator.getCollabid());
				collaboratorDetails.setName(collaborator.getCollabname());
				collaboratorDetails.setAlias(collaborator.getCollabalias());
				collaboratorDetails.setDui(collaborator.getCollabdui());
				collaboratorDetails.setEmail(collaborator.getCollabemail());
				collaboratorDetails.setImageCollaborator(collaborator.getCollabimage());
				collaboratorDetails.setPassword(collaborator.getCollabpassword());
				collaboratorResponseObject.getCollaboratorDetails().add(collaboratorDetails);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return collaboratorResponse;
	}
}
