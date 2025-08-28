package fr.colline.monatis.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import fr.colline.monatis.repository.CategorieRepository;

public class CategorieServiceTests {

	@Mock private CategorieRepository categorieRepository;
	
	@InjectMocks private CategorieService categorieService; 
	
	@Test public void testCategorie() {
		
	}	
}
