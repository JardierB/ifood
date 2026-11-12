package github.com.jardier.model;

import java.util.ArrayList;
import java.util.List;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

public class PratoCarrinho {

    public String cliente;
    public Long prato;


     public static Uni<String> save(Pool client, String cliente, Long prato) {
        return client.preparedQuery("INSERT INTO prato_cliente (cliente, prato_id) VALUES ($1, $2) RETURNING (cliente)").execute(
                Tuple.of(cliente, prato))

                .map(pgRowSet -> pgRowSet.iterator().next().getString("cliente"));
    }

    public static Uni<List<PratoCarrinho>> findCarrinho(Pool client, String cliente) {
        return client.preparedQuery("select * from prato_cliente where cliente = $1 ").execute(Tuple.of(cliente))
                .map(pgRowSet -> {
                    List<PratoCarrinho> list = new ArrayList<>(pgRowSet.size());
                    for (Row row : pgRowSet) {
                        list.add(toPratoCarrinho(row));
                    }
                    return list;
                });
    }

     public static Uni<Boolean> delete(Pool client, String cliente) {
        return client.preparedQuery("DELETE FROM prato_cliente WHERE cliente = $1").execute(Tuple.of(cliente))
                .map(pgRowSet -> pgRowSet.rowCount() == 1);

    }

    private static PratoCarrinho toPratoCarrinho(Row row) {
        PratoCarrinho pc = new PratoCarrinho();
        pc.cliente = row.getString("cliente");
        pc.prato = row.getLong("prato_id");
        return pc;
    }
}
