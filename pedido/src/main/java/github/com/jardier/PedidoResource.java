package github.com.jardier;

import java.util.List;

import org.bson.types.ObjectId;

import github.com.jardier.model.Localizacao;
import github.com.jardier.model.Pedido;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.ext.web.Router;
import io.vertx.mutiny.ext.web.handler.sockjs.SockJSHandler;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    Vertx vertx;

    @Inject
    EventBus eventBus;

    void startup(@Observes Router router) {
        router.route("/localizacoes/*")
                .subRouter(localizacaoHandler());
    }

    private Router localizacaoHandler() {
        SockJSHandler handler = SockJSHandler.create(vertx);

        PermittedOptions permitted = new PermittedOptions();
        permitted.setAddress("novaLocalizacao");

        SockJSBridgeOptions bridgeOptions = new SockJSBridgeOptions()
                .addOutboundPermitted(permitted);

        return handler.bridge(bridgeOptions);
    }

    @GET
    public List<PanacheMongoEntityBase> buscar() {
        return Pedido.listAll();
    }

    @POST
    @Path("{idPedido}/localizacao")
    public Pedido novaLocalizacao(@PathParam("idPedido") String idPedido, Localizacao localizacao) {
        Pedido pedido = Pedido.findById(new ObjectId(idPedido));

        pedido.localizacaoEntregador = localizacao;
        String json = JsonbBuilder.create().toJson(localizacao);
        eventBus.send("novaLocalizacao", json);
        pedido.persistOrUpdate();
        return pedido;
    }
}
