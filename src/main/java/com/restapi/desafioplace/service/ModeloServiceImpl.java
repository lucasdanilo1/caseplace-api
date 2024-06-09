package com.restapi.desafioplace.service;

import com.restapi.desafioplace.dto.modelo.*;
import com.restapi.desafioplace.infra.exception.MarcaNaoEcontradaException;
import com.restapi.desafioplace.infra.exception.ModeloNaoEncontradoException;
import com.restapi.desafioplace.model.Marca;
import com.restapi.desafioplace.model.Modelo;
import com.restapi.desafioplace.repository.MarcaRepository;
import com.restapi.desafioplace.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository repository;

    @Override
    public Modelo cadastrar(DadosCadastroModeloDTO dados) {

        var marca = marcaRepository.findById(dados.marcaId()).orElseThrow(() -> new MarcaNaoEcontradaException(dados.marcaId()));

        Modelo modelo = new Modelo(dados, marca);

        repository.save(modelo);

        return modelo;
    }

    @Override
    public void cadastrarMultiplosModelos(List<DadosCadastroModeloDTO> modeloDTOList, Marca marca){
        for (DadosCadastroModeloDTO dadosCadastroModeloDTO : modeloDTOList) {
            if (!repository.existsByNome(dadosCadastroModeloDTO.nome())) {
                Modelo modelo = new Modelo(dadosCadastroModeloDTO, marca);
                repository.save(modelo);
            }
        }
    }

    @Override
    public DadosModeloDetalhadoDTO atualizar(Long id, AtualizaDadosModeloDTO dados) {

        var modelo = repository.findById(id).orElseThrow(ModeloNaoEncontradoException::new);

        modelo.atualizarInformacoes(dados);

        return new DadosModeloDetalhadoDTO(modelo);
    }

    @Override
    public DadosModeloDetalhadoDTO detalhar(Long id) {

        repository.findById(id).orElseThrow(ModeloNaoEncontradoException::new);

        return new DadosModeloDetalhadoDTO(repository.getReferenceById(id));
    }

    @Override
    public Page<DadosListagemModeloDTO> listar(Pageable page) {
        return repository.findAll(page).map(DadosListagemModeloDTO::new);
    }

    @Override
    public Page<DadosListagemModeloDTO> listarFiltradas(FiltrosModeloDTO filtros, Pageable page) {
        return repository.findAll(filtros, page).map(DadosListagemModeloDTO::new);
    }

    @Override
    public DadosModeloDetalhadoDTO inativar(Long id) {
        var modelo = repository.getReferenceById(id);
        modelo.setAtivo(false);
        return new DadosModeloDetalhadoDTO(modelo);
    }

    @Override
    public DadosModeloDetalhadoDTO ativar(Long id) {
        var modelo = repository.getReferenceById(id);
        modelo.setAtivo(true);
        return new DadosModeloDetalhadoDTO(modelo);
    }
}
