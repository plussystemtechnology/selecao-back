import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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
