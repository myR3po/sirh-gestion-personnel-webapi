package dev.sgp.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import dev.sgp.api.entite.Departement;
import dev.sgp.api.repository.DepartementRepository;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartementController.class)
@AutoConfigureMockMvc
public class DepartementControllerTest {
		
	@Autowired
    private MockMvc mvc;

    @MockBean
    private DepartementRepository departementRepository;
	
	
    
  @Test
	public void testListerDepartement() throws Exception {
    Departement compta = new Departement("Comptabilite");
    Departement rh = new Departement("Ressources Humaines");
    
	  List<Departement> allDepartements = Arrays.asList(compta, rh);
 
    given(departementRepository.findAll()).willReturn(allDepartements);
 
    mvc.perform(get("/departements")
      .contentType(MediaType.APPLICATION_JSON))    
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(2)))
      .andExpect(jsonPath("$[0].nom", is(compta.getNom())));
	}
	
}
