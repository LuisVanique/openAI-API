package br.com.alura.ecommerce;

import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

import java.util.Scanner;

public class CategorizadorDeProdutos {
    public static void main(String[] args) {

        var leitor = new Scanner(System.in);

        System.out.println("Digite as caterogiras validas: ");
        var categorias = leitor.nextLine();

        while (true) {
            System.out.println("Digite o nome do produto: ");
            var user = leitor.nextLine();

            var system = """
                    Você é uma categorizador de produtos e deve responder apenas o nome da categoria do produto informado
                    
                    Escolha uma categoria dentre a lista abaixo:
                    
                    %s
                    
                    #### Exemplo de uso:
                    Pergunta: Bola de Futebol
                    Resposta: Esportes
                    
                    #### Regras a serem seguidas:
                    Caso o usuario pergunte algo que nao seja de categorização de produtos, 
                    voce deve responder que não pode ajudar pois o seu papel é apenas responder a categoria dos produtos
                    """.formatted(categorias);

            dispararRequisicao(user, system);
        }

    }

    public static void dispararRequisicao(String user, String system) {
        var openAI = SimpleOpenAI.
                builder()
                .apiKey(System.getenv("OPENAI_KEY"))
                .build();

        var chatRequest
                = ChatRequest.builder().model("gpt-4o-mini")
                .message(ChatMessage.SystemMessage.of(system))
                .message(ChatMessage.UserMessage.of(user))
                .temperature(0.0)
                .maxCompletionTokens(300)
                .build();

        var futureChat = openAI.chatCompletions().create(chatRequest);
        var chatResponse = futureChat.join().getChoices();
        chatResponse.forEach(c -> System.out.println(c.getMessage().getContent()));

    }
}
