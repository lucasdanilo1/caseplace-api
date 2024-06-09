package com.restapi.desafioplace.service;

import com.restapi.desafioplace.dto.marca.*;
import com.restapi.desafioplace.model.Marca;
import com.restapi.desafioplace.dto.marca.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MarcaService {

    Marca cadastrar(DadosCadastroMarcaDTO dados);

    DadosMarcaDetalhadaDTO detalhar(Long id);

    Page<DadosListagemMarcaDTO> listar(Pageable page);

    Page<DadosListagemMarcaDTO> listarFiltradas(FiltrosMarcaDTO filtros, Pageable page);

    DadosMarcaDetalhadaDTO atualizar(Long id, AtualizaDadosMarcaDTO dados);

    DadosMarcaDetalhadaDTO inativar(Long id);

    DadosMarcaDetalhadaDTO ativar(Long id);

}
