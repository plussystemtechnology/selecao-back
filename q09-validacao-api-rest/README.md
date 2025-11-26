# Questão 09 – Validação de entrada na API REST

O cadastro de paciente deve validar campos obrigatórios e formato de CPF.

```java
// Paciente.java
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    // getters e setters
}
```

```java
// PacienteRequest.java
public class PacienteRequest {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

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

    @PostMapping
    public ResponseEntity<Paciente> criar(@RequestBody PacienteRequest request) {
        Paciente p = new Paciente();
        p.setNome(request.getNome());
        p.setCpf(request.getCpf());
        p.setDataNascimento(request.getDataNascimento());
        Paciente salvo = pacienteRepository.save(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
```

## Pergunta

1. Inclua validações Bean Validation para:
   - `nome` obrigatório e não vazio.
   - `cpf` obrigatório e com tamanho 11 caracteres.
2. Ajuste o controller para disparar automaticamente validações e retornar erro 400 em caso de violação.
3. Explique a importância disso em uma API de ERP de Saúde.

## Conceitos avaliados

- Bean Validation (`javax.validation` / `jakarta.validation`).
- Integração com Spring MVC (`@Valid`, `@Validated`).
- Boas práticas de validação de input em APIs.
