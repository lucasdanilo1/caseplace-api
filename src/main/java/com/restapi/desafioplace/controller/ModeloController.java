package com.restapi.desafioplace.controller;

import com.restapi.desafioplace.dto.modelo.*;
import com.restapi.desafioplace.dto.modelo.*;
import com.restapi.desafioplace.service.ModeloService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("modelo")
public class ModeloController {

    @Autowired
    private ModeloService service;

    @Transactional
    @PostMapping("cadastro/save")
    public ResponseEntity<DadosModeloDetalhadoDTO> cadastrar(@Valid @RequestBody DadosCadastroModeloDTO dados, UriComponentsBuilder uriBuilder){
        var modelo = service.cadastrar(dados);
        return ResponseEntity.created(
                uriBuilder.path("modelo/{id}")
                        .buildAndExpand(modelo.getId())
                        .toUri()
        ).body(new DadosModeloDetalhadoDTO(modelo));
    }

    @GetMapping("{id}")
    public ResponseEntity<DadosModeloDetalhadoDTO> detalhar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.detalhar(id));
    }

    @GetMapping("lista")
    public ResponseEntity<Page<DadosListagemModeloDTO>> listar(@PageableDefault(size = 30) Pageable page) {
        return ResponseEntity.ok().body(service.listar(page));
    }

    @Transactional
    @PostMapping("lista")
    public ResponseEntity<Page<DadosListagemModeloDTO>> listarFiltradas(@RequestBody FiltrosModeloDTO filtros, Pageable page) {
        return ResponseEntity.ok().body(service.listarFiltradas(filtros, page));
    }

    @Transactional
    @PutMapping("atualizar/{id}")
    public ResponseEntity<DadosModeloDetalhadoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizaDadosModeloDTO dados){
        return ResponseEntity.ok().body(service.atualizar(id, dados));
    }

    @Transactional
    @DeleteMapping("inativar/{id}")
    public ResponseEntity<DadosModeloDetalhadoDTO> inativar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.inativar(id));
    }

    @Transactional
    @PutMapping("ativar/{id}")
    public ResponseEntity<DadosModeloDetalhadoDTO> ativar(@PathVariable Long id){
        return ResponseEntity.ok().body(service.ativar(id));
    }

}
