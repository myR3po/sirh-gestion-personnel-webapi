package dev.sgp.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import dev.sgp.api.entite.Collaborateur;
import dev.sgp.api.repository.CollaborateurRepository;
import dev.sgp.api.repository.DepartementRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CollaborateurController.class)
@AutoConfigureMockMvc
public class CollaborateurControllerTest {
	  
  @Autowired
  private MockMvc mvc;
  
  @MockBean
	private CollaborateurRepository collaborateurRepository;
  
  @MockBean
  private DepartementRepository departementRepository;
  
  private Collaborateur compta ;
  private Collaborateur dev ;
  
  @Before
  public void setup() {
    final String NOM_DOMAINE_SOCIETE = "societe.com";
    final String adresse = "rue intel, 44000 nantes";
    final String numeroSecuriteSociale = "1236548970123654";

    final char male = 'm';
    
    compta = new Collaborateur("jean", "pierre", LocalDate.of(1985, 5, 16), adresse, numeroSecuriteSociale);
    compta.setCivilite(male);
    compta.setEmailPro(compta.getPrenom() +'.'+ compta.getNom() + '@'+ NOM_DOMAINE_SOCIETE);
    compta.setIntitulePoste("comptable");
        
    dev = new Collaborateur("alfred", "pierre", LocalDate.of(1995, 5, 16), adresse, numeroSecuriteSociale);
    dev.setCivilite(male);
    dev.setEmailPro(dev.getPrenom() +'.'+ dev.getNom() + '@'+ NOM_DOMAINE_SOCIETE);
    dev.setIntitulePoste("developpeur");
    dev.setActif(false);
    
    List<Collaborateur> collaborateurs = Arrays.asList(compta, dev);
    
    given(collaborateurRepository.findAll()).willReturn(collaborateurs);
    when(collaborateurRepository.findByMatricule(compta.getMatricule()))
    .thenReturn(compta);

  }
		
	@Test
	public void testListerCollaborateur() throws Exception {

	  mvc.perform(get("/collaborateurs")
      .contentType(MediaType.APPLICATION_JSON))
	  .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)))
      .andExpect(jsonPath("$[0].nom", is(compta.getNom())));
    
	}
	
	@Test
  public void testGetCollaborateurAvecMatriculeVide() throws Exception {    
	 
	  MvcResult result = mvc.perform(get("/collaborateurs/    /")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andReturn();
	  
	  assertThat(result.getResponse().getContentAsString()).isEmpty();
  }
	
	@Test
  public void testGetCollaborateur() throws Exception {
	  mvc.perform(get("/collaborateurs/" + compta.getMatricule())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.nom", is(compta.getNom())));
  }


	
	
}
