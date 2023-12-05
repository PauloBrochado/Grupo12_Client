package ClienteServer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import stand.Cliente;
import stand.ClienteService;

@Path("/cliente")
public class ClienteRESTService {
	private ClienteService clienteService;
	
	public ClienteRESTService(EntityManager em) {
		this.clienteService = new ClienteService(em);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(Request a) {
		return "REST Server : Olá, eu sou o controlador de Clientes";
	}
	
	@GET
	@Path("/getUtilizadores")
	public Response getClientes() {
		List<Cliente> clientes = clienteService.listClients();
		
		return Response.status(Response.Status.OK)
				.entity(clientes)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@GET
	@Path("/getClientes/{id}")
	public Response getCliente(@PathParam("id")int id) {
		Cliente clienteResponse = clienteService.findClient(id);
		
		return Response.status(Response.Status.OK)
				.entity(clienteResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@POST
	@Path("/addCliente")
	public Response addCliente(Cliente cli) {
		Cliente clienteResponse = clienteService.updateClient(cli);
		
		return Response.status(Response.Status.CREATED)
				.entity(clienteResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@PUT
	@Path("/updateCliente")
	public Response updateCliente(int id,String pass,String mail,String nome,int nnr) {
		Cliente clienteResponse = clienteService.updateClient(0,null,null,null,0);
		
		return Response.status(Response.Status.OK)
				.entity(clienteResponse)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	@DELETE
	@Path("/deleteCliente/{id}")
	public Response deleteCliente(@PathParam("id") int id) {
		Cliente clienteRemoved = clienteService.removeClient(id);
		
		return Response.status(Response.Status.OK)
				.entity(clienteRemoved)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}