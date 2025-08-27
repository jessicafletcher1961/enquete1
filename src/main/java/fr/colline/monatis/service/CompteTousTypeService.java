package fr.colline.monatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.model.Compte;
import fr.colline.monatis.repository.CompteRepository;

@Service
public class CompteTousTypeService extends CompteService<Compte> {

	@Autowired private CompteRepository<Compte> repository;

	@Override
	protected Class<Compte> getTClass() {

		return Compte.class;
	}

	@Override
	public CompteRepository<Compte> getRepository() {

		return repository;
	}
}
