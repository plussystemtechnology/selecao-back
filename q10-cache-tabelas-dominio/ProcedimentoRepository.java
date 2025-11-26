import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProcedimentoRepository extends JpaRepository<ProcedimentoTabelaDominio, String> {

    List<ProcedimentoTabelaDominio> findByDescricaoContainingIgnoreCase(String descricao);
}
