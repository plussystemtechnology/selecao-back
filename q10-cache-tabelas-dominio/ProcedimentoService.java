import org.springframework.stereotype.Service;
import java.util.List;

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
