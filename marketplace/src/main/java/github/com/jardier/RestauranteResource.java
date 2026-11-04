package github.com.jardier;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import github.com.jardier.dto.PratoDTO;
import github.com.jardier.model.Prato;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.sqlclient.Pool;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path ("restaurantes")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject 
    Pool client;


    @GET 
    @Path ("{idRestaurante}/pratos")
    public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
        return Prato.findAll(client, idRestaurante);
    }

}
