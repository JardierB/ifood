package github.com.jardierB;

import java.util.List;

import github.com.jardierB.model.Restaurante;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/restaurates")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<Restaurante> restaurantes () {
        return Restaurante.listAll();
    }
}
