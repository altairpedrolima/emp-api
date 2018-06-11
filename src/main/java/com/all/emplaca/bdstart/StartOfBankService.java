package com.all.emplaca.bdstart;

import com.all.emplaca.entities.Fabricante;
import com.all.emplaca.entities.Lote;
import com.all.emplaca.enums.EstadoLote;
import com.all.emplaca.repository.FabricanteRepository;
import com.all.emplaca.repository.LoteRepository;
import com.all.emplaca.service.utils.GeradorNumeroLote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StartOfBankService {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private LoteRepository loteRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String estrategiaDeBanco;

    public void startarBanco(){

        // loteRepository.deleteAllInBatch();
        // fabricanteRepository.deleteAllInBatch();

        Fabricante fabricante = new Fabricante();
        fabricante.setCnpj("10746245000116");
        fabricante.setNome("Snow Blanks S/A");
        fabricante.setQuotaDeBlanks(200l);
        fabricanteRepository.save(fabricante);

        Lote lote = new Lote();
        lote.setNumeroLote(GeradorNumeroLote.gerarNumeroLotePara(fabricante.getId()));
        lote.setQuantidadeDeBlanksSolicitados(50l);
        lote.setEstadoLote(EstadoLote.SOLICITADO);
        lote.setFabricante(fabricante);
        loteRepository.save(lote);

    }

}
