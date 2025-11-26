# Questão 07 – Filtro dinâmico com Specifications na fila de atendimento

A fila de atendimento da UPA precisa de filtros dinâmicos:

- nome do paciente (contém)
- CPF (igual)
- data de nascimento (intervalo)
- prioridade (enum)

```java
// Paciente.java
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private PrioridadeTriagem prioridade;

    // getters e setters
}
```

```java
// FilaAtendimentoFilter.java
public class FilaAtendimentoFilter {

    private String nome;
    private String cpf;
    private LocalDate dataNascimentoInicial;
    private LocalDate dataNascimentoFinal;
    private PrioridadeTriagem prioridade;

    // getters e setters
}
```

```java
// PacienteRepository.java
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
```

```java
// FilaAtendimentoController.java
@RestController
@RequestMapping("/api/fila-atendimento")
public class FilaAtendimentoController {

    private final PacienteRepository pacienteRepository;

    public FilaAtendimentoController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping("/buscar")
    public List<Paciente> buscar(@RequestBody FilaAtendimentoFilter filtro) {
        // TODO: implementar filtro dinâmico
        return Collections.emptyList();
    }
}
```

## Pergunta

1. Implemente o filtro dinâmico usando `Specification<Paciente>`.
2. Ajuste o repositório para suportar Specifications.
3. Explique as vantagens dessa abordagem em comparação com vários métodos específicos no repositório.

## Conceitos avaliados

- Spring Data JPA Specifications.
- Filtros dinâmicos e composição de critérios.
- Manutenibilidade de repositórios em sistemas grandes.
