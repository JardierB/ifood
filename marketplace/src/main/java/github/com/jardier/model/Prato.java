package github.com.jardier.model;

import java.math.BigDecimal;

import github.com.jardier.dto.PratoDTO;
import io.smallrye.mutiny.Multi;

import io.vertx.mutiny.sqlclient.Pool;

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
}
