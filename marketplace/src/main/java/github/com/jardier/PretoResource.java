package github.com.jardier;


import github.com.jardier.dto.PratoDTO;
import github.com.jardier.model.Prato;
import io.smallrye.mutiny.Multi;

import io.vertx.mutiny.sqlclient.Pool;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path ("/pratos")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
public class PretoResource {

    @Inject 
    Pool pgPool;

    @GET 
    public Multi<PratoDTO> buscarPratos() {
        return Prato.findAll(pgPool);

    }

}
