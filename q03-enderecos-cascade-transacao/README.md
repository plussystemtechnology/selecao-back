# Questão 03 – Cascade e transação na atualização de endereço

Ao atualizar um cadastro de paciente, o ERP deve sobrescrever os endereços antigos
com a nova lista enviada pelo front-end (substituição completa).

```java
// Paciente.java
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany
    private List<Endereco> enderecos;

    // getters e setters
}
```

```java
// Endereco.java
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String cidade;
    private String uf;

    // getters e setters
}
```

```java
// PacienteRepository.java
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
```

```java
// PacienteService.java
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente atualizarEnderecos(Long pacienteId, List<Endereco> novosEnderecos) {
        Paciente paciente = pacienteRepository.findById(pacienteId).orElseThrow();
        paciente.getEnderecos().clear();
        paciente.getEnderecos().addAll(novosEnderecos);
        return pacienteRepository.save(paciente);
    }
}
```

## Pergunta

1. Quais problemas esse mapeamento pode causar em termos de integridade de dados (endereços órfãos) e performance?
2. Ajuste o mapeamento e o serviço para:
   - Utilizar `cascade` apropriado.
   - Remover endereços órfãos automaticamente.
   - Garantir que a operação ocorra dentro de uma transação.

## Conceitos avaliados

- `cascade` e `orphanRemoval` em relacionamentos `@OneToMany`.
- Uso de `@Transactional` em operações de escrita.
- Evitar registros órfãos no banco (boa modelagem JPA).
