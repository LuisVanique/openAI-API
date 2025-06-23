package br.com.alura.ecommerce;

import io.github.sashirestela.openai.OpenAI;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class AnaliseDeSentimentos {
    public static void main(String[] args) {
        try {
            var openAI = SimpleOpenAI.builder()

                    .apiKey(System.getenv("OPENAI_API")).build();

            var promptSistema = """
                    Você é um analisador de sentimentos de avaliações de produtos.
                    Escreva um parágrafo com até 50 palavras resumindo as avaliações e depois atribua qual o sentimento geral para o produto.
                    Identifique também 3 pontos fortes e 3 pontos fracos identificados a partir das avaliações.
                    
                    #### Formato de saída
                    Nome do produto:
                    Resumo das avaliações: [resuma em até 50 palavras]
                    Sentimento geral: [deve ser: POSITIVO, NEUTRO ou NEGATIVO]
                    Pontos fortes: [3 bullets points]
                    Pontos fracos: [3 bullets points]
                    """;

            var diretorioArquivo = Path.of("src/main/resources/avaliacoes");

            var arquivosDeAvaliacao = Files.walk(diretorioArquivo, 1)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .collect(Collectors.toList());

            for(Path arquivo : arquivosDeAvaliacao){
                System.out.println("Iniciando a analise do arquivo: " + arquivo.getFileName());
                var promptUsuario = carregarArquivo(arquivo);

                var request = ChatRequest
                        .builder()
                        .model("gpt-4-1106-preview")
                        .message(ChatMessage.UserMessage.of(promptUsuario))
                        .message(ChatMessage.SystemMessage.of(promptSistema))
                        .build();

                var futureResponse = openAI.chatCompletions().create(request);
                var response = futureResponse.join().getChoices().get(0).getMessage().getContent();

                System.out.println("Analise finalizada");

                salvarAnalise(arquivo.getFileName().toString().replace(".txt", ""), response);
            }

        }catch (Exception e){
            System.out.println("Ocorreu um erro ao fazer as analises de sentimento");
        }

    }

    private static String carregarArquivo(Path arquivo) {
        try {
            return Files.readAllLines(arquivo).toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar o arquivo!", e);
        }
    }

    private static void salvarAnalise(String arquivo, String analise) {
        try {
            var path = Path.of("src/main/resources/analises/analise-sentimentos-" + arquivo + ".txt");
            Files.writeString(path, analise, StandardOpenOption.CREATE_NEW);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o arquivo!", e);
        }
    }

}