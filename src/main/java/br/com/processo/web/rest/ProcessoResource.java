package br.com.processo.web.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import br.com.processo.domain.Comarca;
import br.com.processo.domain.Processo;
import br.com.processo.service.ComarcaService;
import br.com.processo.service.ProcessoService;
import br.com.processo.service.exception.RegrasException;

@Path("/processo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProcessoResource {
	
	@Inject
	private ProcessoService processoService;
	
	@Inject
	private ComarcaService comarcaService; 
	
	@Inject
	Logger log;
	
	@GET
	@Path("/{numprocesso}")
	public Response buscarProcesso(@PathParam("numprocesso") Integer numprocesso) {
		Processo processo = processoService.buscarProcesso(numprocesso);
		return Optional.ofNullable(processo)
					.map(result -> Response.status(Status.OK).entity(result).build())
					.orElse(Response.status(Status.NOT_FOUND).build());
	}

	@POST
	public Response cadastrarProcesso(Processo processo){
		Comarca comarca = processo.getComarca();
		Comarca comarcaBusca = comarcaService.buscarComarca(comarca.getId());
	    processo.setComarca(comarcaBusca);
	    
	    try {
			processo = processoService.cadastrarProcesso(processo);
		} catch (RegrasException e) {
			log.error(e.getMessage(),e);
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.status(Status.OK).entity(processo).build();
	}
	
}
