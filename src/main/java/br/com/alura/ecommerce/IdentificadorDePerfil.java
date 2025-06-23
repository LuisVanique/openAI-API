package br.com.alura.ecommerce;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.ModelType;
import io.github.sashirestela.openai.OpenAI;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.Chat;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;

public class IdentificadorDePerfil {
    public static void main(String[] args) {
        var openAI = SimpleOpenAI.builder()
                .apiKey(System.getenv("OPENAI_KEY"))
                .build();

        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncodingForModel(ModelType.GPT_4O_MINI);
        var qtd = enc.countTokens("Identifique o perfil de compra de cada cliente");


        var clientes = carregarClientesDoArquivo();
        var qtdTokens = contarTokens(clientes);

        var modelo = "gpt-4o-mini";
        if(qtdTokens > 4096){
            modelo = "gpt-4.1-mini";
        }
        var promptSistema = """
                Identifique o perfil de compra de cada cliente
                
                A resposta deve ser:
                
                Cliente - Descreva o perfil do cliente em três palavras
                
                """;

        System.out.println("QTD TOKENS: " + qtdTokens);
        System.out.println("Modelo escolhido: " + modelo);

        var configureRequest = ChatRequest.builder().model(modelo)
                .message(ChatMessage.SystemMessage.of(promptSistema))
                .message(ChatMessage.UserMessage.of(clientes))
                .temperature(1.0)
                .maxCompletionTokens(4096).build();

        var request = openAI.chatCompletions().create(configureRequest);
        var chatResponse = request.join().getChoices();

        chatResponse.forEach(c -> {
            System.out.println(c.getMessage().getContent());
        });

    }

    private static int contarTokens(String prompt) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncodingForModel(ModelType.GPT_4O_MINI);
        return enc.countTokens(prompt);
    }

    private static String carregarClientesDoArquivo() {
            return "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino" + "cliente0 - Estilo, Conforto, Luxo\n" +
                    "cliente1 - Moda, FItness, Arte\n" +
                    "cliente2 - Romance, Vintage, Diversão\n" +
                    "cliente3 - Beleza, Conforto, Ação\n" +
                    "cliente4 - Arte, Família, Luxo\n" +
                    "cliente5 - Aventura, Beleza, Tecnologia\n" +
                    "cliente6 - Moda, Ação, Luxo\n" +
                    "cliente7 - Romance, Vintage, Fitness\n" +
                    "cliente8 - Aventura, Bebidas Estilo\n" +
                    "cliente9 - Romance, Terror, Masculino";
    }
}
