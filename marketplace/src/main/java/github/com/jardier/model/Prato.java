package github.com.jardier.model;

import java.math.BigDecimal;

import github.com.jardier.dto.PratoDTO;
import io.smallrye.mutiny.Multi;

import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Prato {

    public Long id;
    public String nome;
    public String descricao;
    public BigDecimal preco;
    public Restaurante restaurante;

    public static Multi<PratoDTO> findAll(Pool pgPool) {
        return pgPool.preparedQuery("SELECT * FROM pratos")
                .execute()
                .onItem()
                .transformToMulti(rowSet -> Multi.createFrom().iterable(rowSet))
                .onItem()
                .transform(PratoDTO::from);

    }

    public static Multi<PratoDTO> findAll(Pool client, Long idRestaurante) {
        return client.preparedQuery(
                "SELECT * FROM pratos WHERE restaurante_id = $1 ORDER BY nome ASC")
                .execute(Tuple.of(idRestaurante))
                .onItem()
                .transformToMulti(rowSet -> Multi.createFrom().iterable(rowSet))
                .onItem()
                .transform(PratoDTO::from);

    }
}
