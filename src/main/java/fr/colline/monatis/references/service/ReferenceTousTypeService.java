package fr.colline.monatis.references.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.exception.ServiceTechniqueErreur;
import fr.colline.monatis.exception.ServiceTechniqueException;
import fr.colline.monatis.references.model.Reference;
import fr.colline.monatis.references.repository.ReferenceRepository;

@Service
public class ReferenceTousTypeService extends ReferenceService<Reference> {

	@Autowired private ReferenceRepository<Reference> repository;

	@Override
	protected Class<Reference> getTClass() {

		return Reference.class;
	}

	@Override
	protected ReferenceRepository<Reference> getRepository() {
		
		return repository;
	}
	
	@Override
	public Reference rechercherParNom(String nom) throws ServiceTechniqueException {
		
		throw new ServiceTechniqueException(
				ServiceTechniqueErreur.PROG_RECHERCHE_PAR_NOM_IMPOSSIBLE,
				getTClass().getSimpleName());
	}
}
