import org.springframework.stereotype.Service;

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
