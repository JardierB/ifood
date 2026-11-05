package github.com.jardier.model;

import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Tuple;

public class Restaurante {

    public Long id;
    public String nome;
    public Localizacao localizacao;

    @Override
    public String toString() {
        return "Restaurante [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao;
    }

    public void persiste(Pool pool) {
        pool.preparedQuery("insert into localizacoes (id, latitude, longitude) values ($1, $2, $3)")
                .executeAndAwait(Tuple.of(localizacao.id, localizacao.latitude, localizacao.longitude));

        pool.preparedQuery("insert into restaurantes (id, nome, localizacao_id) values ($1, $2, $3)")
                .executeAndAwait(Tuple.of(id, nome, localizacao.id));

    }
}
