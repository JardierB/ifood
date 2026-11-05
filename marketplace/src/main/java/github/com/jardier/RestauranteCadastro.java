package github.com.jardier;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import github.com.jardier.model.Restaurante;
import io.vertx.mutiny.sqlclient.Pool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

@ApplicationScoped
public class RestauranteCadastro {

    @Inject 
    Pool pool;

    @Incoming("restaurantes")
    public void receberRestaurante(String json) {
        Jsonb create = JsonbBuilder.create();
        Restaurante restaurante = create.fromJson(json, Restaurante.class);
        System.out.println("-------------------------");
        System.out.println(json);
        System.out.println("-------------------------");
        System.out.println(restaurante.toString());

        restaurante.persiste(pool);
    }

}
