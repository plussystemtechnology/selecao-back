import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // TODO: implementar método para buscar apenas pacientes ativos,
    // filtrando por parte do nome (case-insensitive) e de forma paginada.
}
