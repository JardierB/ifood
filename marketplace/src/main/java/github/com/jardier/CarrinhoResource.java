package github.com.jardier;

import java.util.List;
import java.util.stream.Collectors;


import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import github.com.jardier.dto.PedidoRealizadoDTO;
import github.com.jardier.dto.PratoDTO;
import github.com.jardier.dto.PratoPedidoDTO;
import github.com.jardier.dto.RestauranteDTO;
import github.com.jardier.model.Prato;
import github.com.jardier.model.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Pool;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

    private static final String CLIENTE = "a";

    @Inject
    Pool client;

    @Inject
    @Channel("pedidos")
    Emitter<PedidoRealizadoDTO> emitterPedido;

    @GET
    public Uni<List<PratoCarrinho>> buscarCarrinho() {
        return PratoCarrinho.findCarrinho(client, CLIENTE);
    }

    @POST
    @Path("/prato/{idPrato}")
    public Uni<String> adicionarPrato(@PathParam("idPrato") Long idPrato) {

        PratoCarrinho prato = new PratoCarrinho();
        prato.cliente = CLIENTE;
        prato.prato = idPrato;

        return PratoCarrinho.save(client, CLIENTE, idPrato);

    }

    @POST
    @Path("/realizar-pedido")
    public Uni<Boolean> finalizar() throws JsonProcessingException {
        PedidoRealizadoDTO pedido = new PedidoRealizadoDTO();
        pedido.cliente = CLIENTE;

        List<PratoCarrinho> pratoCarrinho = PratoCarrinho.findCarrinho(client, CLIENTE).await().indefinitely();
        // Utilizar mapstruts
        List<PratoPedidoDTO> pratos = pratoCarrinho.stream()
                .map(this::from)
                .collect(Collectors.toList());
        pedido.pratos = pratos;

        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.nome = "nome restaurante";
        pedido.restaurante = restaurante;

        return Uni.createFrom()
                .completionStage(emitterPedido.send(pedido))
                .onItem()
                .invoke(() -> System.out.println("Pedido enviado ao Kafka!"))
                .onItem()
                .transformToUni(ignored -> PratoCarrinho.delete(client, CLIENTE));

    }

    private PratoPedidoDTO from(PratoCarrinho pratoCarrinho) {
        System.out.println("PratoCarrinho ID: " + pratoCarrinho.prato);

        PratoDTO dto = Prato.findById(client, pratoCarrinho.prato)
                .await().indefinitely();

        System.out.println("PratoDTO: " + dto);

        if (dto == null) {
            throw new IllegalStateException(
                    "Não foi encontrado o prato: " + pratoCarrinho.prato);
        }

        return new PratoPedidoDTO(dto.nome, dto.descricao, dto.preco);
    }
}
