package br.com.alura.ecommerce;

import io.github.sashirestela.openai.OpenAI;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import io.github.sashirestela.openai.service.ChatCompletionServices;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.concurrent.Executors;

public class TesteIntegracao {
    public static void main(String[] args) {
        var user = "Gere 5 produtos";
        var system = "Você é uma ferramenta para um ecommerce ficticio e ira gerar apenas o nomes dos produtos";

        var apiKey = System.getenv("OPENAI_KEY");
        var openAI = SimpleOpenAI.builder()
                .apiKey(apiKey)
                .build();

        var chatRequest
                = ChatRequest.builder().model("gpt-4o-mini")
                .message(ChatMessage.SystemMessage.of(system))
                .message(ChatMessage.UserMessage.of(user))
                .temperature(1.0)
                .maxCompletionTokens(300)
                .n(5)
                .build();

        var futureChat = openAI.chatCompletions().create(chatRequest);
        var chatResponse = futureChat.join().getChoices();
        chatResponse.forEach(c -> {
            System.out.println(c.getMessage().getContent());
            System.out.println("----------------------------");
        });
    }
}
