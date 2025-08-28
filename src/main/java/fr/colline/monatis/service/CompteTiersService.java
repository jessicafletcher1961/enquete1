package fr.colline.monatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.colline.monatis.model.CompteTiers;
import fr.colline.monatis.repository.CompteTiersRepository;

@Service public class CompteTiersService extends CompteService<CompteTiers>{

	@Autowired private CompteTiersRepository compteTiersRepository;

	@Override
	protected Class<CompteTiers> getTClass() {

		return CompteTiers.class;
	}
	
	public CompteTiersRepository getRepository() {
		
		return compteTiersRepository;
	}
}
