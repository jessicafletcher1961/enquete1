package fr.colline.monatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.colline.monatis.repository.BanqueRepository;

@RunWith(MockitoJUnitRunner.class)
public class BanqueServiceTests {

	@Mock private BanqueRepository banqueRepository;
	
	@InjectMocks private BanqueService banqueService; 

	@Test public void testBanque() {
	}	
}
