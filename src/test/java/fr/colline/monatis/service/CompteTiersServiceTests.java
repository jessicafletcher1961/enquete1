package fr.colline.monatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.colline.monatis.model.CompteTiers;
import fr.colline.monatis.repository.CompteTiersRepository;

@RunWith(MockitoJUnitRunner.class)
public class CompteTiersServiceTests {

	@Mock private CompteTiersRepository compteTiersRepository;
	
	@InjectMocks private CompteTiersService compteTiersService; 
	
	@Test public void testCompteTiers() {
		
		CompteTiers COMPTE_0 = new CompteTiers(
				"MENAGE01",
				"Ménage");
		COMPTE_0.setId(null);
		
		CompteTiers COMPTE_1 = new CompteTiers(
				"MENAGE01",
				"Ménage");
		COMPTE_1.setId(1L);
		
		CompteTiers COMPTE_2 = new CompteTiers(
				"MENAGE01",
				"Ménage Le Raincy");
		COMPTE_2.setId(1L);
	}	
}
