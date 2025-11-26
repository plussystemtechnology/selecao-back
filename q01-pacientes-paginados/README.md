# Questão 01 – Consulta paginada de pacientes ativos

Você está implementando a listagem de pacientes do ERP de Saúde.  
O código abaixo mostra a entidade, o repositório e o controlador:

```java
// Paciente.java
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private boolean ativo = true;

    // getters e setters omitidos
}
```

```java
// PacienteRepository.java
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // TODO: implementar método para buscar apenas pacientes ativos,
    // filtrando por parte do nome (case-insensitive) e de forma paginada.
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

    @GetMapping
    public Page<Paciente> listar(@RequestParam(required = false) String nome) {
        // TODO: implementar uso de Pageable e chamada ao repositório
        return Page.empty();
    }
}
```

## Pergunta

1. Implemente o método adequado em `PacienteRepository`.
2. Ajuste o método `listar` do controlador para receber o `Pageable` via parâmetros da requisição e chamar o repositório.
3. Explique por que o uso de `Pageable` e de métodos derivados (query methods) é importante em uma API REST de um ERP de Saúde escalável.

## Conceitos avaliados

- Spring Data JPA – Query Methods (métodos derivados).
- Uso de `Pageable` e paginação em APIs REST.
- Boas práticas de leitura paginada para escalabilidade.
