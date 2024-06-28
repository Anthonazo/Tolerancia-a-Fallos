package ec.edu.ups.Examen.business;

import java.util.List;

import ec.edu.ups.Examen.dao.PersonaDao;
import ec.edu.ups.Examen.model.Persona;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class GestionDatos {

	@Inject
	private PersonaDao daoPersona;
	
	@PostConstruct
	public void init() {
				
		Persona p = new Persona("0107486292", "Anthony", "Moya", 24);
		daoPersona.addUser(p);
		p = new Persona("1801856285", "Patricio", "Moya", 60);
		daoPersona.addUser(p);
		p = new Persona("0107486284", "Francis", "Moya", 30);
		daoPersona.addUser(p);
		p = new Persona("0101699882", "Ana", "Ochoa", 58);
		daoPersona.addUser(p);
		
		List<Persona> personas = daoPersona.getAll();
		for(Persona person: personas) {
			System.out.println(person.toString());
		}
		
		
	}
	
}
