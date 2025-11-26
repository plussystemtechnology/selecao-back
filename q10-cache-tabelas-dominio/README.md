# Questão 10 – Cache de tabelas de domínio (Procedimentos)

A tabela de procedimentos (SIGTAP) é grande, mas raramente muda. Ela é usada em quase todas as telas do ERP.

```java
// ProcedimentoTabelaDominio.java
@Entity
public class ProcedimentoTabelaDominio {

    @Id
    private String codigo;

    private String descricao;

    private BigDecimal valor;

    // getters e setters
}
```

```java
// ProcedimentoRepository.java
public interface ProcedimentoRepository extends JpaRepository<ProcedimentoTabelaDominio, String> {

    List<ProcedimentoTabelaDominio> findByDescricaoContainingIgnoreCase(String descricao);
}
```

```java
// ProcedimentoService.java
@Service
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;

    public ProcedimentoService(ProcedimentoRepository procedimentoRepository) {
        this.procedimentoRepository = procedimentoRepository;
    }

    public List<ProcedimentoTabelaDominio> buscarPorDescricao(String descricao) {
        return procedimentoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }
}
```

```java
// ProcedimentoController.java
@RestController
@RequestMapping("/api/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @GetMapping
    public List<ProcedimentoTabelaDominio> listar(@RequestParam String descricao) {
        return procedimentoService.buscarPorDescricao(descricao);
    }
}
```

## Pergunta

1. Ajuste o código para utilizar cache na consulta de procedimentos por descrição.
2. Mostre as anotações necessárias na aplicação e no serviço.
3. Explique o impacto positivo disso em um cenário de alta concorrência.

## Conceitos avaliados

- Spring Cache (`@EnableCaching`, `@Cacheable`).
- Estratégias de cache para tabelas de domínio.
- Escalabilidade e redução de carga de banco.
