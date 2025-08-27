package fr.colline.monatis.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import fr.colline.monatis.model.Banque;
import fr.colline.monatis.repository.BanqueRepository;

@RunWith(MockitoJUnitRunner.class)
public class BanqueServiceTests {

	@Mock private BanqueRepository banqueRepository;
	
	@InjectMocks private BanqueService banqueService; 

	@Test public void testBanque() {

		Banque BANQUE_0 = new Banque(
				"Caisse d'épargne", 
				null);
		BANQUE_0.setId(null);
		
		Banque BANQUE_1 = new Banque(
				"Caisse d'épargne", 
				null);
		BANQUE_1.setId(1L);
		
		Banque BANQUE_2 = new Banque(
				"Caisse d'Epargne", 
				"Caisse d'Epargne et de Prévoyance Ile-de-France");
		BANQUE_2.setId(1L);
		
//		Banque banque;
//
//		// Ajout de BANQUE_0 en base : ajout de 1L en id -> BANQUE_1
//		Mockito.when(banqueRepository.save(BANQUE_0)).thenReturn(BANQUE_1);
//		banque = banqueService.enregistrer(BANQUE_0);
//		Assert.assertEquals(banque, BANQUE_1);
//		Mockito.verify(banqueRepository, times(1)).save(BANQUE_0);
//
//		// Modification du nom et du commentaire -> BANQUE_2
//		Mockito.when(banqueRepository.save(BANQUE_2)).thenReturn(BANQUE_2);
//		banque = banqueService.enregistrer(BANQUE_2);
//		Assert.assertEquals(banque, BANQUE_2);
//		Mockito.verify(banqueRepository, times(1)).save(BANQUE_2);
//		
//		// Suppression de la banque
//		Mockito.doAnswer(new Answer<Banque>() {
//			public Banque answer(InvocationOnMock invocation) {
////				Object[] args = invocation.getArguments();
////				Object mock = invocation.getMock();
//				return null;
//			}}).when(banqueRepository).deleteById(1L);
//		banqueService.retirer(BANQUE_2.getId());
//		Mockito.verify(banqueRepository, times(1)).deleteById(BANQUE_2.getId());
//
//		// Plus de banque en base
//		Mockito.when(banqueRepository.findById(1L)).thenReturn(Optional.empty());
//		Mockito.when(banqueRepository.findByNom(BANQUE_1.getNom())).thenReturn(null);
//		Mockito.when(banqueRepository.findByNom(BANQUE_2.getNom())).thenReturn(null);
//		
//		banque = banqueService.rechercherParId(1L);
//		Assert.assertNull(banque);
//		Mockito.verify(banqueRepository, times(1)).findById(1L);
//		
//		banque = banqueService.rechercherParNom(BANQUE_1.getNom());
//		Assert.assertNull(banque);
//		Mockito.verify(banqueRepository, times(1)).findByNom(BANQUE_1.getNom());
//		
//		banque = banqueService.rechercherParNom(BANQUE_2.getNom());
//		Assert.assertNull(banque);
//		Mockito.verify(banqueRepository, times(1)).findByNom(BANQUE_2.getNom());
	}	
}
