package br.com.alura.screenmatch.service;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumoApi {

    public String obterDados(String endereco) {
        // Cria uma instância de HttpClient, que será usada para enviar a requisição HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Constrói uma requisição HTTP com o URI fornecido
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco)) // Define o URI (endereço do recurso) a partir da string fornecida
                .build(); // Finaliza a construção da requisição

        HttpResponse<String> response = null; // Declara a variável para armazenar a resposta

        try {
            // Envia a requisição e armazena a resposta na variável 'response'
            // O corpo da resposta será tratado como uma string
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // Captura e lida com erros relacionados a entrada/saída (como problemas de conexão)
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            // Captura e lida com interrupções durante o envio da requisição
            throw new RuntimeException(e);
        }

        // Obtém o corpo da resposta como uma string (geralmente em formato JSON)
        String json = response.body();

        // Retorna o corpo da resposta
        return json;
    }


}
