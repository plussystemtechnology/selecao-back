# Questão 05 – Problema de N+1 ao listar pacientes com consultas

O endpoint abaixo retorna a listagem de pacientes com suas consultas:

```java
// Paciente.java
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Consulta> consultas = new ArrayList<>();

    // getters e setters
}
```

```java
// Consulta.java
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private String especialidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    // getters e setters
}
```

```java
// PacienteRepository.java
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
```

```java
// PacienteController.java
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/com-consultas")
    public List<Paciente> listarComConsultas() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        pacientes.forEach(p -> p.getConsultas().size()); // força carregamento
        return pacientes;
    }
}
```

## Pergunta

1. Explique o problema de N+1 que pode ocorrer nessa implementação.
2. Ajuste o repositório para evitar N+1 usando `@EntityGraph` ou `JOIN FETCH`.
3. Comente vantagens e desvantagens em relação à carga LAZY padrão.

## Conceitos avaliados

- Problema de N+1 queries.
- Uso de `@EntityGraph` ou `JOIN FETCH`.
- Balancear performance e volume de dados retornados.
