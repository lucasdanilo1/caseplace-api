package com.restapi.desafioplace.service;

import com.restapi.desafioplace.dto.marca.*;
import com.restapi.desafioplace.infra.exception.CodDenatranEmUsoException;
import com.restapi.desafioplace.infra.exception.MarcaCadastradaException;
import com.restapi.desafioplace.infra.exception.MarcaNaoEcontradaException;
import com.restapi.desafioplace.model.Marca;
import com.restapi.desafioplace.repository.MarcaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository repository;

    @Autowired
    private ModeloService modeloService;

    @Transactional
    @Override
    public Marca cadastrar(DadosCadastroMarcaDTO dados) {

        if(repository.existsByCodDenatran(dados.codDenatran())) throw new CodDenatranEmUsoException(dados.codDenatran());

        Marca marca;

        if(repository.existsByNome(dados.nome()))
            throw new MarcaCadastradaException(dados.nome());
        else
            marca = new Marca(dados);

        repository.save(marca);

        if(dados.modelos() != null) modeloService.cadastrarMultiplosModelos(dados.modelos(), marca);

        return marca;
    }

    @Override
    public DadosMarcaDetalhadaDTO atualizar(Long id, AtualizaDadosMarcaDTO dados) {

        if(repository.existsByNome(dados.nome())) throw new MarcaCadastradaException(dados.nome());

        if(repository.existsByCodDenatran(dados.codDenatran())) throw new CodDenatranEmUsoException(dados.codDenatran());

        var marca = repository.findById(id).orElseThrow(() -> new MarcaNaoEcontradaException(id));

        marca.atualizarInformacoes(dados);

        return new DadosMarcaDetalhadaDTO(marca);
    }

    @Override
    public DadosMarcaDetalhadaDTO detalhar(Long id) {
        return new DadosMarcaDetalhadaDTO(repository.getReferenceById(id));
    }

    @Override
    public Page<DadosListagemMarcaDTO> listar(Pageable page) {
        return repository.findAll(page).map(DadosListagemMarcaDTO::new);
    }

    @Override
    public Page<DadosListagemMarcaDTO> listarFiltradas(FiltrosMarcaDTO filtros, Pageable page) {
        return repository.findAll(filtros, page).map(DadosListagemMarcaDTO::new);
    }

    @Override
    public DadosMarcaDetalhadaDTO inativar(Long id) {
        var marca = repository.getReferenceById(id);
        marca.setAtivo(false);
        return new DadosMarcaDetalhadaDTO(marca);
    }

    @Override
    public DadosMarcaDetalhadaDTO ativar(Long id) {
        var marca = repository.getReferenceById(id);
        marca.setAtivo(true);
        return new DadosMarcaDetalhadaDTO(marca);
    }
}
