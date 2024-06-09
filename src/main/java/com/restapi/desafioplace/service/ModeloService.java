package com.restapi.desafioplace.service;

import com.restapi.desafioplace.dto.modelo.*;
import com.restapi.desafioplace.dto.modelo.*;
import com.restapi.desafioplace.model.Marca;
import com.restapi.desafioplace.model.Modelo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModeloService {

    Modelo cadastrar(DadosCadastroModeloDTO dados);

    void cadastrarMultiplosModelos(List<DadosCadastroModeloDTO> modeloDTOList, Marca marca);

    DadosModeloDetalhadoDTO atualizar(Long id, AtualizaDadosModeloDTO dados);

    DadosModeloDetalhadoDTO detalhar(Long id);

    Page<DadosListagemModeloDTO> listar(Pageable page);

    Page<DadosListagemModeloDTO> listarFiltradas(FiltrosModeloDTO filtros, Pageable page);

    DadosModeloDetalhadoDTO inativar(Long id);

    DadosModeloDetalhadoDTO ativar(Long id);

}
