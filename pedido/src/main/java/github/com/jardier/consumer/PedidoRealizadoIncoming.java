package github.com.jardier.consumer;

import java.util.ArrayList;

import org.bson.types.Decimal128;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import github.com.jardier.dto.PedidoRealizadoDTO;
import github.com.jardier.dto.PratoPedidoDTO;
import github.com.jardier.model.Pedido;
import github.com.jardier.model.Prato;
import github.com.jardier.model.Restaurante;
import github.com.jardier.service.ESService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;

@ApplicationScoped
public class PedidoRealizadoIncoming {

    @Inject
    ESService elastic;

    @Incoming("pedidos")
    public void receberPedidos(PedidoRealizadoDTO dto) {
        System.out.println("Recebendo pedidos do marketplace");
        Pedido pedido = new Pedido();
        pedido.cliente = dto.cliente;
        pedido.pratos = new ArrayList<>();
        dto.pratos.forEach(prato -> pedido.pratos.add(from(prato)));

        Restaurante restaurante = new Restaurante();
        restaurante.nome = dto.restaurante.nome;
        pedido.restaurante = restaurante;

        String json = JsonbBuilder.create().toJson(dto);
        elastic.index("pedidos", json);

        pedido.persist();

    }

    private Prato from(PratoPedidoDTO dto) {
        Prato prato = new Prato();
        prato.descricao = dto.descricao;
        prato.nome = dto.nome;
        prato.preco = new Decimal128(dto.preco);
        return prato;
    }

}
