package github.com.jardier.consumer;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import github.com.jardier.dto.PedidoRealizadoDTO;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRealizadoIncoming {

    @Incoming("pedidos")
    public  void receberPedidos(PedidoRealizadoDTO dto) {
        System.out.println("#######################################");
        System.out.println("Recebendo pedidos do marketplace");
        System.out.println(dto);
        System.out.println("#######################################");

    }

}
