import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;

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
