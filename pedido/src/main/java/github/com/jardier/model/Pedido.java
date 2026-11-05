package github.com.jardier.model;

import java.util.List;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity (collection = "pedidos", database = "pedido")
public class Pedido {

    public String cliente;
    public List<Prato> pratos;
    public Restaurante restaurante;
    public String entregador;
    public Localizacao localizacaoEntregador;
}
