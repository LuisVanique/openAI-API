import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.*;

import java.math.BigDecimal;

public class ContadorDeTokens {

    public static void main(String[] args) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncodingForModel(ModelType.GPT_4O_MINI);
        var qtd = enc.countTokens("Identifique o perfil de compra de cada cliente");

        System.out.println("qtd de Tokens: " + qtd);
        var custo = new BigDecimal(qtd).divide(new BigDecimal("1000"))
                .multiply(new BigDecimal("0.0010"));

        System.out.println("Custo: " + custo);
    }
}
