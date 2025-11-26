import org.springframework.stereotype.Service;
import java.util.List;

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
