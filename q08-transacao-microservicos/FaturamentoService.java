import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class FaturamentoService {

    private final FaturaRepository faturaRepository;
    private final AutorizacaoClient autorizacaoClient;

    public FaturamentoService(FaturaRepository faturaRepository, AutorizacaoClient autorizacaoClient) {
        this.faturaRepository = faturaRepository;
        this.autorizacaoClient = autorizacaoClient;
    }

    @Transactional
    public void faturar(String numeroGuia, BigDecimal valor) {
        Fatura fatura = new Fatura();
        fatura.setNumeroGuia(numeroGuia);
        fatura.setValor(valor);
        faturaRepository.save(fatura);

        autorizacaoClient.enviarParaOperadora(fatura);

        fatura.setEnviadaOperadora(true);
    }
}
