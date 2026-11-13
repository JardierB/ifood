package github.com.jardier.service;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.jboss.logging.Logger;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

import org.elasticsearch.client.RestClient;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.ShutdownEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class ESService {

    private static final Logger LOG = Logger.getLogger(ESService.class);

    private ElasticsearchClient client;
    private RestClientTransport transport;

    void startup(@Observes StartupEvent startupEvent) {

        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();

        transport = new RestClientTransport(
                restClient,
                new JacksonJsonpMapper());

        client = new ElasticsearchClient(transport);

        try {
            var info = client.info();
            LOG.info("Elasticsearch conectado: " + info.version().number());
        } catch (IOException e) {
            LOG.error("Falha ao conectar ao Elasticsearch", e);
        }
    }

    void shutdown(@Observes ShutdownEvent shutdownEvent) {
        try {
            if (transport != null) {
                transport.close();
            }
        } catch (IOException e) {
            LOG.error("Erro ao fechar conexão com Elasticsearch", e);
        }
    }

    public void index(String index, String json) {
        try {
            client.index(i -> i
                    .index(index)
                    .document(JsonData.fromJson(json)));

        } catch (IOException e) {
            LOG.error("Erro ao indexar documento no Elasticsearch", e);
        }
    }
}
