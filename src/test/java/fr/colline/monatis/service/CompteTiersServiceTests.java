package fr.colline.monatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.colline.monatis.repository.CompteTiersRepository;

@RunWith(MockitoJUnitRunner.class)
public class CompteTiersServiceTests {

	@Mock private CompteTiersRepository compteTiersRepository;
	
	@InjectMocks private CompteTiersService compteTiersService; 
	
	@Test public void testCompteTiers() {
	}	
}
