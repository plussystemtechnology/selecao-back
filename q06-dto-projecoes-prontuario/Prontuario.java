import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    private LocalDateTime dataAbertura;

    @Lob
    private String anotacoes;

    private String cidPrincipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(String anotacoes) {
        this.anotacoes = anotacoes;
    }

    public String getCidPrincipal() {
        return cidPrincipal;
    }

    public void setCidPrincipal(String cidPrincipal) {
        this.cidPrincipal = cidPrincipal;
    }
}
