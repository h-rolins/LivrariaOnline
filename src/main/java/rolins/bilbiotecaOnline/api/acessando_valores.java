package rolins.bilbiotecaOnline.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class acessando_valores {

    public static String buscarDadosDaAPI(String nome) {
        try {
            String nomeCodificado = URLEncoder.encode(nome, StandardCharsets.UTF_8.toString());

            String url = "https://gutendex.com/books/?search=" + nomeCodificado;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                return responseBody;
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject parseJsonLenient(String responseBody) {
        try {
            JsonReader reader = new JsonReader(new java.io.StringReader(responseBody));
            reader.setLenient(true);
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
