# Questão 08 – Transação e comunicação entre micro-serviços (faturamento)

O serviço de Faturamento precisa:

1. Gravar uma fatura no banco local.
2. Chamar o micro-serviço de Autorização para registrar a cobrança no operador de saúde.

```java
// Fatura.java
@Entity
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroGuia;

    private BigDecimal valor;

    private boolean enviadaOperadora;

    // getters e setters
}
```

```java
// FaturaRepository.java
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
}
```

```java
// AutorizacaoClient.java
@Component
public class AutorizacaoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarParaOperadora(Fatura fatura) {
        restTemplate.postForEntity("http://autorizacao/api/faturas", fatura, Void.class);
    }
}
```

```java
// FaturamentoService.java
@Service
public class FaturamentoService {

    private final FaturaRepository faturaRepository;
    private final AutorizacaoClient autorizacaoClient;

    public FaturamentoService(FaturaRepository faturaRepository, AutorizacaoClient autorizacaoClient) {
        this.faturaRepository = faturaRepository;
        this.autorizacaoClient = autorizacaoClient;
    }

    @Transactional
    public void faturar(String numeroGuia, BigDecimal valor) {
        Fatura fatura = new Fatura();
        fatura.setNumeroGuia(numeroGuia);
        fatura.setValor(valor);
        faturaRepository.save(fatura);

        autorizacaoClient.enviarParaOperadora(fatura);

        fatura.setEnviadaOperadora(true);
    }
}
```

## Pergunta

1. Quais problemas existem nessa abordagem em um cenário de micro-serviços (falha na chamada remota, consistência, timeouts)?
2. Sugira melhorias envolvendo:
   - Timeouts e resiliência na chamada remota.
   - Estratégia de consistência eventual (por exemplo, padrão Outbox ou fila assíncrona).
3. Indique por que apenas a anotação `@Transactional` local não garante consistência distribuída.

## Conceitos avaliados

- Limites de transações ACID em micro-serviços.
- Resiliência (`RestTemplate`/Feign, timeouts, retry, circuit breaker).
- Consistência eventual e padrões de integração (Outbox, mensageria).
