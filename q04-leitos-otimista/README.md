# Questão 04 – Concorrência na alocação de leitos (optimistic locking)

No módulo de internação, dois médicos podem tentar alocar o mesmo leito quase ao mesmo tempo.

```java
// Leito.java
@Entity
public class Leito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private boolean ocupado;

    // getters e setters
}
```

```java
// LeitoRepository.java
public interface LeitoRepository extends JpaRepository<Leito, Long> {
}
```

```java
// InternacaoService.java
@Service
public class InternacaoService {

    private final LeitoRepository leitoRepository;

    public InternacaoService(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    public void alocarLeito(Long leitoId) {
        Leito leito = leitoRepository.findById(leitoId).orElseThrow();
        if (leito.isOcupado()) {
            throw new IllegalStateException("Leito já está ocupado");
        }
        leito.setOcupado(true);
        leitoRepository.save(leito);
    }
}
```

## Pergunta

1. Explique o risco de condição de corrida nesse código.
2. Ajuste o modelo e/ou serviço para usar optimistic locking com JPA, de forma a evitar dupla alocação de leito.
3. Indique como o serviço deve reagir quando ocorrer um conflito de versão.

## Conceitos avaliados

- Concorrência e condição de corrida em sistemas multiusuário.
- Optimistic locking com `@Version`.
- Tratamento da `OptimisticLockException`.
