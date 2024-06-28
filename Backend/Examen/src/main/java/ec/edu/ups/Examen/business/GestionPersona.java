package ec.edu.ups.Examen.business;

import java.util.List;

import ec.edu.ups.Examen.dao.PersonaDao;
import ec.edu.ups.Examen.model.Persona;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionPersona {

	@Inject
	private PersonaDao daoPersona;

	private final Tracer tracer = GlobalTracer.get();

	public void addUser(Persona p) {
		Span span = tracer.buildSpan("addUser").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
			daoPersona.addUser(p);
		} catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		} finally {
			span.finish();
		}
	}

	public void deleteUserByCode(int code) {
		daoPersona.deleteUserByCode(code);
	}

	public void deleteUser(Persona p) {
		daoPersona.deleteUser(p);
	}

	public void getUser(int code) {
		daoPersona.getUser(code);
	}

	public void updateUser(Persona p) {
		daoPersona.updateUser(p);
	}

	public List<Persona> getAll() {
		Span span = tracer.buildSpan("List").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
			return daoPersona.getAll();
		} catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		} finally {
			span.finish();
		}
	}

	public void deleteUserByCedula(String cedula) {
		Span span = tracer.buildSpan("deleteUserByCedula").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
			daoPersona.deleteUserByCedula(cedula);
			;
		} catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		} finally {
			span.finish();
		}
	}

	public void updateUserByCedula(Persona p) {
		Span span = tracer.buildSpan("updateUserByCedula").start();
		try (Scope scope = tracer.scopeManager().activate(span)) {
			daoPersona.updateUserByCedula(p);
		} catch (Exception e) {
			span.log(e.getMessage());
			throw e;
		} finally {
			span.finish();
		}
	}

	public Persona getPersonaByCedula(String cedula) {
		return daoPersona.getUserByCedula(cedula);

	}

}
