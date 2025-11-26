import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AutorizacaoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarParaOperadora(Fatura fatura) {
        restTemplate.postForEntity("http://autorizacao/api/faturas", fatura, Void.class);
    }
}
