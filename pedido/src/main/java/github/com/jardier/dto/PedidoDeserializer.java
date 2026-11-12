package github.com.jardier.dto;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidoRealizadoDTO> {

    public PedidoDeserializer() {
         super(PedidoRealizadoDTO.class);

    }

}
