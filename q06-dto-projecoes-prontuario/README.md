# Questão 06 – DTO / Projeções para dados de prontuário

O módulo de prontuário retorna muitos dados sensíveis e colunas grandes (anotações, documentos).

```java
// Prontuario.java
@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    private LocalDateTime dataAbertura;

    @Lob
    private String anotacoes;

    private String cidPrincipal;

    // getters e setters
}
```

```java
// ProntuarioRepository.java
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
```

```java
// ProntuarioController.java
@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    private final ProntuarioRepository prontuarioRepository;

    public ProntuarioController(ProntuarioRepository prontuarioRepository) {
        this.prontuarioRepository = prontuarioRepository;
    }

    @GetMapping
    public List<Prontuario> listar() {
        return prontuarioRepository.findAll();
    }
}
```

## Pergunta

1. Por que expor diretamente a entidade `Prontuario` pode ser um problema (performance, segurança, acoplamento)?
2. Implemente uma projeção (interface) ou DTO para retornar apenas:
   - `id`, `nomePaciente`, `dataAbertura`, `cidPrincipal`.
3. Ajuste o repositório e o controlador para usar essa projeção.

## Conceitos avaliados

- Uso de DTOs ou Projeções Spring Data JPA.
- Segurança de dados sensíveis.
- Redução de payload para APIs de alta escala.
