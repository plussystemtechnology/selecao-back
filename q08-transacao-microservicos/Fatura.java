import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroGuia;

    private BigDecimal valor;

    private boolean enviadaOperadora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isEnviadaOperadora() {
        return enviadaOperadora;
    }

    public void setEnviadaOperadora(boolean enviadaOperadora) {
        this.enviadaOperadora = enviadaOperadora;
    }
}
