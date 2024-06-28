package ec.edu.ups.Examen.dao;
import java.util.List;


import ec.edu.ups.Examen.model.Persona;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;



@Stateless
public class PersonaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addUser(Persona p) {
		em.persist(p);
	}
	
	public void deleteUser(Persona p) {
		Persona persona = em.find(Persona.class, p.getCodigo());
		em.remove(persona);
	}
	
	public void deleteUserByCode(int code) {
		Persona persona = em.find(Persona.class, code);
		em.remove(persona);
	}
	
	public Persona getUser(int code) {
		Persona persona = em.find(Persona.class, code);
		return persona;
	}
	
	public void updateUser(Persona p) {
		em.merge(p);
	}
	
	public List<Persona> getAll(){
		String jpql = "SELECT c FROM Persona c"; 
		Query q = em.createQuery(jpql, Persona.class);
		return q.getResultList();
	}
	
    public void deleteUserByCedula(String cedula) {
        Persona persona = em.createQuery("SELECT p FROM Persona p WHERE p.cedula = :cedula", Persona.class)
                                       .setParameter("cedula", cedula)
                                       .getSingleResult();
        if (persona != null) {
            em.remove(persona);
        }
    }
    
    public void updateUserByCedula(Persona p) {
        Persona persona = em.createQuery("SELECT p FROM Persona p WHERE p.cedula = :cedula", Persona.class)
                                       .setParameter("cedula", p.getCedula())
                                       .getSingleResult();
        if (persona != null) {
            persona.setNombre(p.getNombre());
            persona.setApellido(p.getApellido());
            persona.setEdad(p.getEdad());
            em.merge(persona);
        }
    }
    
    public Persona getUserByCedula(String cedula) throws NoResultException {
        return em.createQuery("SELECT p FROM Persona p WHERE p.cedula = :cedula", Persona.class)
                             .setParameter("cedula", cedula)
                             .getSingleResult();
    }
}
