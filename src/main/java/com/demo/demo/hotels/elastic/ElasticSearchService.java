package com.demo.demo.hotels.elastic;

import com.demo.demo.hotels.db.HotelEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class ElasticSearchService {
    private final String ELASTIC_URL = System.getenv("ELASTIC_URL");
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    private String processElasticRequest(String hint)
            throws IOException, InterruptedException {
        String jsonBody = """
                {
                  "query": {
                    "match": {
                      "name": "%s"
                    }
                  }
                }
                """;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ELASTIC_URL + "/_search"))
                .header("Authorization", "Bearer ")
                .POST(HttpRequest.BodyPublishers.ofString(
                        jsonBody.formatted(hint))
                )
                .build();
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        return response.body();
    }

    private List<HotelEntity> serializeResult(String body)
            throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(body);
        JsonNode hits = root.path("hits").path("hits");
        return mapper.readValue(
                hits.toString(),
                new TypeReference<List<HotelEntity>>() {}
        );
    }

    public List<HotelEntity> getElasticResults(String hint, int size)
            throws IOException, InterruptedException {
        String response = this.processElasticRequest(hint);
        return serializeResult(response).subList(0, size - 1);
    }


}
