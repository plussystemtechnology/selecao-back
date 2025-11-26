# Questão 02 – Mapeamento de relacionamentos Paciente x Consulta

O ERP de Saúde precisa manter o histórico de consultas de cada paciente.

Veja o código abaixo:

```java
// Paciente.java
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany
    private List<Consulta> consultas;

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

    @ManyToOne
    private Paciente paciente;

    // getters e setters
}
```

```java
// ConsultaRepository.java
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteId(Long pacienteId);
}
```

## Pergunta

1. Ajuste o mapeamento de `Paciente.consultas` para refletir corretamente o relacionamento bidirecional com `Consulta.paciente`, evitando criação de tabela intermediária desnecessária.
2. Especifique o `mappedBy` e a estratégia de `FetchType` que você considera adequada para um cenário de grande volume de consultas, justificando.
3. Comente por que é recomendado evitar carregar a lista de consultas automaticamente em toda leitura de paciente no contexto de um ERP de Saúde.

## Conceitos avaliados

- Mapeamento de relacionamentos JPA (`@OneToMany` / `@ManyToOne`).
- `mappedBy`, `FetchType.LAZY` vs `FetchType.EAGER`.
- Impacto de relacionamentos em performance.
