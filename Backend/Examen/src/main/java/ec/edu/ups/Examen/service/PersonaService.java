package ec.edu.ups.Examen.service;

import java.util.List;

import ec.edu.ups.Examen.business.GestionPersona;
import ec.edu.ups.Examen.model.Persona;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("personas")
public class PersonaService {

	@Inject
	private GestionPersona gPersona;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(value = "crear")
	public Response crear(Persona persona) {
		try {
			gPersona.addUser(persona);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.ok(error).build();
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(value = "actualizar")
    public Response actualizar(Persona persona) {
        try {
            gPersona.updateUserByCedula(persona);
            ErrorMessage error = new ErrorMessage(1, "OK");
            return Response.ok(error).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(99, e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Path(value = "eliminar")
    public Response borrar(@QueryParam("cedula") String cedula) {
        try {
            gPersona.deleteUserByCedula(cedula);
            ErrorMessage error = new ErrorMessage(1, "OK");
            return Response.ok(error).build();
        } catch (Exception e) {
            // Manejo de la excepción
            return Response.status(Response.Status.NOT_FOUND).entity("Error: " + e.getMessage()).build();
        }
    }



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes() {
		List<Persona> clientes = gPersona.getAll();
		if (clientes.size() > 0)
			return Response.ok(clientes).build();
		ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	}

    @GET
    @Path(value = "get")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response leerPorCedula(@QueryParam("cedula") String cedula) {
        try {
            Persona persona = gPersona.getPersonaByCedula(cedula);
            return Response.ok(persona).build();
        } catch (Exception e) {
            ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	//@Produces("application/json")
//	public Response leer(@QueryParam("dni") String cedula, @QueryParam("nombre") String nombre) {
//		try{
//			System.out.println("cedula " +  cedula + " nom=" + nombre);
//			Cliente cli = gClientes.getClientePorCedula(cedula);
//			return Response.ok(cli).build();
//		}catch (Exception e) {
//			// TODO: handle exception
//			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
//			return Response.status(Response.Status.NOT_FOUND)
//					.entity(error)
//					.build();
//		}
//	}

//	@GET
//	@Path("{dni}/{nombre}")
//	@Produces(MediaType.APPLICATION_JSON)
//	//@Produces("application/json")
//	public Response leer2(@PathParam("dni") String cedula, @PathParam("nombre") String nombre) {
//		try{
//			System.out.println("cedula " +  cedula + " nom=" + nombre);
//			Cliente cli = gClientes.getClientePorCedula(cedula);
//			return Response.ok(cli).build();
//		}catch (Exception e) {
//			// TODO: handle exception
//			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
//			return Response.status(Response.Status.NOT_FOUND)
//					.entity(error)
//					.build();
//		}
//	}
}