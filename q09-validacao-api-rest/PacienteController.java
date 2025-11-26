import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
